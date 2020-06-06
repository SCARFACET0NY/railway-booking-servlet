package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.Wagon;

public interface TicketService extends CrudService<Ticket, Long> {
    Ticket createTicket(TripDto tripDto, Wagon wagon, TripSeat tripSeat);
}
