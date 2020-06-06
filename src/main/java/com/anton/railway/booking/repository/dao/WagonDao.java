package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.Wagon;

import java.util.List;

public interface WagonDao extends Dao<Wagon, Long> {
    List<Wagon> findWagonsByTrainId(Long id);
}
