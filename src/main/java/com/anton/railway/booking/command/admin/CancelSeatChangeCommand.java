package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelSeatChangeCommand implements Command {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("index", null);
        req.getSession().setAttribute("selectedWagonClass", null);
        req.getSession().setAttribute("selectedWagon", null);
        req.getSession().setAttribute("wagons", null);
        req.getSession().setAttribute("seats", null);

        return "redirect:admin";
    }
}
