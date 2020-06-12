package com.anton.railway.booking.command.cart;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RemoveTicketCommand implements Command {
    private final PaymentService paymentService;

    public RemoveTicketCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long seatId = Long.valueOf(req.getParameter("seat_id"));
        Map<Long, TicketDto> cart = (Map<Long, TicketDto>) req.getSession().getAttribute("cart");

        cart.keySet().removeIf(seatId::equals);

        req.getSession().setAttribute("cart", cart);
        req.getSession().setAttribute("total", paymentService.getCartTotal(cart));

        return "redirect:cart";
    }
}
