package com.ss.restfulwebservices.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int id) {
        return postRepository.allPostsForUser(id);
    }

    @PostMapping("/users/{id}/posts")
    public Post createPostPostsForUser(@PathVariable int id, String massage) {
        return postRepository.createPostForUser(id, massage);
    }

    @GetMapping("/users/{id}/posts/{postId}")
    public String postDetails(@PathVariable int id, @PathVariable int postId) {
        return postRepository.postDetails(id, postId);
    }
}
