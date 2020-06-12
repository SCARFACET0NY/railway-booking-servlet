package com.anton.railway.booking.command.login;

import com.anton.railway.booking.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        return "redirect:";
    }
}
