package com.anton.railway.booking.repository.dao;

import com.anton.railway.booking.repository.entity.User;

public interface UserDao extends Dao<User, Long> {
    Long checkUsername(String username);

    String getPasswordForUsername(String username);
}
