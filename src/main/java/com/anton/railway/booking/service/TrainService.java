package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.entity.Train;
import com.anton.railway.booking.repository.entity.enums.WagonClass;

import java.util.Set;

public interface TrainService extends CrudService<Train, Long> {
    Set<WagonClass> getWagonClassesForTrain(Train train);
}
