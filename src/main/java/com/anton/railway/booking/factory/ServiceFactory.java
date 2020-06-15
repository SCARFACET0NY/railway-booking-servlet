package com.anton.railway.booking.factory;

import com.anton.railway.booking.service.*;
import com.anton.railway.booking.service.impl.*;
import com.anton.railway.booking.util.UpdatableBCrypt;

public class ServiceFactory {
    private static EmailService emailService;
    private static PaymentService paymentService;
    private static RouteService routeService;
    private static StationService stationService;
    private static TicketService ticketService;
    private static TrainService trainService;
    private static TripService tripService;
    private static TripSeatService tripSeatService;
    private static UserService userService;
    private static WagonService wagonService;

    static {
        emailService = new EmailServiceImpl();
        paymentService = new PaymentServiceImpl(DaoFactory.getPaymentDao());
        routeService = new RouteServiceImpl(DaoFactory.getRouteDao());
        stationService = new StationServiceImpl(DaoFactory.getStationDao());
        ticketService = new TicketServiceImpl(DaoFactory.getPaymentDao(), DaoFactory.getSeatDao(),
                DaoFactory.getTripSeatDao(), DaoFactory.getTicketDao(), DaoFactory.getUserDao(),
                DaoFactory.getWagonDao(), DaoFactory.getWagonTypeDao());
        trainService = new TrainServiceImpl(
                DaoFactory.getTrainDao(), DaoFactory.getWagonDao(), DaoFactory.getWagonTypeDao());
        tripService = new TripServiceImpl(DaoFactory.getRouteDao(), DaoFactory.getStationDao(), DaoFactory.getTrainDao(),
                DaoFactory.getTripDao());
        tripSeatService = new TripSeatServiceImpl(DaoFactory.getSeatDao(), DaoFactory.getTripSeatDao());
        userService = new UserServiceImpl(new UpdatableBCrypt(11), DaoFactory.getUserDao());
        wagonService = new WagonServiceImpl(DaoFactory.getWagonDao(), DaoFactory.getWagonTypeDao());
    }

    public ServiceFactory() {}

    public static EmailService getEmailService() {
        return emailService;
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    public static RouteService getRouteService() {
        return routeService;
    }

    public static StationService getStationService() {
        return stationService;
    }

    public static TicketService getTicketService() {
        return ticketService;
    }

    public static TrainService getTrainService() {
        return trainService;
    }

    public static TripService getTripService() {
        return tripService;
    }

    public static TripSeatService getTripSeatService() {
        return tripSeatService;
    }

    public static WagonService getWagonService() {
        return wagonService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
