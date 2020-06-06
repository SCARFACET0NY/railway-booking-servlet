package com.anton.railway.booking.command.ticket;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.service.TrainService;
import com.anton.railway.booking.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTripCommand implements Command {
    private final TripService tripService;
    private final TrainService trainService;

    public SetTripCommand(TripService tripService, TrainService trainService) {
        this.tripService = tripService;
        this.trainService = trainService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("selectedWagonClass");
        req.getSession().removeAttribute("wagons");
        req.getSession().removeAttribute("selectedWagon");
        req.getSession().removeAttribute("seats");
        req.getSession().removeAttribute("selectedSeat");
        req.getSession().removeAttribute("ticket");

        TripDto trip = tripService.getTripDtoByTripId(Long.valueOf(req.getParameter("trip_id")));
        req.getSession().setAttribute("trip", trip);
        req.getSession().setAttribute("wagonClasses", trainService.getWagonClassesForTrain(trip.getTrain()));

        return new String[] {"trip", "redirect"};
    }
}
