package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.PaymentDao;
import com.anton.railway.booking.repository.dao.TicketDao;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.Payment;
import com.anton.railway.booking.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;
    private final TicketDao ticketDao;

    public PaymentServiceImpl(PaymentDao paymentDao, TicketDao ticketDao) {
        this.paymentDao = paymentDao;
        this.ticketDao = ticketDao;
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
    public BigDecimal getCartTotal(Map<Long, TicketDto> cart) {
        return cart.values().stream()
                .map(ticketDto -> ticketDto.getTicket().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
