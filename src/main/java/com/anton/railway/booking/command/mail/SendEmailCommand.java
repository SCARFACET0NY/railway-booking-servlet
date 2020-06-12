package com.anton.railway.booking.command.mail;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.service.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class SendEmailCommand implements Command {
    private final EmailService emailService;

    public SendEmailCommand(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<Long, TicketDto> cart = (Map<Long, TicketDto>) req.getSession().getAttribute("cart");
        BigDecimal total = (BigDecimal) req.getSession().getAttribute("total");
        User user = (User) req.getSession().getAttribute("user");

        emailService.sendEmail(user.getEmail(), "Tickets from Railway Booking",
                emailService.createEmailText(user, cart, total));

        cart.clear();
        req.getSession().setAttribute("mailSuccess", "success");
        req.getSession().setAttribute("cart", null);
        req.getSession().setAttribute("total", null);

        return "redirect:";
    }
}
