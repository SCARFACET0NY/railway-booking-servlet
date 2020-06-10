package com.anton.railway.booking.service.impl;

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
        return null;
    }

    @Override
    public Ticket findById(Long aLong) {
        return null;
    }

    @Override
    public Long save(Ticket ticket) {
        return null;
    }

    @Override
    public void delete(Ticket ticket) {

    }

    @Override
    public void deleteById(Long id) {
        Ticket ticket = ticketDao.findById(id).orElse(null);
        TripSeat tripSeat = tripSeatDao.findById(ticket.getTripSeatId()).orElse(null);
        Payment payment = paymentDao.findById(ticket.getPaymentId()).orElse(null);

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
                .multiply(wagonTypeDao.findById(wagon.getWagonTypeId()).orElse(null).getPriceCoefficient())
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
    public List<PaidTicket> findPaidTicketsByTripId(Long id) {
        List<PaidTicket> paidTickets = new ArrayList<>();
        tripSeatDao.findTripSeatsForTripByStatus(id, SeatStatus.OCCUPIED).forEach(tripSeat -> {
            Seat seat = seatDao.findById(tripSeat.getSeatId()).orElse(null);
            Wagon wagon = wagonDao.findById(seat.getWagonId()).orElse(null);
            WagonClass wagonClass = wagonTypeDao.findById(wagon.getWagonTypeId()).orElse(null).getWagonClass();
            Ticket ticket = ticketDao.findTicketByTripSeatId(tripSeat.getTripSeatId());
            Payment payment = paymentDao.findById(ticket.getPaymentId()).orElse(null);
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
}
