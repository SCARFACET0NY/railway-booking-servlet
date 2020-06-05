package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StationDaoImpl implements StationDao {
    private static final Logger LOG = LogManager.getLogger(RouteDaoImpl.class);
    private final Connection connection;

    private final String FIND_STATION_BY_ID= "SELECT station_id, title, city, country, code FROM station " +
            "WHERE station_id = ?";

    public StationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Station> findById(Long id) {
        Station station = null;
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_STATION_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                station = new Station();
                station.setStationId(rs.getLong("station_id"));
                station.setTitle(rs.getString("title"));
                station.setCity(rs.getString("city"));
                station.setCountry(rs.getString("country"));
                station.setCode(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(station);
    }

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public Long save(Station station) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Station station) {

    }
}
