package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.exception.DaoException;
import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.entity.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TrainDaoImpl implements TrainDao {
    private static final Logger LOG = LogManager.getLogger(TrainDao.class);
    private final Connection connection;

    private final String FIND_TRAIN_BY_ID= "SELECT train_id, locomotive_id, `number` FROM train WHERE train_id = ?";

    public TrainDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Train> findById(Long id) {
        Train train = null;
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_TRAIN_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                train = new Train();
                train.setTrainId(rs.getLong("train_id"));
                train.setLocomotiveId(rs.getLong("locomotive_id"));
                train.setTrainNumber(rs.getString("number"));
            }
        } catch (SQLException e) {
            LOG.error("Extraction of train failed. ", e);
            throw new DaoException("Can't find train by id: " + e.getMessage(), e);
        }

        return Optional.ofNullable(train);
    }

    @Override
    public List<Train> findAll() {
        return null;
    }

    @Override
    public Long save(Train train) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Train train) {

    }
}
