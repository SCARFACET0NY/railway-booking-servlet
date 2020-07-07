package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.*;
import com.anton.railway.booking.repository.dao.*;
import com.anton.railway.booking.repository.dto.PaidTicket;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.*;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.TicketService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    public static final int ROWS_PER_PAGE = 5;
    private final PaymentDao paymentDao;
    private final SeatDao seatDao;
    private final TripSeatDao tripSeatDao;
    private final TicketDao ticketDao;
    private final UserDao userDao;
    private final WagonDao wagonDao;
    private final WagonTypeDao wagonTypeDao;

    public TicketServiceImpl(PaymentDao paymentDao, SeatDao seatDao, TripSeatDao tripSeatDao, TicketDao ticketDao, UserDao userDao, WagonDao wagonDao, WagonTypeDao wagonTypeDao) {
        this.paymentDao = paymentDao;
        this.seatDao = seatDao;
        this.tripSeatDao = tripSeatDao;
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.wagonDao = wagonDao;
        this.wagonTypeDao = wagonTypeDao;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketDao.findById(id).orElseThrow(() -> new TicketException("Ticket not found"));
    }

    @Override
    public Long save(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketDao.delete(ticket);
    }

    @Override
    public void deleteById(Long id) {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() -> new TicketException("Ticket not found"));
        TripSeat tripSeat = tripSeatDao.findById(ticket.getTripSeatId()).orElseThrow(() ->
                new TripSeatException("TripSeat not found"));
        Payment payment = paymentDao.findById(ticket.getPaymentId()).orElseThrow(() ->
                new PaymentException("Payment not found"));

        List<Ticket> tickets = ticketDao.findTicketsByPaymentId(ticket.getPaymentId());
        tripSeat.setSeatStatus(SeatStatus.FREE);

        tripSeatDao.save(tripSeat);
        ticketDao.delete(ticket);

        if (tickets.size() > 1) {
            payment.setTotal(payment.getTotal().subtract(ticket.getPrice()));
            paymentDao.save(payment);
        } else {
            paymentDao.delete(payment);
        }

    }

    @Override
    public Ticket createTicket(TripDto tripDto, Wagon wagon, TripSeat tripSeat) {
        BigDecimal price = tripDto.getMinPrice()
                .multiply(wagonTypeDao.findById(wagon.getWagonTypeId()).orElseThrow(() ->
                        new WagonTypeException("WagonType not found")).getPriceCoefficient())
                .setScale(2, RoundingMode.HALF_UP);
        return Ticket.builder()
                .tripSeatId(tripSeat.getTripSeatId())
                .price(price).build();
    }

    @Override
    public TicketDto createTicketDto(Ticket ticket, TripDto tripDto, TripSeatDto tripSeatDto, String wagonNumber) {
        return TicketDto.builder()
                .ticket(ticket)
                .tripDto(tripDto)
                .tripSeatDto(tripSeatDto)
                .wagonNumber(wagonNumber).build();
    }

    @Override
    public Ticket changeTicketPrice(Ticket ticket, Wagon oldWagon, Wagon newWagon) {
        if (!oldWagon.getWagonTypeId().equals(newWagon.getWagonTypeId())) {
            BigDecimal oldPriceCoefficient = wagonTypeDao.findById(oldWagon.getWagonTypeId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found")).getPriceCoefficient();
            BigDecimal newPriceCoefficient = wagonTypeDao.findById(newWagon.getWagonTypeId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found")).getPriceCoefficient();
            BigDecimal oldPrice = ticket.getPrice();

            ticket.setPrice(ticket.getPrice().divide(oldPriceCoefficient).multiply(newPriceCoefficient));
            BigDecimal newPrice = ticket.getPrice();

            Payment payment = paymentDao.findById(ticket.getPaymentId())
                    .orElseThrow(() -> new PaymentException("Payment not found"));
            payment.setTotal(payment.getTotal().add(newPrice.subtract(oldPrice)));

            paymentDao.save(payment);
        }

        return ticket;
    }

    @Override
    public List<PaidTicket> findPaidTicketsPageByTripId(Long id, Integer pageNumber) {
        List<PaidTicket> paidTickets = new ArrayList<>();
        tripSeatDao.findTripSeatsForTripByStatusPaged(
                id, SeatStatus.OCCUPIED, ROWS_PER_PAGE * pageNumber, ROWS_PER_PAGE).forEach(tripSeat -> {
            Seat seat = seatDao.findById(tripSeat.getSeatId())
                    .orElseThrow(() -> new SeatException("Seat not found"));
            Wagon wagon = wagonDao.findById(seat.getWagonId())
                    .orElseThrow(() -> new WagonException("Wagon not found"));
            WagonClass wagonClass = wagonTypeDao.findById(wagon.getWagonTypeId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found")).getWagonClass();
            Ticket ticket = ticketDao.findTicketByTripSeatId(tripSeat.getTripSeatId());
            Payment payment = paymentDao.findById(ticket.getPaymentId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found"));
            User user = userDao.findById(payment.getUserId()).orElse(null);

            paidTickets.add(PaidTicket.builder()
                    .user(user)
                    .payment(payment)
                    .ticket(ticket)
                    .tripSeat(tripSeat)
                    .wagonClass(wagonClass)
                    .wagon(wagon)
                    .seat(seat).build());
        });

        return paidTickets;
    }

    @Override
    public Integer getNumberOfPaidTicketsPagesByTripId(Long id) {
        int numOfSeats = tripSeatDao.getNumberOfTripSeatsForTripByStatus(id, SeatStatus.OCCUPIED);
        return numOfSeats % ROWS_PER_PAGE == 0
                ? numOfSeats / ROWS_PER_PAGE - 1
                : numOfSeats / ROWS_PER_PAGE ;
    }
}
