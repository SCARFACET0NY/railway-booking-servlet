package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.repository.entity.enums.TripStatus;

import java.util.List;

public interface TripSeatDao extends Dao<TripSeat, Long> {
    List<TripSeat> findTripSeatsForTripByStatusPaged(Long tripId, SeatStatus status, Integer offset, Integer numPerPage);

    List<TripSeat> findTripSeatsForWagonByStatus(Long tripId, Long wagonId, SeatStatus status);

    Integer getNumberOfTripSeatsForTripByStatus(Long tripId, SeatStatus status);
}
