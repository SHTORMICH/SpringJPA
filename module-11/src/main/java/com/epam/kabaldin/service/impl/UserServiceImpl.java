package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.UserImpl;
import com.epam.kabaldin.service.UserService;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<UserImpl> userOp = userDAO.findById(userId);
        userOp.isPresent();
        return userOp.get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return userDAO.findByName(name, pageable);
    }

    @Override
    public User createUser(User user) {
        userDAO.save(((UserImpl) user));
        return user;
    }

    @Override
    public User updateUser(User user) {
        userDAO.save(((UserImpl) user));
        return user;
    }

    @Override
    public boolean deleteUser(Long userId) {
        userDAO.deleteById(userId);
        return true;
    }
}

