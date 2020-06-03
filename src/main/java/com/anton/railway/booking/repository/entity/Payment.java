package com.anton.railway.booking.repository.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long paymentId;
    private LocalDateTime paymentDate;
    private BigDecimal total;
    private Long userId;
}
