package com.anton.railway.booking.repository.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();

    ID save(T t);

    void deleteById(ID id);

    void delete(T t);
}
