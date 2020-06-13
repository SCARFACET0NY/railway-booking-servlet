package com.anton.railway.booking.factory;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.command.admin.*;
import com.anton.railway.booking.command.cart.AddTicketCommand;
import com.anton.railway.booking.command.cart.PayCommand;
import com.anton.railway.booking.command.cart.RemoveTicketCommand;
import com.anton.railway.booking.command.login.LoginCommand;
import com.anton.railway.booking.command.login.LogoutCommand;
import com.anton.railway.booking.command.login.RegisterCommand;
import com.anton.railway.booking.command.mail.SendEmailCommand;
import com.anton.railway.booking.command.search.SearchCommand;
import com.anton.railway.booking.command.ticket.*;
import com.anton.railway.booking.command.view.*;

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
        commands.put("admin", new AdminCommand(ServiceFactory.getTicketService()));
        commands.put("admin/cancelSeatChange", new CancelSeatChangeCommand());
        commands.put("admin/cancelTicket", new CancelTicketCommand(ServiceFactory.getTicketService()));
        commands.put("admin/chooseSeat", new ChooseSeatCommand(ServiceFactory.getTripSeatService(),
                ServiceFactory.getTicketService(), ServiceFactory.getWagonService()));
        commands.put("admin/chooseTripDate", new ChooseTripDateCommand(ServiceFactory.getTripService()));
        commands.put("admin/chooseTrip", new ChooseTripCommand(ServiceFactory.getTicketService(),
                ServiceFactory.getTrainService(), ServiceFactory.getTripService()));
        commands.put("admin/chooseWagon", new ChooseWagonCommand(ServiceFactory.getTripSeatService(),
                ServiceFactory.getWagonService()));
        commands.put("admin/chooseWagonClass", new ChooseWagonClassCommand(ServiceFactory.getWagonService()));
        commands.put("cart", new CartCommand());
        commands.put("login", new LoginCommand(ServiceFactory.getUserService()));
        commands.put("logout", new LogoutCommand());
        commands.put("pay", new PayCommand(ServiceFactory.getPaymentService()));
        commands.put("register", new RegisterCommand(ServiceFactory.getUserService()));
        commands.put("removeTicket", new RemoveTicketCommand(ServiceFactory.getPaymentService()));
        commands.put("schedule", new ScheduleCommand(ServiceFactory.getTripService()));
        commands.put("search", new SearchCommand(ServiceFactory.getTripService()));
        commands.put("searchPage", new SearchPageCommand());
        commands.put("sendEmail", new SendEmailCommand(ServiceFactory.getEmailService()));
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
