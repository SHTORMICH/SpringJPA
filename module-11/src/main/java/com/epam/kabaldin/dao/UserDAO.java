package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByName(String name, Pageable pageable);

}
