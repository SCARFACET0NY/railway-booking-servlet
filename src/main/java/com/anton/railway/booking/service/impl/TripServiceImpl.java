package com.anton.railway.booking.service.impl;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripServiceImpl implements TripService {
    private final RouteDao routeDao;
    private final StationDao stationDao;
    private final TripDao tripDao;
    private final TrainDao trainDao;

    public TripServiceImpl(RouteDao routeDao, StationDao stationDao, TripDao tripDao, TrainDao trainDao) {
        this.routeDao = routeDao;
        this.stationDao = stationDao;
        this.tripDao = tripDao;
        this.trainDao = trainDao;
    }

    @Override
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public Trip findById(Long id) {
        return tripDao.findById(id).orElse(null);
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
    public List<TripDto> findAllScheduledTrips() {
        List<TripDto> tripDtoS = new ArrayList<>();
        List<Trip> trips = tripDao.findAllByTripStatus(TripStatus.SCHEDULED);

        trips.forEach(trip -> {
            Train train = trainDao.findById(trip.getTrainId()).orElse(null);
            Route route = routeDao.findById(trip.getRouteId()).orElse(null);
            Station departureStation = stationDao.findById(route.getDepartureStationId()).orElse(null);
            Station arrivalStation = stationDao.findById(route.getArrivalStationId()).orElse(null);

            LocalDateTime departure = LocalDateTime.of(trip.getDepartureDate(), trip.getDepartureTime());
            LocalDateTime arrival = departure.plusMinutes(route.getDurationInMinutes());

            tripDtoS.add(TripDto.builder()
                    .trip(trip)
                    .trainNumber(train.getTrainNumber())
                    .departureCity(departureStation.getCity())
                    .arrivalCity(arrivalStation.getCity())
                    .departureTime(departure)
                    .arrivalTime(arrival)
                    .minPrice(route.getBasePrice().setScale(2, RoundingMode.HALF_UP)).build());
        });

        return tripDtoS;
    }
}
