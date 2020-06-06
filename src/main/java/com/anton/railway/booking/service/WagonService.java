package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.entity.Wagon;
import com.anton.railway.booking.repository.entity.enums.WagonClass;

import java.util.List;

public interface WagonService extends CrudService<Wagon, Long> {
    List<Wagon> findWagonsByClassAndTrainId(WagonClass wagonClass, Long trainId);
}
