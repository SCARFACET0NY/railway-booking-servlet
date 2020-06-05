package com.anton.railway.booking.factory;

import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.dao.TripDao;
import com.anton.railway.booking.repository.dao.impl.RouteDaoImpl;
import com.anton.railway.booking.repository.dao.impl.StationDaoImpl;
import com.anton.railway.booking.repository.dao.impl.TrainDaoImpl;
import com.anton.railway.booking.repository.dao.impl.TripDaoImpl;
import com.anton.railway.booking.repository.db.DBCPDataSource;

public class DaoFactory {
    private static RouteDao routeDao;
    private static StationDao stationDao;
    private static TrainDao trainDao;
    private static TripDao tripDao;

    static {
        routeDao = new RouteDaoImpl(DBCPDataSource.getConnection());
        stationDao = new StationDaoImpl(DBCPDataSource.getConnection());
        trainDao = new TrainDaoImpl(DBCPDataSource.getConnection());
        tripDao = new TripDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static RouteDao getRouteDao() {
        return routeDao;
    }

    public static StationDao getStationDao() {
        return stationDao;
    }

    public static TrainDao getTrainDao() {
        return trainDao;
    }

    public static TripDao getTripDao() {
        return tripDao;
    }
}
