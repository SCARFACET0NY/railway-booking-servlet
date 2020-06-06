package com.anton.railway.booking.command.ticket;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.WagonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetWagonClassCommand implements Command {
    private final WagonService wagonService;

    public SetWagonClassCommand(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TripDto trip = (TripDto) req.getSession().getAttribute("trip");
        WagonClass wagonClass = WagonClass.valueOf(req.getParameter("wagon_class"));

        req.getSession().setAttribute("wagons",
                wagonService.findWagonsByClassAndTrainId(wagonClass, trip.getTrain().getTrainId()));
        req.getSession().setAttribute("selectedWagonClass", wagonClass);

        return new String[] {"trip", "redirect"};
    }
}
