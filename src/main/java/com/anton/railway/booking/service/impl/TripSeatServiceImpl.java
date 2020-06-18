package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.SeatException;
import com.anton.railway.booking.exception.TripSeatException;
import com.anton.railway.booking.exception.WagonTypeException;
import com.anton.railway.booking.repository.dao.SeatDao;
import com.anton.railway.booking.repository.dao.TripSeatDao;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Seat;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.service.TripSeatService;

import java.util.ArrayList;
import java.util.List;

public class TripSeatServiceImpl implements TripSeatService {
    private final SeatDao seatDao;
    private final TripSeatDao tripSeatDao;

    public TripSeatServiceImpl(SeatDao seatDao, TripSeatDao tripSeatDao) {
        this.seatDao = seatDao;
        this.tripSeatDao = tripSeatDao;
    }

    @Override
    public List<TripSeat> findAll() {
        return tripSeatDao.findAll();
    }

    @Override
    public TripSeat findById(Long id) {
        return tripSeatDao.findById(id).orElseThrow(() -> new TripSeatException("TripSeat not found"));
    }

    @Override
    public Long save(TripSeat tripSeat) {
        return tripSeatDao.save(tripSeat);
    }

    @Override
    public void delete(TripSeat tripSeat) {
        tripSeatDao.delete(tripSeat);
    }

    @Override
    public void deleteById(Long id) {
        tripSeatDao.deleteById(id);
    }

    @Override
    public List<TripSeatDto> findWagonsFreeSeatsForTrip(Long tripId, Long wagonId) {
        List<TripSeatDto> seats = new ArrayList<>();
        tripSeatDao.findTripSeatsForWagonByStatus(tripId, wagonId, SeatStatus.FREE).forEach(tripSeat -> {
            seats.add(convertTripSeatToTripSeatDto(tripSeat));
        });

        return seats;
    }

    @Override
    public TripSeatDto convertTripSeatToTripSeatDto(TripSeat tripSeat) {
        Seat seat = seatDao.findById(tripSeat.getSeatId())
                .orElseThrow(() -> new SeatException("Seat not found"));

        return TripSeatDto.builder()
                .tripSeat(tripSeat)
                .seatNumber(seat.getSeatNumber()).build();
    }

}
