package com.anton.railway.booking.factory;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.command.search.SearchCommand;
import com.anton.railway.booking.command.view.IndexCommand;
import com.anton.railway.booking.command.view.ScheduleCommand;
import com.anton.railway.booking.command.view.SearchPageCommand;

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
        commands.put("schedule", new ScheduleCommand(ServiceFactory.getTripService()));
        commands.put("search", new SearchCommand(ServiceFactory.getTripService()));
        commands.put("searchPage", new SearchPageCommand());
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
