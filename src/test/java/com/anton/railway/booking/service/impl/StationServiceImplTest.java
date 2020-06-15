package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.entity.Station;
import com.anton.railway.booking.service.StationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StationServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final Station station = new Station();
    private static final List<Station> stations = new ArrayList<>();
    @Mock
    StationDao stationDao;
    StationService stationService;

    @BeforeAll
    static void beforeAll() {
        Station station1 = new Station();
        station.setStationId(ID);
        station1.setStationId(ID2);

        stations.add(station);
        stations.add(station1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        stationService = new StationServiceImpl(stationDao);
    }

    @Test
    void findAllTest() {
        when(stationDao.findAll()).thenReturn(stations);
        List<Station> returnedStations = stationService.findAll();

        assertNotNull(returnedStations);
        assertEquals(2, returnedStations.size());
        assertEquals(stations, returnedStations);

        verify(stationDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(stationDao.findById(anyLong())).thenReturn(Optional.of(station));
        Station returnedStation = stationService.findById(ID);

        assertNotNull(returnedStation);
        assertEquals(station, returnedStation);
        assertEquals(station.getStationId(), returnedStation.getStationId());

        verify(stationDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(stationDao.save(any(Station.class))).thenReturn(ID);

        assertEquals(stationService.save(station), ID);

        verify(stationDao).save(any(Station.class));
    }

    @Test
    void deleteTest() {
        stationService.delete(station);

        verify(stationDao).delete(any(Station.class));
    }

    @Test
    void deleteByIdTest() {
        stationService.deleteById(ID);

        verify(stationDao).deleteById(anyLong());
    }
}