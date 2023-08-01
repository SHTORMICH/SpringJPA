package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User expectedUser = new UserImpl();
        expectedUser.setId(userId);
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john.doe@example.com");

        when(userDAO.getUserById(userId)).thenReturn(expectedUser);

        User retrievedUser = userService.getUserById(userId);

        verify(userDAO, times(1)).getUserById(userId);

        assertEquals(expectedUser, retrievedUser);
    }

    @Test
    void testGetUserByEmail() {
        String email = "john.doe@example.com";
        User expectedUser = new UserImpl();
        expectedUser.setId(1L);
        expectedUser.setName("John Doe");
        expectedUser.setEmail(email);

        when(userDAO.getUserByEmail(email)).thenReturn(expectedUser);

        User retrievedUser = userService.getUserByEmail(email);

        verify(userDAO, times(1)).getUserByEmail(email);

        assertEquals(expectedUser, retrievedUser);
    }

    @Test
    void testGetUsersByName() {
        String name = "John Doe";
        int pageSize = 10;
        int pageNum = 1;
        List<User> expectedUsers = new ArrayList<>();

        when(userDAO.getUsersByName(name, pageSize, pageNum)).thenReturn(expectedUsers);

        List<User> retrievedUsers = userService.getUsersByName(name, pageSize, pageNum);

        verify(userDAO, times(1)).getUsersByName(name, pageSize, pageNum);

        assertEquals(expectedUsers, retrievedUsers);
    }

    @Test
    void testCreateUser() {
        User user = new UserImpl();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        User createdUser = userService.createUser(user);

        verify(userDAO, times(1)).saveUser(user);

        assertEquals(user, createdUser);
    }

    @Test
    void testUpdateUser() {
        User user = new UserImpl();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        User updatedUser = userService.updateUser(user);

        verify(userDAO, times(1)).updateUser(user);

        assertEquals(user, updatedUser);
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;

        when(userDAO.deleteUser(userId)).thenReturn(true);

        boolean result = userService.deleteUser(userId);

        verify(userDAO, times(1)).deleteUser(userId);

        assertEquals(true, result);
    }

}
