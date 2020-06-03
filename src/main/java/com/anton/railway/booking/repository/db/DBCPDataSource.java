package com.anton.railway.booking.repository.db;

import com.anton.railway.booking.exceptions.DataBaseConnectionException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for connection pooling
 */
public class DBCPDataSource {
    private static final Logger LOG = LogManager.getLogger(DBCPDataSource.class);
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://localhost/railway_booking?serverTimezone=UTC");
        dataSource.setUsername("anton");
        dataSource.setPassword("anton");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(20);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
    }

    /**
     * Gets connection from the pool
     * @return {@code Connection}
     */
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Connection failed.", e);
            throw new DataBaseConnectionException("Could not connect to database: " + e.getMessage());
        }

        return connection;
    }
}
