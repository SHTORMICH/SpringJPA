package com.epam.kabaldin.service;

import com.epam.kabaldin.model.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    UserAccount getUserAccountById(Long id);
    boolean updateUserAccount(UserAccount userAccount);

    void refillAccount(Long userId, Long amount);
}
