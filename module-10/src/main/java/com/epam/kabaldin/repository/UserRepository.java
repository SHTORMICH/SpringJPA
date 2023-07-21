package com.epam.kabaldin.repository;

import com.epam.kabaldin.entity.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CouchbaseRepository<User, Long> {

    @Query("#{#n1ql.selectEntity} WHERE email = $1")
    User findByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE ANY sport IN sports SATISFIES sport.sportName = $1 END")
    List<User> findBySportName(String sportName);
}
