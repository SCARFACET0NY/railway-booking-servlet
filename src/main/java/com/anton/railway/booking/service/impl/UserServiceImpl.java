package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.UserDao;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.service.UserService;
import com.anton.railway.booking.util.UpdatableBCrypt;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UpdatableBCrypt bCrypt;

    public UserServiceImpl(UserDao userDao, UpdatableBCrypt bCrypt) {
        this.userDao = userDao;
        this.bCrypt = bCrypt;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
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
    public User getUserByUsername(String username) {
        return findById(userDao.checkUsername(username));
    }

    @Override
    public boolean verifyUser(String username, String password) {
        return userDao.checkUsername(username) > 0 && bCrypt.verifyHash(password, userDao.getPasswordForUsername(username));
    }
}
