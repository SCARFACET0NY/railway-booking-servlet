package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.TripDao;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.repository.entity.enums.TripStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDaoImpl implements TripDao {
    private static final Logger LOG = LogManager.getLogger(TripDaoImpl.class);
    private final Connection connection;

    private final String FIND_TRIPS_BY_STATUS = "SELECT trip_id, departure_date, departure_time, trip_status, " +
            "route_id, train_id FROM trip WHERE trip_status = ?";

    public TripDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Trip> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Trip> findAll() {
        return null;
    }

    @Override
    public Long save(Trip trip) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Trip trip) {

    }

    @Override
    public List<Trip> findAllByTripStatus(TripStatus status) {
        List<Trip> trips = null;
        Trip trip = null;
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_TRIPS_BY_STATUS)) {
            statement.setString(1, status.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                if (trips == null) {
                    trips = new ArrayList<>();
                }
                trip = new Trip();
                trip.setTripId(rs.getLong("trip_id"));
                trip.setDepartureDate(rs.getDate("departure_date").toLocalDate());
                trip.setDepartureTime(rs.getTime("departure_time").toLocalTime());
                trip.setTripStatus(TripStatus.valueOf(rs.getString("trip_status")));
                trip.setRouteId(rs.getLong("route_id"));
                trip.setTrainId(rs.getLong("train_id"));

                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }
}
