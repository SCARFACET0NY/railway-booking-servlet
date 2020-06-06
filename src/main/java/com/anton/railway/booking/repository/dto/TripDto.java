package com.anton.railway.booking.repository.dto;

import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.Trip;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDto {
    private Trip trip;
    private Train train;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer durationInMinutes;
    private BigDecimal minPrice;
}
