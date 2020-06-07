package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.TicketDao;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.service.TicketService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;
    private final WagonTypeDao wagonTypeDao;

    public TicketServiceImpl(TicketDao ticketDao, WagonTypeDao wagonTypeDao) {
        this.ticketDao = ticketDao;
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
    public void deleteById(Long aLong) {

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
}
