package com.anton.railway.booking.service;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    ID save(T t);

    void delete(T t);

    void deleteById(ID id);
}
