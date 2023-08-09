package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userDAO.findById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        Pageable pageable = (Pageable) PageRequest.of(pageNum, pageSize);
        return userDAO.findByName(name, pageable);
    }

    @Override
    public User createUser(User user) {
        userDAO.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userDAO.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(Long userId) {
        userDAO.deleteById(userId);
        return true;
    }
}

