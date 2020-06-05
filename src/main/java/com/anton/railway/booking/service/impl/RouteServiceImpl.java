package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.entity.Route;
import com.anton.railway.booking.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }

    @Override
    public Route findById(Long id) {
        return routeDao.findById(id).orElse(null);
    }

    @Override
    public Long save(Route route) {
        return routeDao.save(route);
    }

    @Override
    public void delete(Route route) {
        routeDao.delete(route);
    }

    @Override
    public void deleteById(Long id) {
        routeDao.deleteById(id);
    }
}
