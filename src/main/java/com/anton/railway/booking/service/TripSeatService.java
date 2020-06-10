package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.TripSeat;

import java.util.List;

public interface TripSeatService extends CrudService<TripSeat, Long> {
    List<TripSeatDto> findWagonsFreeSeatsForTrip(Long tripId, Long wagonId);

    List<TripSeat> findOccupiedSeatsByTripId(Long id);

    TripSeatDto convertTripSeatToTripSeatDto(TripSeat tripSeat);
}
