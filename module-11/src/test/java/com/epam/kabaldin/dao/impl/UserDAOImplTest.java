package com.epam.kabaldin.dao.impl;

import static org.junit.jupiter.api.Assertions.*;


import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImplTest {
    private final Map<String, List<? extends Entity>> storage = new HashMap<>();
    private List<User> userStorage;
    private UserDAOImpl userDAO;


    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOImpl();
        userStorage = new ArrayList<>();
        User user = new UserImpl();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");

        userStorage.add(user);
        storage.put("user", userStorage);
        userDAO.setStorage(storage);
        userDAO.init();
    }

    @Test
    public void saveUser_ShouldAddUserToStorage() {
        User user = new UserImpl();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");

        userDAO.saveUser(user);

        assertEquals(user.getId(), userDAO.getUserById(1L).getId());
        assertEquals(user.getName(), userDAO.getUserById(1L).getName());
        assertEquals(user.getEmail(), userDAO.getUserById(1L).getEmail());
    }

    @Test
    public void getUserById_ShouldReturnCorrectUser() {
        User user = new UserImpl();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        userDAO.saveUser(user);

        User retrievedUser = userDAO.getUserById(1L);

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    @Test
    public void getUserByEmail_ShouldReturnCorrectUser() {
        User user2 = new UserImpl();
        user2.setId(2L);
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        userDAO.saveUser(user2);

        User retrievedUser = userDAO.getUserByEmail("jane@example.com");

        assertEquals(userDAO.getUserById(2L), retrievedUser);
    }

    @Test
    public void getUsersByName_ShouldReturnMatchingUsers() {
        User user2 = new UserImpl();
        user2.setId(2L);
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        userDAO.saveUser(user2);

        List<User> users = userDAO.getUsersByName("John", 10, 1);

        assertEquals(1, users.size());
        assertTrue(users.contains(userDAO.getUserById(1L)));
        assertFalse(users.contains(user2));
    }

    @Test
    public void updateUser_ShouldUpdateExistingUser() {
        User user = new UserImpl();
        user.setId(2L);
        user.setName("John Smith");
        user.setEmail("johnSmith@example.com");
        userDAO.saveUser(user);

        user.setName("John Couch");
        user.setEmail("johnSmith@example.com");
        userDAO.updateUser(user);

        assertEquals(user, userDAO.getUserById(2L));
        assertEquals("John Doe", userDAO.getUserById(1L).getName());
    }

    @Test
    public void deleteUser_ShouldRemoveUserFromStorage() {
        boolean isDeleted = userDAO.deleteUser(1L);

        assertTrue(isDeleted);
    }
}
