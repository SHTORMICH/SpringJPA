package com.epam.kabaldin.service;

import com.epam.kabaldin.entity.Sport;
import com.epam.kabaldin.entity.User;
import com.epam.kabaldin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User addSportToUser(Long userId, Sport sport) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            if (user.getSports() == null) {
                user.setSports(new ArrayList<>());
            }
            user.getSports().add(sport);
            return userRepository.save(user);
        }
        return new User();
    }

    public List<User> getUsersBySportName(String sportName) {
        return userRepository.findBySportName(sportName);
    }
}
