package com.epam.kabaldin.service;

import com.epam.kabaldin.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long userId);
    public User getUserByEmail(String email);
    public List<User> getUsersByName(String name, int pageSize, int pageNum);
    public User createUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(Long userId);
}
