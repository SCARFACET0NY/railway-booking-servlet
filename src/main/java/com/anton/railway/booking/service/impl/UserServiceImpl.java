package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.exception.UserException;
import com.anton.railway.booking.repository.dao.UserDao;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;
import com.anton.railway.booking.service.UserService;
import com.anton.railway.booking.util.UpdatableBCrypt;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UpdatableBCrypt bCrypt;
    private final UserDao userDao;

    public UserServiceImpl(UpdatableBCrypt bCrypt, UserDao userDao) {
        this.bCrypt = bCrypt;
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public Long save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Long registerUser(String firstName, String lastName, String phone, String email, LocalDateTime dateJoined,
                             String cardNumber, String userName, String password, AccountStatus status) {

        User user = User.builder().firstName(firstName).lastName(lastName).phone(phone).email(email)
                .dateJoined(dateJoined).cardNumber(cardNumber).username(userName).password(bCrypt.hash(password))
                .accountStatus(status).build();

        return userDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return findById(userDao.checkUsername(username));
    }

    @Override
    public boolean verifyUser(String username, String password) {
        return userDao.checkUsername(username) > 0 && bCrypt.verifyHash(password, userDao.getPasswordForUsername(username));
    }
}
