package com.kabaldin.socialmedia;

import com.kabaldin.socialmedia.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByUsername(String username);
}
