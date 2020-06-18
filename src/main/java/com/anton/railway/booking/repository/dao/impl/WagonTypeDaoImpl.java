package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.exception.DaoException;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.entity.WagonType;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class WagonTypeDaoImpl implements WagonTypeDao {
    private static final Logger LOG = LogManager.getLogger(WagonTypeDaoImpl.class);
    private final Connection connection;

    private final String FIND_WAGON_TYPE_BY_ID = "SELECT wagon_type_id, class, capacity, price_coefficient " +
            "FROM wagon_type WHERE wagon_type_id = ?";

    public WagonTypeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<WagonType> findById(Long id) {
        WagonType wagonType = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_WAGON_TYPE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wagonType = new WagonType();
                wagonType.setWagonTypeId(rs.getLong("wagon_type_id"));
                wagonType.setWagonClass(WagonClass.valueOf(rs.getString("class")));
                wagonType.setCapacity(rs.getInt("capacity"));
                wagonType.setPriceCoefficient(rs.getBigDecimal("price_coefficient"));
            }
        } catch (SQLException e) {
            LOG.error("Extraction of wagon type failed. ", e);
            throw new DaoException("Can't find wagon type by id: " + e.getMessage(), e);
        }

        return Optional.ofNullable(wagonType);
    }

    @Override
    public List<WagonType> findAll() {
        return null;
    }

    @Override
    public Long save(WagonType wagonType) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(WagonType wagonType) {

    }
}
