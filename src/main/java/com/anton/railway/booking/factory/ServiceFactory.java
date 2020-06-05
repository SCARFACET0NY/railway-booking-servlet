package com.anton.railway.booking.factory;

import com.anton.railway.booking.service.RouteService;
import com.anton.railway.booking.service.StationService;
import com.anton.railway.booking.service.TrainService;
import com.anton.railway.booking.service.TripService;
import com.anton.railway.booking.service.impl.RouteServiceImpl;
import com.anton.railway.booking.service.impl.StationServiceImpl;
import com.anton.railway.booking.service.impl.TrainServiceImpl;
import com.anton.railway.booking.service.impl.TripServiceImpl;

public class ServiceFactory {
    private static RouteService routeService;
    private static StationService stationService;
    private static TrainService trainService;
    private static TripService tripService;

    static {
        routeService = new RouteServiceImpl(DaoFactory.getRouteDao());
        stationService = new StationServiceImpl(DaoFactory.getStationDao());
        trainService = new TrainServiceImpl(DaoFactory.getTrainDao());
        tripService = new TripServiceImpl(DaoFactory.getRouteDao(), DaoFactory.getStationDao(), DaoFactory.getTripDao(),
                DaoFactory.getTrainDao());
    }

    public ServiceFactory() {}

    public static RouteService getRouteService() {
        return routeService;
    }

    public static StationService getStationService() {
        return stationService;
    }

    public static TrainService getTrainService() {
        return trainService;
    }

    public static TripService getTripService() {
        return tripService;
    }
}
