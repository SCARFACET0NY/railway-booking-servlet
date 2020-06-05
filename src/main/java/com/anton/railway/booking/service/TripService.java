package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.Trip;

import java.util.List;

public interface TripService extends CrudService<Trip, Long> {
    List<TripDto> findAllScheduledTrips();
}
