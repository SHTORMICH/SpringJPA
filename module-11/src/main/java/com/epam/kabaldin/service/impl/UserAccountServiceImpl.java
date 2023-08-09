package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserAccountDAO;
import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {
    private UserAccountDAO userAccountDAO;

    @Override
    public Optional<UserAccount> getUserAccountById(Long id) {
        return userAccountDAO.findById(id);
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        userAccountDAO.save(userAccount);
        return true;
    }

    @Override
    public void refillAccount(Long userId, Long amount) {
        userAccountDAO.saveById(userId, amount);
    }
}
