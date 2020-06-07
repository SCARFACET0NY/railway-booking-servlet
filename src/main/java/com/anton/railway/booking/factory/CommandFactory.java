package com.anton.railway.booking.factory;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.command.cart.AddTicketCommand;
import com.anton.railway.booking.command.search.SearchCommand;
import com.anton.railway.booking.command.ticket.*;
import com.anton.railway.booking.command.view.IndexCommand;
import com.anton.railway.booking.command.view.ScheduleCommand;
import com.anton.railway.booking.command.view.SearchPageCommand;
import com.anton.railway.booking.command.view.TripCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for Commands
 */
public class CommandFactory {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("", new IndexCommand());
        commands.put("addTicket", new AddTicketCommand(
                ServiceFactory.getPaymentService(), ServiceFactory.getTicketService()));
        commands.put("schedule", new ScheduleCommand(ServiceFactory.getTripService()));
        commands.put("search", new SearchCommand(ServiceFactory.getTripService()));
        commands.put("searchPage", new SearchPageCommand());
        commands.put("setSeat", new SetSeatCommand());
        commands.put("setTrip", new SetTripCommand(ServiceFactory.getTripService(), ServiceFactory.getTrainService()));
        commands.put("setWagon", new SetWagonCommand(
                ServiceFactory.getTripSeatService(), ServiceFactory.getWagonService()));
        commands.put("setWagonClass", new SetWagonClassCommand(ServiceFactory.getWagonService()));
        commands.put("showTicket", new ShowTicketCommand(ServiceFactory.getTicketService()));
        commands.put("trip", new TripCommand());
    }

    /**
     * Returns {@code Command} for specified action
     * @param action - String that specifies path for {@code Command}
     * @return {@code Command}
     */
    public static Command getCommand(String action) {
        return commands.get(action);
    }
}
