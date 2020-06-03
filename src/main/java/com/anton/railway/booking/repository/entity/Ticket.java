package com.anton.railway.booking.repository.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    private Long ticketId;
    private Long tripSeatId;
    private BigDecimal price;
    private Long paymentId;
}
