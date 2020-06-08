package com.anton.railway.booking.service;

import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;

import java.time.LocalDateTime;

public interface UserService extends CrudService<User, Long> {
    Long registerUser(String firstName, String lastName, String phone, String email, LocalDateTime dateJoined,
                             Long cardNumber, String userName, String password, AccountStatus status);

    User getUserByUsername(String username);

    boolean verifyUser(String username, String password);
}
