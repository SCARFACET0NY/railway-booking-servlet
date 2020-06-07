package com.anton.railway.booking.repository.dto;

import com.anton.railway.booking.repository.entity.Ticket;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Ticket ticket;
    private TripDto tripDto;
    private TripSeatDto tripSeatDto;
    private String wagonNumber;
}
