package com.anton.railway.booking.factory;

import com.anton.railway.booking.service.*;
import com.anton.railway.booking.service.impl.*;

public class ServiceFactory {
    private static PaymentService paymentService;
    private static RouteService routeService;
    private static StationService stationService;
    private static TicketService ticketService;
    private static TrainService trainService;
    private static TripService tripService;
    private static TripSeatService tripSeatService;
    private static WagonService wagonService;

    static {
        paymentService = new PaymentServiceImpl(DaoFactory.getPaymentDao(), DaoFactory.getTicketDao());
        routeService = new RouteServiceImpl(DaoFactory.getRouteDao());
        stationService = new StationServiceImpl(DaoFactory.getStationDao());
        ticketService = new TicketServiceImpl(DaoFactory.getTicketDao(), DaoFactory.getWagonTypeDao());
        trainService = new TrainServiceImpl(
                DaoFactory.getTrainDao(), DaoFactory.getWagonDao(), DaoFactory.getWagonTypeDao());
        tripService = new TripServiceImpl(DaoFactory.getRouteDao(), DaoFactory.getStationDao(), DaoFactory.getTripDao(),
                DaoFactory.getTrainDao());
        tripSeatService = new TripSeatServiceImpl(DaoFactory.getSeatDao(), DaoFactory.getTripSeatDao());
        wagonService = new WagonServiceImpl(DaoFactory.getWagonDao(), DaoFactory.getWagonTypeDao());
    }

    public ServiceFactory() {}

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
}
