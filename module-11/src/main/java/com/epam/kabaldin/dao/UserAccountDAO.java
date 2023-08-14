package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.model.impl.UserAccountImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserAccountDAO extends CrudRepository<UserAccountImpl, Long> {
    UserAccount save(UserAccount userAccount);
}
