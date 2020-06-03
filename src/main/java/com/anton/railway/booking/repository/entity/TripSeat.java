package com.anton.railway.booking.repository.entity;

import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripSeat {
    private Long tripSeatId;
    private Long tripId;
    private Long seatId;
    private SeatStatus seatStatus;
}
