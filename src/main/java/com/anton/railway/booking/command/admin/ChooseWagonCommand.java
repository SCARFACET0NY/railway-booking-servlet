package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.service.TripSeatService;
import com.anton.railway.booking.service.WagonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseWagonCommand implements Command {
    private final TripSeatService tripSeatService;
    private final WagonService wagonService;

    public ChooseWagonCommand(TripSeatService tripSeatService, WagonService wagonService) {
        this.tripSeatService = tripSeatService;
        this.wagonService = wagonService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long wagon_id = Long.valueOf(req.getParameter("wagon_id"));
        Trip trip = (Trip) req.getSession().getAttribute("selectedTrip");

        req.getSession().setAttribute("selectedWagon", wagonService.findById(wagon_id));
        req.getSession().setAttribute("seats",
                tripSeatService.findWagonsFreeSeatsForTrip(trip.getTripId(), wagon_id));

        return "redirect:admin";
    }
}
