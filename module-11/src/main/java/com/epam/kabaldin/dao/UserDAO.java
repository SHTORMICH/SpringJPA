package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.UserImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<UserImpl, Long> {
    User save (User user);
    User findByEmail(String email);
    List<User> findByName(String name, Pageable pageable);

}
