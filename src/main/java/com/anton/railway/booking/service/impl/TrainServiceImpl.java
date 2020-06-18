package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.TrainException;
import com.anton.railway.booking.exception.WagonTypeException;
import com.anton.railway.booking.repository.dao.TrainDao;
import com.anton.railway.booking.repository.dao.WagonDao;
import com.anton.railway.booking.repository.dao.WagonTypeDao;
import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.TrainService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainServiceImpl implements TrainService {
    private final TrainDao trainDao;
    private final WagonDao wagonDao;
    private final WagonTypeDao wagonTypeDao;

    public TrainServiceImpl(TrainDao trainDao, WagonDao wagonDao, WagonTypeDao wagonTypeDao) {
        this.trainDao = trainDao;
        this.wagonDao = wagonDao;
        this.wagonTypeDao = wagonTypeDao;
    }

    @Override
    public List<Train> findAll() {
        return trainDao.findAll();
    }

    @Override
    public Train findById(Long id) {
        return trainDao.findById(id).orElseThrow(() -> new TrainException("Train not found"));
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

    @Override
    public Set<WagonClass> getWagonClassesForTrain(Train train) {
        List<Wagon> wagons = wagonDao.findWagonsByTrainId(train.getTrainId());
        Set<WagonClass> wagonClasses = new HashSet<>();
        wagons.forEach(wagon -> {
            wagonClasses.add(wagonTypeDao.findById(wagon.getWagonTypeId())
                    .orElseThrow(() -> new WagonTypeException("WagonType not found")).getWagonClass());
        });

        return wagonClasses;
    }
}
