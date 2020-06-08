package com.anton.railway.booking.command.view;

import com.anton.railway.booking.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return new String[] {"cart", "forward"};
    }
}
