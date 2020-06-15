package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.dao.TripDao;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.*;
import com.anton.railway.booking.repository.entity.enums.TripStatus;
import com.anton.railway.booking.service.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TripServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final String DEPARTURE_CITY = "Kiev";
    private static final String ARRIVAL_CITY = "Lviv";
    private static final List<Trip> trips = new ArrayList<>();
    private static final Route route = new Route();
    private static final Station departureStation = new Station();
    private static final Station arrivalStation = new Station();
    private static final Train train = new Train();
    private static final Trip trip = new Trip();
    @Mock
    RouteDao routeDao;
    @Mock
    StationDao stationDao;
    @Mock
    TrainDao trainDao;
    @Mock
    TripDao tripDao;
    TripService tripService;

    @BeforeAll
    static void beforeAll() {
        departureStation.setStationId(ID);
        departureStation.setCity(DEPARTURE_CITY);
        arrivalStation.setStationId(ID2);
        arrivalStation.setCity(ARRIVAL_CITY);

        route.setDepartureStationId(ID);
        route.setArrivalStationId(ID2);
        route.setDurationInMinutes(300);
        route.setBasePrice(BigDecimal.valueOf(500).setScale(2, RoundingMode.HALF_UP));

        train.setTrainId(ID);

        Trip trip1 = Trip.builder()
                .tripId(ID2)
                .departureDate(LocalDate.now())
                .departureTime(LocalTime.now())
                .routeId(ID2)
                .trainId(ID2).build();

        trip.setTripId(ID);
        trip.setDepartureDate(LocalDate.now());
        trip.setDepartureTime(LocalTime.now());
        trip.setTrainId(ID);
        trip.setRouteId(ID);

        trips.add(trip);
        trips.add(trip1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tripService = new TripServiceImpl(routeDao, stationDao, trainDao, tripDao);
    }

    @Test
    void findAllTest() {
        when(tripDao.findAll()).thenReturn(trips);
        List<Trip> returnedTrips = tripService.findAll();

        assertNotNull(returnedTrips);
        assertEquals(2, returnedTrips.size());
        assertEquals(trips, returnedTrips);

        verify(tripDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(tripDao.findById(anyLong())).thenReturn(Optional.of(trip));
        Trip returnedTrip = tripService.findById(ID);

        assertNotNull(returnedTrip);
        assertEquals(trip, returnedTrip);
        assertEquals(trip.getTripId(), returnedTrip.getTripId());

        verify(tripDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(tripDao.save(any(Trip.class))).thenReturn(ID);

        assertEquals(tripService.save(trip), ID);

        verify(tripDao).save(any(Trip.class));
    }

    @Test
    void deleteTest() {
        tripService.delete(trip);

        verify(tripDao).delete(any(Trip.class));
    }

    @Test
    void deleteByIdTest() {
        tripService.deleteById(ID);

        verify(tripDao).deleteById(anyLong());
    }

    @Test
    void getTripDtoByTripIdTest() {
        when(tripDao.findById(anyLong())).thenReturn(Optional.of(trip));
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        TripDto tripDto = tripService.getTripDtoByTripId(ID);

        assertNotNull(tripDto);
        assertEquals(trip, tripDto.getTrip());
        assertEquals(train, tripDto.getTrain());
        assertEquals(departureStation.getCity(), tripDto.getDepartureCity());
        assertEquals(arrivalStation.getCity(), tripDto.getArrivalCity());
        assertThat(tripDto.getArrivalTime(), instanceOf(LocalDateTime.class));
        assertEquals(route.getDurationInMinutes(), tripDto.getDurationInMinutes());
        assertEquals(route.getBasePrice(), tripDto.getMinPrice());

        verify(routeDao).findById(anyLong());
        verify(trainDao).findById(anyLong());
        verify(tripDao).findById(anyLong());
        verify(stationDao, times(2)).findById(anyLong());
    }

    @Test
    void findAllScheduledTripsTest() {
        when(tripDao.findAllByTripStatus(eq(TripStatus.SCHEDULED))).thenReturn(trips);
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        List<TripDto> tripDtos = tripService.findAllScheduledTrips();

        assertNotNull(tripDtos);
        assertEquals(trips.size(), tripDtos.size());

        verify(tripDao).findAllByTripStatus(any(TripStatus.class));
    }

    @Test
    void findScheduledTripsForDateTest() {
        when(tripDao.findAllByTripStatusAndDate(eq(TripStatus.SCHEDULED), any(LocalDate.class))).thenReturn(trips);
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        List<TripDto> tripDtos = tripService.findScheduledTripsForDate(LocalDate.now());

        assertNotNull(tripDtos);
        assertEquals(trips.size(), tripDtos.size());

        verify(tripDao).findAllByTripStatusAndDate(any(TripStatus.class), any(LocalDate.class));
    }

    @Test
    void searchTripsTest() {
        when(tripDao.searchTripsByDepartureCityAndArrivalCity(eq(DEPARTURE_CITY), eq(ARRIVAL_CITY))).thenReturn(trips);
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        List<TripDto> tripDtos = tripService.searchTrips(DEPARTURE_CITY, ARRIVAL_CITY);
        List<TripDto> emptyList = tripService.searchTrips("abc", "abc");

        assertNotNull(tripDtos);
        assertNotNull(emptyList);
        assertEquals(trips.size(), tripDtos.size());
        assertEquals(0, emptyList.size());

        verify(tripDao, times(2))
                .searchTripsByDepartureCityAndArrivalCity(anyString(), anyString());
    }

    @Test
    void searchTripsWithDateTest() {
        when(tripDao.searchTripsByDepartureCityAndArrivalCityAndDate(
                eq(DEPARTURE_CITY), eq(ARRIVAL_CITY), eq(LocalDate.now()))).thenReturn(trips);
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        List<TripDto> tripDtos = tripService.searchTrips(DEPARTURE_CITY, ARRIVAL_CITY, LocalDate.now());
        List<TripDto> emptyList = tripService.searchTrips(DEPARTURE_CITY, ARRIVAL_CITY, LocalDate.now().minusDays(1));

        assertNotNull(tripDtos);
        assertNotNull(emptyList);
        assertEquals(trips.size(), tripDtos.size());
        assertEquals(0, emptyList.size());

        verify(tripDao, times(2))
                .searchTripsByDepartureCityAndArrivalCityAndDate(anyString(), anyString(), any(LocalDate.class));
    }

    @Test
    void convertTripToTripDtoTest() {
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        when(stationDao.findById(ID)).thenReturn(Optional.of(departureStation));
        when(stationDao.findById(ID2)).thenReturn(Optional.of(arrivalStation));

        TripDto tripDto = tripService.convertTripToTripDto(trip);

        assertNotNull(tripDto);
        assertEquals(trip, tripDto.getTrip());
        assertEquals(train, tripDto.getTrain());
        assertEquals(departureStation.getCity(), tripDto.getDepartureCity());
        assertEquals(arrivalStation.getCity(), tripDto.getArrivalCity());
        assertThat(tripDto.getArrivalTime(), instanceOf(LocalDateTime.class));
        assertEquals(route.getDurationInMinutes(), tripDto.getDurationInMinutes());
        assertEquals(route.getBasePrice(), tripDto.getMinPrice());

        verify(routeDao).findById(anyLong());
        verify(trainDao).findById(anyLong());
        verify(stationDao, times(2)).findById(anyLong());
    }
}