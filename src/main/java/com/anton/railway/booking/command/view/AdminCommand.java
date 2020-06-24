package com.anton.railway.booking.command.view;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminCommand implements Command {
    private final TicketService ticketService;

    public AdminCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Trip trip = (Trip) session.getAttribute("selectedTrip");

        if (trip != null) {
            int pageNumber = req.getParameter("page") == null ?
                    (int) session.getAttribute("page") : Integer.parseInt(req.getParameter("page"));

            session.setAttribute("paidTickets", ticketService
                    .findPaidTicketsPageByTripId(trip.getTripId(), pageNumber));
            session.setAttribute("numOfPages", ticketService.getNumberOfPaidTicketsPagesByTripId(trip.getTripId()));
            session.setAttribute("page", pageNumber);
        } else {
            session.removeAttribute("paidTickets");
        }

        return "admin";
    }
}
