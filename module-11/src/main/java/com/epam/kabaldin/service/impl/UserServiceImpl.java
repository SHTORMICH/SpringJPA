package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(long userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDAO.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        userDAO.saveUser(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userDAO.updateUser(user);
        return user;
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDAO.deleteUser(userId);
    }
}

