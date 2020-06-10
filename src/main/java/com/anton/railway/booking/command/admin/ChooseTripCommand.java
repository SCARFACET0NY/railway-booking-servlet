package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseTripCommand implements Command {
    private final TicketService ticketService;

    public ChooseTripCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long tripId = Long.valueOf(req.getParameter("trip_id"));
        req.getSession().setAttribute("selectedTripId", tripId);
        req.getSession().setAttribute("paidTickets", ticketService.findPaidTicketsByTripId(tripId));

        return new String[] {"admin", "redirect"};
    }
}
