package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.Payment;

import java.math.BigDecimal;
import java.util.Map;

public interface PaymentService extends CrudService<Payment, Long> {
    BigDecimal getCartTotal(Map<Long, TicketDto> cart);
}
