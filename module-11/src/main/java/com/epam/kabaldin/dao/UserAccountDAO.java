package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserAccountDAO extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findById(Long id);
    UserAccount getUserAccountById(Long id);
    void saveById(Long userId, Long amount);

}
