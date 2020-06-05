package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.entity.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RouteDaoImpl implements RouteDao {
    private static final Logger LOG = LogManager.getLogger(RouteDaoImpl.class);
    private final Connection connection;

    private final String FIND_ROUTE_BY_ID= "SELECT route_id, departure_station_id, arrival_station_id, " +
            "duration_in_minutes, base_price, code FROM route WHERE route_id = ?";

    public RouteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Route> findById(Long id) {
        Route route = null;
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ROUTE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                route = new Route();
                route.setRouteId(rs.getLong("route_id"));
                route.setDepartureStationId(rs.getLong("departure_station_id"));
                route.setArrivalStationId(rs.getLong("arrival_station_id"));
                route.setDurationInMinutes(rs.getInt("duration_in_minutes"));
                route.setBasePrice(rs.getBigDecimal("base_price"));
                route.setCode(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(route);
    }

    @Override
    public List<Route> findAll() {
        return null;
    }

    @Override
    public Long save(Route route) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Route route) {

    }
}
