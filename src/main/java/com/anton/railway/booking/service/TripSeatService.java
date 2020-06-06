package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.TripSeat;

import java.util.List;

public interface TripSeatService extends CrudService<TripSeat, Long> {
    List<TripSeatDto> findWagonFreeSeatsForTrip(Long tripId, Long wagonId);

    TripSeatDto convertTripSeatToTripSeatDto(TripSeat tripSeat);
}
