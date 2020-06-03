package com.anton.railway.booking.repository.entity;

import com.anton.railway.booking.repository.entity.enums.TripStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    private Long tripId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private TripStatus tripStatus;
    private Long routeId;
    private Long trainId;
}
