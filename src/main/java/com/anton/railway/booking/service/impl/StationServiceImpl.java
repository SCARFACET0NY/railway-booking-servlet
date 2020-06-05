package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.StationDao;
import com.anton.railway.booking.repository.entity.Station;
import com.anton.railway.booking.service.StationService;

import java.util.List;

public class StationServiceImpl implements StationService {
    private final StationDao stationDao;

    public StationServiceImpl(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Override
    public List<Station> findAll() {
        return stationDao.findAll();
    }

    @Override
    public Station findById(Long id) {
        return stationDao.findById(id).orElse(null);
    }

    @Override
    public Long save(Station station) {
        return stationDao.save(station);
    }

    @Override
    public void delete(Station station) {
        stationDao.delete(station);
    }

    @Override
    public void deleteById(Long id) {
        stationDao.deleteById(id);
    }
}
