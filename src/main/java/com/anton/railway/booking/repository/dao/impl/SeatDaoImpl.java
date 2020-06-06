package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.SeatDao;
import com.anton.railway.booking.repository.entity.Seat;
import com.anton.railway.booking.repository.entity.Wagon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatDaoImpl implements SeatDao {
    private static final Logger LOG = LogManager.getLogger(SeatDaoImpl.class);
    private final Connection connection;

    private final String FIND_SEAT_BY_ID = "SELECT seat_id, `number`, wagon_id FROM seat WHERE seat_id = ?";
    private final String FIND_SEATS_BY_WAGON_ID = "SELECT seat_id, `number`, wagon_id FROM seat WHERE wagon_id = ?";

    public SeatDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Seat> findById(Long id) {
        Seat seat = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_SEAT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seat = new Seat();
                seat.setSeatId(rs.getLong("seat_id"));
                seat.setSeatNumber(rs.getString("number"));
                seat.setWagonId(rs.getLong("wagon_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(seat);
    }

    @Override
    public List<Seat> findAll() {
        return null;
    }

    @Override
    public Long save(Seat seat) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Seat seat) {

    }

    @Override
    public List<Seat> findSeatsByWagonId(Long id) {
        List<Seat> seats = new ArrayList<>();
        Seat seat = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_SEATS_BY_WAGON_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seat = new Seat();
                seat.setSeatId(rs.getLong("seat_id"));
                seat.setSeatNumber(rs.getString("number"));
                seat.setWagonId(rs.getLong("wagon_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }
}
