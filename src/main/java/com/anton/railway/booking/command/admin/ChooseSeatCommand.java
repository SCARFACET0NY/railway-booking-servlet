package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.service.TicketService;
import com.anton.railway.booking.service.TripSeatService;
import com.anton.railway.booking.service.WagonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChooseSeatCommand implements Command {
    private final TripSeatService tripSeatService;
    private final TicketService ticketService;
    private final WagonService wagonService;

    public ChooseSeatCommand(TripSeatService tripSeatService, TicketService ticketService, WagonService wagonService) {
        this.tripSeatService = tripSeatService;
        this.ticketService = ticketService;
        this.wagonService = wagonService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long oldSeatId = Long.valueOf(req.getParameter("old_seat_id"));
        Long newSeatId = Long.valueOf(req.getParameter("seat_id"));
        Long oldWagonId = Long.valueOf(req.getParameter("old_wagon_id"));
        Long ticketId = Long.valueOf(req.getParameter("ticket_id"));
        Integer pageNumber = (Integer) req.getSession().getAttribute("page");

        List<TripSeatDto> seats = (List<TripSeatDto>) req.getSession().getAttribute("seats");
        Wagon oldWagon = wagonService.findById(oldWagonId);
        Wagon newWagon = (Wagon) req.getSession().getAttribute("selectedWagon");
        TripSeat oldSeat = tripSeatService.findById(oldSeatId);
        Ticket ticket = ticketService.changeTicketPrice(ticketService.findById(ticketId), oldWagon, newWagon);

        oldSeat.setSeatStatus(SeatStatus.FREE);
        tripSeatService.save(oldSeat);

        seats.forEach(seat -> {
            if (seat.getTripSeat().getTripSeatId().equals(newSeatId)) {
                seat.getTripSeat().setSeatStatus(SeatStatus.OCCUPIED);
                ticket.setTripSeatId(seat.getTripSeat().getTripSeatId());
                tripSeatService.save(seat.getTripSeat());
                ticketService.save(ticket);

                req.getSession().setAttribute("paidTickets",
                        ticketService.findPaidTicketsPageByTripId(seat.getTripSeat().getTripId(), pageNumber));
            }
        });

        req.getSession().setAttribute("index", null);
        req.getSession().setAttribute("selectedWagonClass", null);
        req.getSession().setAttribute("selectedWagon", null);
        req.getSession().setAttribute("wagons", null);
        req.getSession().setAttribute("seats", null);

        return "redirect:admin";
    }
}
