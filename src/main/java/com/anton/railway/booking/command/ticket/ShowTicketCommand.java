package com.anton.railway.booking.command.ticket;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTicketCommand implements Command {
    private final TicketService ticketService;

    public ShowTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TripDto tripDto = (TripDto) req.getSession().getAttribute("trip");
        Wagon wagon = (Wagon) req.getSession().getAttribute("selectedWagon");
        TripSeatDto tripSeatDto = (TripSeatDto) req.getSession().getAttribute("selectedSeat");

        req.getSession().setAttribute("ticket", ticketService.createTicket(tripDto, wagon, tripSeatDto.getTripSeat()));

        return new String[] {"trip", "redirect"};
    }
}
