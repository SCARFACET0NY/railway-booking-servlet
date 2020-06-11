package com.anton.railway.booking.repository.dao.impl;

import com.anton.railway.booking.repository.dao.TicketDao;
import com.anton.railway.booking.repository.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl implements TicketDao {
    private static final Logger LOG = LogManager.getLogger(TicketDaoImpl.class);
    private final Connection connection;

    private final String DELETE_TICKET_BY_ID = "DELETE FROM ticket WHERE ticket_id = ?";
    private final String FIND_TICKET_BY_ID = "SELECT ticket_id, trip_seat_id, price, payment_id " +
            "FROM ticket WHERE ticket_id = ?";
    private final String FIND_TICKETS_BY_PAYMENT_ID = "SELECT ticket_id, trip_seat_id, price, payment_id " +
            "FROM ticket WHERE payment_id = ?";
    private final String FIND_TICKET_BY_TRIP_SEAT_ID = "SELECT ticket_id, trip_seat_id, price, payment_id " +
            "FROM ticket WHERE trip_seat_id = ?";
    private final String UPDATE_TICKET_BY_ID = "UPDATE ticket SET trip_seat_id = ?, price = ? WHERE ticket_id = ?";

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        Ticket ticket = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TICKET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketId(rs.getLong("ticket_id"));
                ticket.setTripSeatId(rs.getLong("trip_seat_id"));
                ticket.setPrice(rs.getBigDecimal("price"));
                ticket.setPaymentId(rs.getLong("payment_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Long save(Ticket ticket) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_TICKET_BY_ID)) {
            preparedStatement.setLong(1, ticket.getTripSeatId());
            preparedStatement.setBigDecimal(2, ticket.getPrice());
            preparedStatement.setLong(3, ticket.getTicketId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket.getTicketId();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Ticket ticket) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_TICKET_BY_ID)) {
            preparedStatement.setLong(1, ticket.getTicketId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket findTicketByTripSeatId(Long id) {
        Ticket ticket = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TICKET_BY_TRIP_SEAT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketId(rs.getLong("ticket_id"));
                ticket.setTripSeatId(rs.getLong("trip_seat_id"));
                ticket.setPrice(rs.getBigDecimal("price"));
                ticket.setPaymentId(rs.getLong("payment_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }

    @Override
    public List<Ticket> findTicketsByPaymentId(Long id) {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_TICKETS_BY_PAYMENT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketId(rs.getLong("ticket_id"));
                ticket.setTripSeatId(rs.getLong("trip_seat_id"));
                ticket.setPrice(rs.getBigDecimal("price"));
                ticket.setPaymentId(rs.getLong("payment_id"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
