package com.anton.railway.booking.command.cart;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.service.PaymentService;
import com.anton.railway.booking.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddTicketCommand implements Command {
    private final PaymentService paymentService;
    private final TicketService ticketService;

    public AddTicketCommand(PaymentService paymentService, TicketService ticketService) {
        this.paymentService = paymentService;
        this.ticketService = ticketService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<Long, TicketDto> cart = (Map<Long, TicketDto>) req.getSession().getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        TripDto tripDto = (TripDto) req.getSession().getAttribute("trip");
        TripSeatDto tripSeatDto = (TripSeatDto) req.getSession().getAttribute("selectedSeat");
        Ticket ticket = (Ticket) req.getSession().getAttribute("ticket");
        Wagon wagon = (Wagon) req.getSession().getAttribute("selectedWagon");

        cart.put(tripSeatDto.getTripSeat().getTripSeatId(), ticketService.createTicketDto(
                ticket, tripDto, tripSeatDto, wagon.getWagonNumber()));

        req.getSession().setAttribute("cart", cart);
        req.getSession().setAttribute("total", paymentService.getCartTotal(cart));
        req.getSession().setAttribute("trip", tripDto);

        return "redirect:trip";
    }
}
