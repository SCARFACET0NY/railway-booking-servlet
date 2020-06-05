package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.service.TrainService;

import java.util.List;

public class TrainServiceImpl implements TrainService {
    private final TrainDao trainDao;

    public TrainServiceImpl(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    @Override
    public List<Train> findAll() {
        return trainDao.findAll();
    }

    @Override
    public Train findById(Long id) {
        return trainDao.findById(id).orElse(null);
    }

    @Override
    public Long save(Train train) {
        return trainDao.save(train);
    }

    @Override
    public void delete(Train train) {
        trainDao.delete(train);
    }

    @Override
    public void deleteById(Long id) {
        trainDao.deleteById(id);
    }
}
