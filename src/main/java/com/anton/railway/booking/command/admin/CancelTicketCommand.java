package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelTicketCommand implements Command {
    private final TicketService ticketService;

    public CancelTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long ticketId = Long.valueOf(req.getParameter("ticket_id"));
        Long tripId = (Long) req.getSession().getAttribute("selectedTripId");

        ticketService.deleteById(ticketId);
        req.getSession().setAttribute("paidTickets", ticketService.findPaidTicketsByTripId(tripId));

        return "redirect:admin";
    }
}
