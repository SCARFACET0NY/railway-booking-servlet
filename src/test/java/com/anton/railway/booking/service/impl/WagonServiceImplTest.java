package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.WagonDao;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.WagonType;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.WagonService;
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
import static org.mockito.Mockito.*;

class WagonServiceImplTest {
    private static final long ID = 1L;
    private static final List<Wagon> wagons = new ArrayList<>();
    private static final Wagon wagon = new Wagon();
    @Mock
    WagonDao wagonDao;
    @Mock
    WagonTypeDao wagonTypeDao;
    WagonService wagonService;

    @BeforeAll
    static void beforeAll() {
        Wagon wagon1 = new Wagon();
        wagon1.setWagonTypeId(ID);
        wagon.setWagonId(ID);
        wagon.setWagonTypeId(ID);

        wagons.add(wagon);
        wagons.add(wagon1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        wagonService = new WagonServiceImpl(wagonDao, wagonTypeDao);
    }

    @Test
    void findAllTest() {
        when(wagonDao.findAll()).thenReturn(wagons);
        List<Wagon> returnedWagons = wagonService.findAll();

        assertNotNull(returnedWagons);
        assertEquals(2, returnedWagons.size());
        assertEquals(wagons, returnedWagons);

        verify(wagonDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(wagonDao.findById(anyLong())).thenReturn(Optional.of(wagon));
        Wagon returnedWagon = wagonService.findById(ID);

        assertNotNull(returnedWagon);
        assertEquals(wagon, returnedWagon);
        assertEquals(wagon.getWagonId(), returnedWagon.getWagonId());

        verify(wagonDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(wagonDao.save(any(Wagon.class))).thenReturn(ID);

        assertEquals(wagonService.save(wagon), ID);

        verify(wagonDao).save(any(Wagon.class));
    }

    @Test
    void deleteTest() {
        wagonService.delete(wagon);

        verify(wagonDao).delete(any(Wagon.class));
    }

    @Test
    void deleteByIdTest() {
        wagonService.deleteById(ID);

        verify(wagonDao).deleteById(anyLong());
    }

    @Test
    void findWagonsByClassAndTrainIdTest() {
        WagonType wagonType = new WagonType();
        wagonType.setWagonClass(WagonClass.ECONOMY);

        when(wagonDao.findWagonsByTrainId(anyLong())).thenReturn(wagons);
        when(wagonTypeDao.findById(anyLong())).thenReturn(Optional.of(wagonType));

        List<Wagon> returnedWagons = wagonService.findWagonsByClassAndTrainId(WagonClass.ECONOMY, ID);

        assertNotNull(returnedWagons);
        assertEquals(2, returnedWagons.size());
        assertEquals(wagons, returnedWagons);

        verify(wagonDao).findWagonsByTrainId(anyLong());
        verify(wagonTypeDao, times(2)).findById(anyLong());
    }
}