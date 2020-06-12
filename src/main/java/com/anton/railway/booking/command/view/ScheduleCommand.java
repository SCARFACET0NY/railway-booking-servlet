package com.anton.railway.booking.command.view;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ScheduleCommand implements Command {
    private final TripService tripService;

    public ScheduleCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("trips", tripService.findAllScheduledTrips());

        return "schedule";
    }
}
