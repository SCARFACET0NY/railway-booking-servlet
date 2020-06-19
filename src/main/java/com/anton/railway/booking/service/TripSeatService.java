package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.TripSeat;

import java.util.List;
import java.util.Set;

public interface TripSeatService extends CrudService<TripSeat, Long> {
    List<TripSeatDto> findWagonsFreeSeatsForTrip(Long tripId, Long wagonId);

    TripSeatDto convertTripSeatToTripSeatDto(TripSeat tripSeat);
}
