package com.anton.railway.booking.command.admin;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.WagonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseWagonClassCommand implements Command {
    private final WagonService wagonService;

    public ChooseWagonClassCommand(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Trip trip = (Trip) req.getSession().getAttribute("selectedTrip");
        WagonClass wagonClass = WagonClass.valueOf(req.getParameter("wagon_class"));


        req.getSession().setAttribute("wagons",
                wagonService.findWagonsByClassAndTrainId(wagonClass, trip.getTrainId()));
        req.getSession().setAttribute("selectedWagonClass", wagonClass);
        req.getSession().setAttribute("index", req.getParameter("index"));

        return new String[] {"admin", "redirect"};
    }
}
