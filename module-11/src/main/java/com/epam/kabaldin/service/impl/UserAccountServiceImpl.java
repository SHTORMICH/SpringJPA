package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserAccountDAO;
import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.model.impl.UserAccountImpl;
import com.epam.kabaldin.service.UserAccountService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    private UserAccountDAO userAccountDAO;

    @Override
    public UserAccount getUserAccountById(Long id) {
        Optional<UserAccountImpl> userAccountOp = userAccountDAO.findById(id);
        userAccountOp.isPresent();
        return userAccountOp.get();
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        userAccountDAO.save(((UserAccountImpl) userAccount));
        return true;
    }

    @Override
    public void refillAccount(Long userId, Long amount) {
        UserAccountImpl userAccount = userAccountDAO.findById(userId).get();
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney() + amount);
        userAccountDAO.save(userAccount);
    }
}
