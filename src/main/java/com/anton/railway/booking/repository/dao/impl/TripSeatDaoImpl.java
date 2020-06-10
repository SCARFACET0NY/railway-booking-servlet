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

    private final String FIND_TRIP_SEAT_BY_ID = "SELECT trip_seat_id, trip_id, seat_id, status " +
            "FROM trip_seat WHERE trip_seat_id = ?";
    private final String FIND_TRIP_SEATS_BY_TRIP_AND_STATUS = "SELECT trip_seat_id, trip_id, seat_id, status " +
            "FROM trip_seat WHERE trip_id = ? AND status = ?";
    private final String FIND_TRIP_SEATS_BY_TRIP_AND_WAGON_IDS_AND_STATUS = "SELECT trip_seat_id, trip_id, " +
            "trip_seat.seat_id, status FROM trip_seat " +
            "LEFT JOIN seat ON trip_seat.seat_id = seat.seat_id " +
            "WHERE trip_id = ? AND wagon_id = ? AND status = ?";
    private final String UPDATE_TRIP_SEAT_BY_ID = "UPDATE trip_seat SET status = ? WHERE trip_seat_id = ?";

    public TripSeatDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<TripSeat> findById(Long id) {
        TripSeat tripSeat = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TRIP_SEAT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tripSeat = new TripSeat();
                tripSeat.setTripSeatId(rs.getLong("trip_seat_id"));
                tripSeat.setTripId(rs.getLong("trip_id"));
                tripSeat.setSeatId(rs.getLong("seat_id"));
                tripSeat.setSeatStatus(SeatStatus.valueOf(rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(tripSeat);
    }

    @Override
    public List<TripSeat> findAll() {
        return null;
    }

    @Override
    public Long save(TripSeat tripSeat) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_TRIP_SEAT_BY_ID)) {
            preparedStatement.setString(1, tripSeat.getSeatStatus().toString());
            preparedStatement.setLong(2, tripSeat.getTripSeatId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripSeat.getTripSeatId();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(TripSeat tripSeat) {

    }

    @Override
    public List<TripSeat> findTripSeatsForTripByStatus(Long tripId, SeatStatus status) {
        List<TripSeat> seats = new ArrayList<>();
        TripSeat seat = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TRIP_SEATS_BY_TRIP_AND_STATUS)) {
            preparedStatement.setLong(1, tripId);
            preparedStatement.setString(2, status.toString());
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
