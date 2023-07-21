package com.epam.kabaldin.service;

import com.epam.kabaldin.entity.User;
import com.epam.kabaldin.repository.UserSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {
    private final UserSearchRepository userSearchRepository;

    @Autowired
    public UserSearchService(UserSearchRepository userSearchRepository) {
        this.userSearchRepository = userSearchRepository;
    }

    public List<User> searchUsers(String query) {
        return userSearchRepository.findBySearchQuery(query);
    }
}

