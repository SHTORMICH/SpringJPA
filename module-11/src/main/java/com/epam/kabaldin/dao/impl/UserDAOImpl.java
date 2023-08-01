package com.epam.kabaldin.dao.impl;

import com.epam.kabaldin.dao.UserDAO;
import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDAOImpl implements UserDAO {
    private List<User> userStorage;
    private Map<String, List<? extends Entity>> storage;

    public void init() {
        userStorage = (List<User>) storage.computeIfAbsent("user", k -> new ArrayList<>());
    }

    public void setStorage(Map<String, List<? extends Entity>> storage) {
        this.storage = storage;
    }

    @Override
    public void saveUser(User user) {
        userStorage.add(user);
    }

    @Override
    public User getUserById(long userId) {
        for (User user : userStorage) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userStorage.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> usersWithName = userStorage.stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());

        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, usersWithName.size());

        if (startIndex >= usersWithName.size()) {
            return Collections.emptyList();
        }

        return usersWithName.subList(startIndex, endIndex);
    }

    @Override
    public User updateUser(User user) {
        for (User userForUpdate : userStorage) {
            if (userForUpdate.getId() == user.getId()) {
                userForUpdate.setName(user.getName());
                userForUpdate.setEmail(user.getEmail());
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        for (User userForDelete : userStorage) {
            if (userForDelete.getId() == userId) {
                userStorage.remove(userForDelete);
                return true;
            }
        }
        return false;
    }
}

