package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.TripSeatDao;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripSeatDaoImpl implements TripSeatDao {
    private static final Logger LOG = LogManager.getLogger(TripSeatDaoImpl.class);
    private final Connection connection;

    private final String FIND_TRIP_SEATS_BY_TRIP_AND_WAGON_IDS_AND_STATUS = "SELECT trip_seat_id, trip_id, " +
            "trip_seat.seat_id, status FROM trip_seat " +
            "LEFT JOIN seat ON trip_seat.seat_id = seat.seat_id " +
            "WHERE trip_id = ? AND wagon_id = ? AND status = ?";

    public TripSeatDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<TripSeat> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<TripSeat> findAll() {
        return null;
    }

    @Override
    public Long save(TripSeat tripSeat) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(TripSeat tripSeat) {

    }


    @Override
    public List<TripSeat> findTripSeatsForWagonByStatus(Long tripId, Long wagonId, SeatStatus status) {
        List<TripSeat> seats = new ArrayList<>();
        TripSeat seat = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TRIP_SEATS_BY_TRIP_AND_WAGON_IDS_AND_STATUS)) {
            preparedStatement.setLong(1, tripId);
            preparedStatement.setLong(2, wagonId);
            preparedStatement.setString(3, status.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seat = new TripSeat();
                seat.setTripSeatId(rs.getLong("trip_seat_id"));
                seat.setTripId(rs.getLong("trip_id"));
                seat.setSeatId(rs.getLong("seat_id"));
                seat.setSeatStatus(SeatStatus.valueOf(rs.getString("status")));

                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }
}
