package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Seat;

import java.util.List;

public interface SeatDao extends Dao<Seat, Long> {
    List<Seat> findSeatsByWagonId(Long id);
}
