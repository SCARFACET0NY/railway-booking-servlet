package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.repository.entity.enums.TripStatus;

import java.time.LocalDate;
import java.util.List;

public interface TripDao extends Dao<Trip, Long> {
    List<Trip> findAllByTripStatus(TripStatus status);

    List<Trip> searchTripsByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);

    List<Trip> searchTripsByDepartureCityAndArrivalCityAndDate(String departureCity, String arrivalCity, LocalDate date);
}
