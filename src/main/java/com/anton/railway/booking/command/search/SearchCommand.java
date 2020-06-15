package com.anton.railway.booking.command.search;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class SearchCommand implements Command {
    private final TripService tripService;

    public SearchCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String departureCity = req.getParameter("departure");
        String arrivalCity = req.getParameter("arrival");
        String date = req.getParameter("date");

        if (!departureCity.isEmpty() && !arrivalCity.isEmpty()) {
            if (date.isEmpty()) {
                req.setAttribute("trips", tripService.searchTrips(departureCity, arrivalCity));
            } else {
                req.setAttribute("trips", tripService.searchTrips(departureCity, arrivalCity, LocalDate.parse(date)));
            }
        }

        return "index";
    }
}
