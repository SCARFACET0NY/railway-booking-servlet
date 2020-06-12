package com.anton.railway.booking.command.view;

import com.anton.railway.booking.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexCommand implements Command {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("mailSuccess") != null) {
            req.setAttribute("mailSuccess", req.getSession().getAttribute("mailSuccess"));
            req.getSession().setAttribute("mailSuccess", null);
        }

        return "index";
    }
}
