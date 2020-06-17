package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class ChooseTripDateCommand implements Command {
    private final TripService tripService;

    public ChooseTripDateCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String date = req.getParameter("date");
        req.getSession().setAttribute("date", date);
        req.getSession().setAttribute("trips", tripService.findScheduledTripsForDate(LocalDate.parse(date)));
        req.getSession().removeAttribute("selectedTrip");

        return "redirect:admin";
    }
}
