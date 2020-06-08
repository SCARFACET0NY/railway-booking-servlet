package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Payment;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;

import java.util.List;

public interface PaymentDao extends Dao<Payment, Long> {
    Long savePaymentWithTickets(Payment payment, List<Ticket> tickets, List<TripSeat> seats);
}
