package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.RouteException;
import com.anton.railway.booking.exception.StationException;
import com.anton.railway.booking.exception.TrainException;
import com.anton.railway.booking.exception.TripException;
import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.dao.TripDao;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.Route;
import com.anton.railway.booking.repository.entity.Station;
import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.repository.entity.enums.TripStatus;
import com.anton.railway.booking.service.TripService;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripServiceImpl implements TripService {
    private final RouteDao routeDao;
    private final StationDao stationDao;
    private final TrainDao trainDao;
    private final TripDao tripDao;

    public TripServiceImpl(RouteDao routeDao, StationDao stationDao, TrainDao trainDao, TripDao tripDao) {
        this.routeDao = routeDao;
        this.stationDao = stationDao;
        this.trainDao = trainDao;
        this.tripDao = tripDao;
    }

    @Override
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public Trip findById(Long id) {
        return tripDao.findById(id).orElseThrow(() -> new TripException("Trip not found"));
    }

    @Override
    public Long save(Trip trip) {
        return tripDao.save(trip);
    }

    @Override
    public void delete(Trip trip) {
        tripDao.delete(trip);
    }

    @Override
    public void deleteById(Long id) {
        tripDao.deleteById(id);
    }

    @Override
    public TripDto getTripDtoByTripId(Long id) {
        return convertTripToTripDto(findById(id));
    }

    @Override
    public List<TripDto> findAllScheduledTrips() {
        List<TripDto> tripDtoS = new ArrayList<>();
        List<Trip> trips = tripDao.findAllByTripStatus(TripStatus.SCHEDULED);

        trips.forEach(trip -> tripDtoS.add(convertTripToTripDto(trip)));

        return tripDtoS;
    }

    @Override
    public List<TripDto> findScheduledTripsForDate(LocalDate date) {
        List<TripDto> tripDtoS = new ArrayList<>();
        List<Trip> trips = tripDao.findAllByTripStatusAndDate(TripStatus.SCHEDULED, date);

        trips.forEach(trip -> tripDtoS.add(convertTripToTripDto(trip)));

        return tripDtoS;
    }

    @Override
    public List<TripDto> searchTrips(String departureCity, String arrivalCity) {
        List<TripDto> trips = new ArrayList<>();
        tripDao.searchTripsByDepartureCityAndArrivalCity(departureCity, arrivalCity).forEach(trip -> {
            trips.add(convertTripToTripDto(trip));
        });

        return trips;
    }

    @Override
    public List<TripDto> searchTrips(String departureCity, String arrivalCity, LocalDate date) {
        List<TripDto> trips = new ArrayList<>();
        tripDao.searchTripsByDepartureCityAndArrivalCityAndDate(departureCity, arrivalCity, date).forEach(trip -> {
            trips.add(convertTripToTripDto(trip));
        });

        return trips;
    }

    @Override
    public TripDto convertTripToTripDto(Trip trip) {
        Train train = trainDao.findById(trip.getTrainId()).orElseThrow(() ->
                new TrainException("Train not found"));
        Route route = routeDao.findById(trip.getRouteId()).orElseThrow(() ->
                new RouteException("Route not found"));
        Station departureStation = stationDao.findById(route.getDepartureStationId())
                .orElseThrow(() -> new StationException("Station not found"));
        Station arrivalStation = stationDao.findById(route.getArrivalStationId())
                .orElseThrow(() -> new StationException("Station not found"));

        LocalDateTime departure = LocalDateTime.of(trip.getDepartureDate(), trip.getDepartureTime());
        LocalDateTime arrival = departure.plusMinutes(route.getDurationInMinutes());

        return TripDto.builder()
                .trip(trip)
                .train(train)
                .departureCity(departureStation.getCity())
                .arrivalCity(arrivalStation.getCity())
                .departureTime(departure)
                .arrivalTime(arrival)
                .durationInMinutes(route.getDurationInMinutes())
                .minPrice(route.getBasePrice().setScale(2, RoundingMode.HALF_UP)).build();
    }
}
