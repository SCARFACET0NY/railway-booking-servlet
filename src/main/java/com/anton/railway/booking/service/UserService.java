package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.entity.User;

public interface UserService extends CrudService<User, Long> {
    User getUserByUsername(String username);

    boolean verifyUser(String username, String password);
}
