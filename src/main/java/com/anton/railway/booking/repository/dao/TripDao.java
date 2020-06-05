package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.repository.entity.enums.TripStatus;

import java.util.List;

public interface TripDao extends Dao<Trip, Long> {
    List<Trip> findAllByTripStatus(TripStatus status);
}
