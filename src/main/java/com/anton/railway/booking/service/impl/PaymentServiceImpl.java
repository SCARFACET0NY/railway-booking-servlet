package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.PaymentDao;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.Payment;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return paymentDao.findById(id).orElse(null);
    }

    @Override
    public Long save(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentDao.delete(payment);
    }

    @Override
    public void deleteById(Long id) {
        paymentDao.deleteById(id);
    }

    @Override
    public Payment createPayment(BigDecimal total, Long userId) {
        return Payment.builder()
                .paymentDate(LocalDateTime.now())
                .total(total)
                .userId(userId).build();
    }

    @Override
    public BigDecimal getCartTotal(Map<Long, TicketDto> cart) {
        return cart.values().stream()
                .map(ticketDto -> ticketDto.getTicket().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Long savePaymentWithTickets(BigDecimal total, Long userId, Map<Long, TicketDto> cart) {
        List<Ticket> tickets = new ArrayList<>();
        List<TripSeat> seats = new ArrayList<>();
        cart.values().forEach(ticketDto -> {
            TripSeat seat = ticketDto.getTripSeatDto().getTripSeat();
            seat.setSeatStatus(SeatStatus.OCCUPIED);

            seats.add(seat);
            tickets.add(ticketDto.getTicket());
        });

        return paymentDao.savePaymentWithTickets(createPayment(total, userId), tickets, seats);
    }
}
