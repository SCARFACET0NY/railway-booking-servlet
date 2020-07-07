package com.anton.railway.booking.command.login;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;
import com.anton.railway.booking.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equals("POST")) {
            long id = userService.registerUser(req.getParameter("firstName"),
                    req.getParameter("lastName"), req.getParameter("phone"), req.getParameter("email"),
                    LocalDateTime.now(), req.getParameter("cardNumber"), req.getParameter("userName"),
                    req.getParameter("password"), AccountStatus.CUSTOMER);
            if (id > 0) {
                req.getSession().setAttribute("user", userService.findById(id));
                return "redirect:";
            }
        }

        return "register";
    }
}
