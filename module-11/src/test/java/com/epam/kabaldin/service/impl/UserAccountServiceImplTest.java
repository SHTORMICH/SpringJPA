package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.UserAccountDAO;
import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.model.impl.UserAccountImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserAccountServiceImplTest {

    @Mock
    private UserAccountDAO userAccountDAO;

    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserAccountById() {
        Long accountId = 1L;
        UserAccount userAccount = new UserAccountImpl();
        when(userAccountDAO.findById(accountId)).thenReturn(Optional.of((UserAccountImpl) userAccount));

        UserAccount result = userAccountService.getUserAccountById(accountId);

        assertNotNull(result);
        assertEquals(userAccount, result);
    }

    @Test
    public void testUpdateUserAccount() {
        UserAccount userAccount = new UserAccountImpl();

        boolean result = userAccountService.updateUserAccount(userAccount);

        assertTrue(result);
        verify(userAccountDAO, times(1)).save((UserAccountImpl) userAccount);
    }

    @Test
    public void testRefillAccount() {
        Long userId = 1L;
        Long amount = 100L;
        UserAccountImpl userAccount = new UserAccountImpl();
        userAccount.setPrepaidMoney(200L);
        when(userAccountDAO.findById(userId)).thenReturn(Optional.of(userAccount));

        userAccountService.refillAccount(userId, amount);

        assertEquals(300L, userAccount.getPrepaidMoney());
        verify(userAccountDAO, times(1)).save(userAccount);
    }
}
