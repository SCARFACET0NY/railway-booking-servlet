package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.SeatDao;
import com.anton.railway.booking.repository.dao.TripSeatDao;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Seat;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.repository.entity.enums.TripStatus;
import com.anton.railway.booking.service.TripSeatService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TripSeatServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final String SEAT_NUMBER = "01";
    private static final Seat seat = new Seat();
    private static final TripSeat tripSeat = new TripSeat();
    private static final List<TripSeat> tripSeats = new ArrayList<>();
    @Mock
    SeatDao seatDao;
    @Mock
    TripSeatDao tripSeatDao;
    TripSeatService tripSeatService;

    @BeforeAll
    static void beforeAll() {
        TripSeat tripSeat1 = new TripSeat();
        tripSeat.setTripSeatId(ID);
        tripSeat.setSeatId(ID);
        tripSeat1.setTripSeatId(ID2);
        tripSeat1.setSeatId(ID2);

        seat.setSeatNumber(SEAT_NUMBER);

        tripSeats.add(tripSeat);
        tripSeats.add(tripSeat1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tripSeatService = new TripSeatServiceImpl(seatDao, tripSeatDao);
    }

    @Test
    void findAllTest() {
        when(tripSeatDao.findAll()).thenReturn(tripSeats);
        List<TripSeat> returnedTripSeats = tripSeatService.findAll();

        assertNotNull(returnedTripSeats);
        assertEquals(2, returnedTripSeats.size());
        assertEquals(tripSeats, returnedTripSeats);

        verify(tripSeatDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(tripSeatDao.findById(anyLong())).thenReturn(Optional.of(tripSeat));
        TripSeat returnedTripSeat = tripSeatService.findById(ID);

        assertNotNull(returnedTripSeat);
        assertEquals(tripSeat, returnedTripSeat);
        assertEquals(tripSeat.getTripSeatId(), returnedTripSeat.getTripSeatId());

        verify(tripSeatDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(tripSeatDao.save(any(TripSeat.class))).thenReturn(ID);

        assertEquals(tripSeatService.save(tripSeat), ID);

        verify(tripSeatDao).save(any(TripSeat.class));
    }

    @Test
    void deleteTest() {
        tripSeatService.delete(tripSeat);

        verify(tripSeatDao).delete(any(TripSeat.class));
    }

    @Test
    void deleteByIdTest() {
        tripSeatService.deleteById(ID);

        verify(tripSeatDao).deleteById(anyLong());
    }

    @Test
    void findWagonsFreeSeatsForTripTest() {
        when(tripSeatDao.findTripSeatsForWagonByStatus(anyLong(), anyLong(), eq(SeatStatus.FREE))).thenReturn(tripSeats);
        when(seatDao.findById(anyLong())).thenReturn(Optional.of(seat));

        List<TripSeatDto> tripSeatDtos = tripSeatService.findWagonsFreeSeatsForTrip(ID, ID2);

        assertNotNull(tripSeatDtos);
        assertEquals(tripSeats.size(), tripSeatDtos.size());

        verify(tripSeatDao).findTripSeatsForWagonByStatus(anyLong(), anyLong(), any(SeatStatus.class));
    }

    @Test
    void convertTripSeatToTripSeatDtoTest() {
        when(seatDao.findById(anyLong())).thenReturn(Optional.of(seat));

        TripSeatDto tripSeatDto = tripSeatService.convertTripSeatToTripSeatDto(tripSeat);

        assertNotNull(tripSeatDto);
        assertEquals(tripSeat, tripSeatDto.getTripSeat());
        assertEquals(SEAT_NUMBER, tripSeatDto.getSeatNumber());

        verify(seatDao).findById(anyLong());
    }
}