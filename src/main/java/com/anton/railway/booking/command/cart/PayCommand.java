package com.anton.railway.booking.command.cart;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class PayCommand implements Command {
    private final PaymentService paymentService;

    public PayCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Map<Long, TicketDto> cart = (Map<Long, TicketDto>) session.getAttribute("cart");

        if (!cart.isEmpty()) {
            BigDecimal total = paymentService.getCartTotal(cart);
            long userId = ((User)session.getAttribute("user")).getUserId();

            paymentService.savePaymentWithTickets(total, userId, cart);
            req.getSession().setAttribute("cart", null);
            req.getSession().setAttribute("total", null);

            return "redirect:sendMail";
        }

        return "redirect:";
    }
}
