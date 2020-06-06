package com.anton.railway.booking.command.ticket;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TripSeatDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SetSeatCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id= Long.valueOf(req.getParameter("seat_id"));
        List<TripSeatDto> seats = (List<TripSeatDto>) req.getSession().getAttribute("seats");

        seats.forEach(seat -> {
            if (seat.getTripSeat().getTripSeatId().equals(id)) req.getSession().setAttribute("selectedSeat", seat);
        });

        return new String[] {"trip", "redirect"};
    }
}
