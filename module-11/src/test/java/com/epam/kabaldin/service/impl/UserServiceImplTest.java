package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new UserImpl();
        when(userDAO.findById(userId)).thenReturn(Optional.of((UserImpl) user));

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new UserImpl();
        when(userDAO.findByEmail(email)).thenReturn(user);

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testGetUsersByName() {
        String name = "Test User";
        int pageSize = 10;
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<User> users = new ArrayList<>();
        when(userDAO.findByName(name, pageable)).thenReturn(users);

        List<User> result = userService.getUsersByName(name, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(users, result);
    }

    @Test
    public void testCreateUser() {
        User user = new UserImpl();

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new UserImpl();

        User result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        boolean result = userService.deleteUser(userId);

        assertTrue(result);
        verify(userDAO, times(1)).deleteById(userId);
    }
}
