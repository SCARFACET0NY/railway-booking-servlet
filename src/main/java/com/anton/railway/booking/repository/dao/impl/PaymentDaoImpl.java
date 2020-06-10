package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.PaymentDao;
import com.anton.railway.booking.repository.entity.Payment;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private static final Logger LOG = LogManager.getLogger(PaymentDaoImpl.class);
    private final Connection connection;

    private final String CREATE_PAYMENT = "INSERT INTO payment (payment_date, total, user_id) VALUES (?, ?, ?)";
    private final String CREATE_TICKET = "INSERT INTO ticket (trip_seat_id, price, payment_id) " +
            "VALUES (?, ?, ?)";
    private final String CHANGE_SEAT_STATUS = "UPDATE trip_seat SET status = ? WHERE trip_seat_id = ?";
    private final String DELETE_PAYMENT_BY_ID = "DELETE FROM payment WHERE payment_id = ?";
    private final String FIND_PAYMENT_BY_ID = "SELECT payment_id, payment_date, total, user_id " +
            "FROM payment WHERE payment_id = ?";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";
    private final String UPDATE_PAYMENT = "UPDATE payment SET total = ? WHERE payment_id = ?";

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        Payment payment = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_PAYMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                payment = new Payment();
                payment.setPaymentId(rs.getLong("payment_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
                payment.setTotal(rs.getBigDecimal("total"));
                payment.setUserId(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(payment);
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Long save(Payment payment) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_PAYMENT)) {
            preparedStatement.setBigDecimal(1, payment.getTotal());
            preparedStatement.setLong(2, payment.getPaymentId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment.getPaymentId();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Payment payment) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_PAYMENT_BY_ID)) {
            preparedStatement.setLong(1, payment.getPaymentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long savePaymentWithTickets(Payment payment, List<Ticket> tickets, List<TripSeat> seats) {
        long id = -1;
        try (PreparedStatement paymentStatement = this.connection.prepareStatement(CREATE_PAYMENT);
             Statement idStatement = this.connection.createStatement();
             PreparedStatement ticketStatement = this.connection.prepareStatement(CREATE_TICKET);
             PreparedStatement seatStatement = this.connection.prepareStatement(CHANGE_SEAT_STATUS)
        ) {
            connection.setAutoCommit(false);

            paymentStatement.setTimestamp(1, Timestamp.valueOf(payment.getPaymentDate()));
            paymentStatement.setBigDecimal(2, payment.getTotal());
            paymentStatement.setLong(3, payment.getUserId());
            paymentStatement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            while (rs.next()) {
                id = rs.getLong(1);

                for (Ticket ticket : tickets) {
                    ticket.setPaymentId(id);
                    ticketStatement.setLong(1, ticket.getTripSeatId());
                    ticketStatement.setBigDecimal(2, ticket.getPrice());
                    ticketStatement.setLong(3, ticket.getPaymentId());
                    ticketStatement.addBatch();
                }
                ticketStatement.executeBatch();

                for (TripSeat seat : seats) {
                    seatStatement.setString(1, seat.getSeatStatus().toString());
                    seatStatement.setLong(2, seat.getTripSeatId());
                    seatStatement.addBatch();
                }
                seatStatement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Transaction is being rolled back. Creation of payment with tickets failed", e);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException se) {
                LOG.error("Transaction rollback failed", se);
            }
            throw new RuntimeException("Can't create payment with tickets: " + e.getMessage(), e);
        }

        return id;
    }
}
