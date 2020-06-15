package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.dao.WagonDao;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.WagonType;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.TrainService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final Train train = new Train();
    private static final List<Train> trains = new ArrayList<>();
    private static final Wagon wagon = new Wagon();
    private static final Wagon wagon1 = new Wagon();
    private static final List<Wagon> wagons = new ArrayList<>();
    private static final WagonType wagonType = new WagonType();
    @Mock
    TrainDao trainDao;
    @Mock
    WagonDao wagonDao;
    @Mock
    WagonTypeDao wagonTypeDao;
    TrainService trainService;

    @BeforeAll
    static void beforeAll() {
        Train train1 = new Train();
        train.setTrainId(ID);
        train1.setTrainId(ID2);

        wagon.setWagonTypeId(ID);
        wagon1.setWagonTypeId(ID2);

        wagonType.setWagonClass(WagonClass.BUSINESS);

        trains.add(train);
        trains.add(train1);
        wagons.add(wagon);
        wagons.add(wagon1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trainService = new TrainServiceImpl(trainDao, wagonDao, wagonTypeDao);
    }

    @Test
    void findAllTest() {
        when(trainDao.findAll()).thenReturn(trains);
        List<Train> returnedTrains = trainService.findAll();

        assertNotNull(returnedTrains);
        assertEquals(2, returnedTrains.size());
        assertEquals(trains, returnedTrains);

        verify(trainDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(trainDao.findById(anyLong())).thenReturn(Optional.of(train));
        Train returnedTrain = trainService.findById(ID);

        assertNotNull(returnedTrain);
        assertEquals(train, returnedTrain);
        assertEquals(train.getTrainId(), returnedTrain.getTrainId());

        verify(trainDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(trainDao.save(any(Train.class))).thenReturn(ID);

        assertEquals(trainService.save(train), ID);

        verify(trainDao).save(any(Train.class));
    }

    @Test
    void deleteTest() {
        trainService.delete(train);

        verify(trainDao).delete(any(Train.class));
    }

    @Test
    void deleteByIdTest() {
        trainService.deleteById(ID);

        verify(trainDao).deleteById(anyLong());
    }

    @Test
    void getWagonClassesForTrainTest() {
        when(wagonDao.findWagonsByTrainId(anyLong())).thenReturn(wagons);
        when(wagonTypeDao.findById(anyLong())).thenReturn(Optional.of(wagonType));

        Set<WagonClass> wagonClasses = trainService.getWagonClassesForTrain(train);

        assertNotNull(wagonClasses);
        assertTrue(wagonClasses.contains(wagonType.getWagonClass()));
    }
}