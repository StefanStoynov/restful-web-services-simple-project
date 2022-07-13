package com.ss.restfulwebservices.post;

import com.ss.restfulwebservices.user.User;
import com.ss.restfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PostRepository {

    @Autowired
    private UserRepository userRepository;

    //retrieve all posts for a user
    public List<Post> allPostsForUser(int userId){
        return Collections.unmodifiableList(userRepository.findUser(userId).getPosts());
    }

    //create a post for a user
    public Post createPostForUser(int userId, String postMessage){
        Post post = new Post(postMessage);
        User user = this.userRepository.findUser(userId);
        if(user != null) {
          user.setPosts(post);
          return post;
      }
        return null;
    }

    //retrieve details of a post
    public String postDetails (int userId, int postId){
        try{
            User user = this.userRepository.findUser(userId);
            return user.getPosts().get(postId).getMessage();
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
