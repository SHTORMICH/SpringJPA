package com.kabaldin.socialmedia;

import com.kabaldin.socialmedia.controller.SocialMediaController;
import com.kabaldin.socialmedia.entity.Author;
import com.kabaldin.socialmedia.entity.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(SocialMediaController.class)
public class SocialMediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private PostRepository postRepository;

    @Test
    public void createUser_ReturnsUser() throws Exception {
        Author user = new Author();
        user.setUsername("john_doe");

        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john_doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void getAllUsers_ReturnsListOfUsers() throws Exception {
        Author user1 = new Author();
        user1.setUsername("john_doe");

        Author user2 = new Author();
        user2.setUsername("jane_smith");

        List<Author> userList = Arrays.asList(user1, user2);

        Mockito.when(authorRepository.findAll()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(userList.size()));
    }

    @Test
    public void createPost_ReturnsPost() throws Exception {
        Author user = new Author();
        user.setUsername("john_doe");

        Post post = new Post();
        post.setTitle("My First Post");
        post.setBody("Hello, world!");
        post.setAuthor(user);

        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"My First Post\", \"body\":\"Hello, world!\", \"author\":{\"username\":\"john_doe\"}}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("My First Post"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Hello, world!"));
    }

    @Test
    public void getAllPosts_ReturnsListOfPosts() throws Exception {
        Author user = new Author();
        user.setUsername("john_doe");

        Post post1 = new Post();
        post1.setTitle("Post 1");
        post1.setBody("This is post 1");
        post1.setAuthor(user);

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setBody("This is post 2");
        post2.setAuthor(user);

        List<Post> postList = Arrays.asList(post1, post2);

        Mockito.when(postRepository.findAll()).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(postList.size()));
    }
}
