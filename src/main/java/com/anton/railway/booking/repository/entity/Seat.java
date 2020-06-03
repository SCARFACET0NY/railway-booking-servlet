package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seat {
    private Long seatId;
    private String seatNumber;
    private Long wagonId;
}
