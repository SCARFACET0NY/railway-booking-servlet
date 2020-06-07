package com.anton.railway.booking.command.login;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equals("POST")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (userService.verifyUser(username, password)) {
                req.getSession().setAttribute("user", userService.getUserByUsername(username));

                return new String[] {"", "redirect"};
            }
            req.setAttribute("invalid", "invalid");
        }
        return new String[] {"login", "forward"};
    }
}
