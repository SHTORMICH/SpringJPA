package com.epam.kabaldin.repository;

import com.epam.kabaldin.entity.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserSearchRepository extends CouchbaseRepository<User, Long> {

    @Query("#{#n1ql.selectEntity} WHERE SEARCH(user_search_index, $1)")
    List<User> findBySearchQuery(String query);
}
