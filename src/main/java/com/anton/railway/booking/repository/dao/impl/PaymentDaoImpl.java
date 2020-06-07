package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.PaymentDao;
import com.anton.railway.booking.repository.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private static final Logger LOG = LogManager.getLogger(PaymentDaoImpl.class);
    private final Connection connection;

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Payment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Long save(Payment payment) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Payment payment) {

    }
}
