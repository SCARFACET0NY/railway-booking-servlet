package com.anton.railway.booking.repository.dto;

import com.anton.railway.booking.repository.entity.TripSeat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripSeatDto {
    private TripSeat tripSeat;
    private String seatNumber;
}
