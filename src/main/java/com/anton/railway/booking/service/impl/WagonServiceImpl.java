package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.WagonException;
import com.anton.railway.booking.exception.WagonTypeException;
import com.anton.railway.booking.repository.dao.WagonDao;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.WagonType;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.WagonService;

import java.util.ArrayList;
import java.util.List;

public class WagonServiceImpl implements WagonService {
    private final WagonDao wagonDao;
    private final WagonTypeDao wagonTypeDao;

    public WagonServiceImpl(WagonDao wagonDao, WagonTypeDao wagonTypeDao) {
        this.wagonDao = wagonDao;
        this.wagonTypeDao = wagonTypeDao;
    }

    @Override
    public List<Wagon> findAll() {
        return wagonDao.findAll();
    }

    @Override
    public Wagon findById(Long id) {
        return wagonDao.findById(id).orElseThrow(() -> new WagonException("Wagon not found"));
    }

    @Override
    public Long save(Wagon wagon) {
        return wagonDao.save(wagon);
    }

    @Override
    public void delete(Wagon wagon) {
        wagonDao.delete(wagon);
    }

    @Override
    public void deleteById(Long id) {
        wagonDao.deleteById(id);
    }

    @Override
    public List<Wagon> findWagonsByClassAndTrainId(WagonClass wagonClass, Long trainId) {
        List<Wagon> wagons = new ArrayList<>();
        wagonDao.findWagonsByTrainId(trainId).forEach(wagon -> {
            WagonType wagonType = wagonTypeDao.findById(wagon.getWagonTypeId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found"));
            if (wagonType.getWagonClass().equals(wagonClass)) {
                wagons.add(wagon);
            }
        });

        return wagons;
    }
}
