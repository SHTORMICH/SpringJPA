package com.kabaldin.socialmedia;

import com.kabaldin.socialmedia.entity.Author;
import com.kabaldin.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Author author);
}
