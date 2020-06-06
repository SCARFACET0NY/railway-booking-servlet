package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.WagonDao;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.WagonType;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WagonDaoImpl implements WagonDao {
    private static final Logger LOG = LogManager.getLogger(WagonDaoImpl.class);
    private final Connection connection;

    private final String FIND_WAGON_BY_ID = "SELECT wagon_id, `number`, wagon_type_id, train_id FROM wagon " +
            "WHERE wagon_id = ?";
    private final String FIND_WAGONS_BY_TRAIN_ID = "SELECT wagon_id, `number`, wagon_type_id, train_id FROM wagon " +
            "WHERE train_id = ?";

    public WagonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Wagon> findById(Long id) {
        Wagon wagon = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_WAGON_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wagon = new Wagon();
                wagon.setWagonId(rs.getLong("wagon_id"));
                wagon.setWagonNumber(rs.getString("number"));
                wagon.setWagonTypeId(rs.getLong("wagon_type_id"));
                wagon.setTrainId(rs.getLong("train_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(wagon);
    }

    @Override
    public List<Wagon> findAll() {
        return null;
    }

    @Override
    public Long save(Wagon wagon) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Wagon wagon) {

    }

    @Override
    public List<Wagon> findWagonsByTrainId(Long id) {
        List<Wagon> wagons = new ArrayList<>();
        Wagon wagon = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_WAGONS_BY_TRAIN_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wagon = new Wagon();
                wagon.setWagonId(rs.getLong("wagon_id"));
                wagon.setWagonNumber(rs.getString("number"));
                wagon.setWagonTypeId(rs.getLong("wagon_type_id"));
                wagon.setTrainId(rs.getLong("train_id"));

                wagons.add(wagon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wagons;
    }
}
