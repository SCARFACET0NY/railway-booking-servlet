package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.PaidTicket;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.Wagon;

import java.util.List;

public interface TicketService extends CrudService<Ticket, Long> {
    Ticket createTicket(TripDto tripDto, Wagon wagon, TripSeat tripSeat);

    TicketDto createTicketDto(Ticket ticket, TripDto tripDto, TripSeatDto tripSeatDto, String wagonNumber);

    Ticket changeTicketPrice(Ticket ticket, Wagon oldWagon, Wagon newWagon);

    List<PaidTicket> findPaidTicketsPageByTripId(Long id, Integer pageNumber);

    Integer getNumberOfPaidTicketsPagesByTripId(Long id);
}
