package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.exception.DaoException;
import com.anton.railway.booking.repository.dao.UserDao;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    private final Connection connection;

    private final String CHECK_USER = "SELECT user_id FROM `user` WHERE username = ?";
    private final String CREATE_USER = "INSERT INTO `user` (first_name, last_name, phone, email, date_joined, " +
            "card_number, username, password, account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_USER_BY_ID = "SELECT user_id, first_name, last_name, phone, email, date_joined, " +
            "card_number, username, password, account_status FROM `user` WHERE user_id = ? ";
    private final String GET_PASSWORD = "SELECT password FROM `user` WHERE username = ?";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setDateJoined(rs.getTimestamp("date_joined").toLocalDateTime());
                user.setCardNumber(rs.getLong("card_number"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
            }
        } catch (SQLException e) {
            LOG.error("Extraction of user failed. ", e);
            throw new DaoException("Can't find user by id: " + e.getMessage(), e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Long save(User user) {
        long id = -1;
        try (
                PreparedStatement statement = this.connection.prepareStatement(CREATE_USER);
                Statement idStatement = this.connection.createStatement()
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, Timestamp.valueOf(user.getDateJoined()));
            statement.setLong(6, user.getCardNumber());
            statement.setString(7, user.getUsername());
            statement.setString(8, user.getPassword());
            statement.setString(9, user.getAccountStatus().toString());
            statement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Saving of user failed. ", e);
            throw new DaoException("Can't save user: " + e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Long checkUsername(String username) {
        long id = -1;
        try (PreparedStatement statement = this.connection.prepareStatement(CHECK_USER)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getLong("user_id");
            }
        } catch (SQLException e) {
            LOG.error("Username verification failed.", e);
            throw new DaoException("Can't find username: " + e.getMessage(), e);
        }

        return id;
    }

    @Override
    public String getPasswordForUsername(String username) {
        String password = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_PASSWORD)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            LOG.error("Unsuccessful password verification.", e);
            throw new DaoException("Can't find password for username: " + e.getMessage(), e);
        }
        return password;
    }
}
