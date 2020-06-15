package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.UserDao;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;
import com.anton.railway.booking.service.UserService;
import com.anton.railway.booking.util.UpdatableBCrypt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserServiceImplTest {
    private static final long ID = 1L;
    private static final String USERNAME = "anton";
    private static final String PASSWORD = "pass";
    private static final List<User> users = new ArrayList<>();
    private static final User user = new User();
    @Mock
    UpdatableBCrypt bCrypt;
    @Mock
    UserDao userDao;
    UserService userService;

    @BeforeAll
    static void beforeAll() {
        User user1 = new User();
        user.setUserId(ID);
        user.setUsername(USERNAME);

        users.add(user);
        users.add(user1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(bCrypt, userDao);
    }

    @Test
    void findAllTest() {
        when(userDao.findAll()).thenReturn(users);
        List<User> returnedUsers = userService.findAll();

        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
        assertEquals(users, returnedUsers);

        verify(userDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        User returnedUser = userService.findById(ID);

        assertNotNull(returnedUser);
        assertEquals(user, returnedUser);
        assertEquals(user.getUserId(), returnedUser.getUserId());
        assertEquals(user.getUsername(), returnedUser.getUsername());

        verify(userDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(userDao.save(any(User.class))).thenReturn(ID);

        assertEquals(userService.save(user), ID);

        verify(userDao).save(any(User.class));
    }

    @Test
    void deleteTest() {
        userService.delete(user);

        verify(userDao).delete(any(User.class));
    }

    @Test
    void deleteByIdTest() {
        userService.deleteById(ID);

        verify(userDao).deleteById(anyLong());
    }

    @Test
    void registerUserTest() {
        when(userDao.save(any(User.class))).thenReturn(ID);

        Long id = userService.registerUser(null, null, null, null,
                LocalDateTime.now(), 0L, null, null, AccountStatus.CUSTOMER);

        assertEquals(ID, id);
        verify(userDao).save(any(User.class));
    }

    @Test
    void getUserByUsernameTest() {
        when(userDao.checkUsername(anyString())).thenReturn(ID);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));

        User returnedUser = userService.getUserByUsername(USERNAME);

        assertNotNull(returnedUser);
        assertEquals(user, returnedUser);
        assertEquals(user.getUserId(), returnedUser.getUserId());
        assertEquals(user.getUsername(), returnedUser.getUsername());

        verify(userDao).findById(anyLong());
        verify(userDao).checkUsername(anyString());
    }

    @Test
    void verifyUserTest() {
        when(userDao.checkUsername(USERNAME)).thenReturn(ID);
        when(userDao.getPasswordForUsername(USERNAME)).thenReturn(PASSWORD);
        when(bCrypt.verifyHash(PASSWORD, PASSWORD)).thenReturn(true);

        assertTrue(userService.verifyUser(USERNAME, PASSWORD));
        assertFalse(userService.verifyUser(USERNAME, USERNAME));
        assertFalse(userService.verifyUser(PASSWORD, PASSWORD));

        verify(userDao, times(3)).checkUsername(anyString());
        verify(userDao, times(2)).getPasswordForUsername(anyString());
        verify(bCrypt, times(2)).verifyHash(anyString(), anyString());
    }
}