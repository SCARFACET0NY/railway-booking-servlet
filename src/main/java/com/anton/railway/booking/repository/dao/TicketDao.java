package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Ticket;

import java.util.List;

public interface TicketDao extends Dao<Ticket, Long> {
    Ticket findTicketByTripSeatId(Long id);

    List<Ticket> findTicketsByPaymentId(Long id);
}
