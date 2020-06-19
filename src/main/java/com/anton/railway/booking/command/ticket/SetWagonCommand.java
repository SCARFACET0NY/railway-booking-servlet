package com.anton.railway.booking.command.ticket;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.service.TripSeatService;
import com.anton.railway.booking.service.WagonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetWagonCommand implements Command {
    private final TripSeatService tripSeatService;
    private final WagonService wagonService;

    public SetWagonCommand(TripSeatService tripSeatService, WagonService wagonService) {
        this.tripSeatService = tripSeatService;
        this.wagonService = wagonService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long wagon_id = Long.valueOf(req.getParameter("wagon_id"));
        TripDto tripDto = (TripDto) req.getSession().getAttribute("trip");
        Map<Long, TicketDto> cart = (Map<Long, TicketDto>) req.getSession().getAttribute("cart");
        List<TripSeatDto> seats = tripSeatService.findWagonsFreeSeatsForTrip(tripDto.getTrip().getTripId(), wagon_id);

        if (cart != null) {
            seats.removeIf(seat -> cart.containsKey(seat.getTripSeat().getTripSeatId()));
        }

        req.getSession().setAttribute("selectedWagon", wagonService.findById(wagon_id));
        req.getSession().setAttribute("seats", seats);

        return "redirect:trip";
    }
}
