package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.service.TicketService;
import com.anton.railway.booking.service.TrainService;
import com.anton.railway.booking.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseTripCommand implements Command {
    private final TicketService ticketService;
    private final TrainService trainService;
    private final TripService tripService;

    public ChooseTripCommand(TicketService ticketService, TrainService trainService, TripService tripService) {
        this.ticketService = ticketService;
        this.trainService = trainService;
        this.tripService = tripService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long tripId = Long.valueOf(req.getParameter("trip_id"));
        Trip trip = tripService.findById(tripId);
        Train train = trainService.findById(trip.getTrainId());

        req.getSession().setAttribute("selectedTrip", trip);
        req.getSession().setAttribute("page", 0);
        req.getSession().setAttribute("wagonClasses", trainService.getWagonClassesForTrain(train));

        return "redirect:admin";
    }
}
