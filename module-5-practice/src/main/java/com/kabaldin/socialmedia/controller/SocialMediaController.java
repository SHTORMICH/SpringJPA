package com.kabaldin.socialmedia.controller;

import com.kabaldin.socialmedia.entity.Author;
import com.kabaldin.socialmedia.AuthorRepository;
import com.kabaldin.socialmedia.entity.Post;
import com.kabaldin.socialmedia.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialMediaController {
    @Autowired
    private AuthorRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/users")
    public Author createUser(@RequestBody Author user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<Author> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
