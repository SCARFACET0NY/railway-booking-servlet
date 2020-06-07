package com.anton.railway.booking.factory;

import com.anton.railway.booking.repository.dao.*;
import com.anton.railway.booking.repository.dao.impl.*;
import com.anton.railway.booking.repository.db.DBCPDataSource;

public class DaoFactory {
    private static PaymentDao paymentDao;
    private static RouteDao routeDao;
    private static SeatDao seatDao;
    private static StationDao stationDao;
    private static TicketDao ticketDao;
    private static TrainDao trainDao;
    private static TripDao tripDao;
    private static TripSeatDao tripSeatDao;
    private static WagonDao wagonDao;
    private static WagonTypeDao wagonTypeDao;
    private static UserDao userDao;

    static {
        paymentDao = new PaymentDaoImpl(DBCPDataSource.getConnection());
        routeDao = new RouteDaoImpl(DBCPDataSource.getConnection());
        seatDao = new SeatDaoImpl(DBCPDataSource.getConnection());
        stationDao = new StationDaoImpl(DBCPDataSource.getConnection());
        ticketDao = new TicketDaoImpl(DBCPDataSource.getConnection());
        trainDao = new TrainDaoImpl(DBCPDataSource.getConnection());
        tripDao = new TripDaoImpl(DBCPDataSource.getConnection());
        tripSeatDao = new TripSeatDaoImpl(DBCPDataSource.getConnection());
        wagonDao = new WagonDaoImpl(DBCPDataSource.getConnection());
        wagonTypeDao = new WagonTypeDaoImpl(DBCPDataSource.getConnection());
        userDao = new UserDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public static RouteDao getRouteDao() {
        return routeDao;
    }

    public static SeatDao getSeatDao() {
        return seatDao;
    }

    public static StationDao getStationDao() {
        return stationDao;
    }

    public static TicketDao getTicketDao() {
        return ticketDao;
    }

    public static TrainDao getTrainDao() {
        return trainDao;
    }

    public static TripDao getTripDao() {
        return tripDao;
    }

    public static TripSeatDao getTripSeatDao() {
        return tripSeatDao;
    }

    public static WagonDao getWagonDao() {
        return wagonDao;
    }

    public static WagonTypeDao getWagonTypeDao() {
        return wagonTypeDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
