package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripService extends CrudService<Trip, Long> {
    TripDto getTripDtoByTripId(Long id);

    List<TripDto> findAllScheduledTrips();

    List<TripDto> findScheduledTripsForDate(LocalDate date);

    List<TripDto> searchTrips(String departureCity, String arrivalCity);

    List<TripDto> searchTrips(String departureCity, String arrivalCity, LocalDate date);

    TripDto convertTripToTripDto(Trip trip);
}
