package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Route {
    private Long routeId;
    private Long departureStation;
    private Long arrivalStation;
    private Integer durationInMinutes;
    private BigDecimal basePrice;
    private String code;
}
