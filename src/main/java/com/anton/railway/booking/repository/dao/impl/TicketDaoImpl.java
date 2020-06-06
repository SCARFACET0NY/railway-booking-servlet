package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.TicketDao;
import com.anton.railway.booking.repository.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl implements TicketDao {
    private static final Logger LOG = LogManager.getLogger(TicketDaoImpl.class);
    private final Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Ticket> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Long save(Ticket ticket) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Ticket ticket) {

    }
}
