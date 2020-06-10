package com.anton.railway.booking.repository.dto;

import com.anton.railway.booking.repository.entity.*;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaidTicket {
    private User user;
    private Payment payment;
    private Ticket ticket;
    private TripSeat tripSeat;
    private WagonClass wagonClass;
    private Wagon wagon;
    private Seat seat;
}
