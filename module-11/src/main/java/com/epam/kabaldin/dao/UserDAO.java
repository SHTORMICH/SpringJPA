package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);
    User getUserById(long userId);
    User getUserByEmail(String email);
    List<User> getUsersByName(String name, int pageSize, int pageNum);
    User updateUser(User user);
    boolean deleteUser(long userId);
}
