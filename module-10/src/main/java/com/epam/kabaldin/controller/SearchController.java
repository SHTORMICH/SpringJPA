package com.epam.kabaldin.controller;

import com.epam.kabaldin.entity.User;
import com.epam.kabaldin.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search/user")
public class SearchController {
    private final UserSearchService userSearchService;

    @Autowired
    public SearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping
    public ResponseEntity<List<User>> searchUsers(@RequestParam("q") String query) {
        List<User> users = userSearchService.searchUsers(query);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

