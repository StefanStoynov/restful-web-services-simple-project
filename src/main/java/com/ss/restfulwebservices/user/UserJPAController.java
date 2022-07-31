package com.ss.restfulwebservices.user;

import com.ss.restfulwebservices.post.Post;
import com.ss.restfulwebservices.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private PostRepository postRepository;


    //retrieve all users
    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userJPARepository.findAll();
    }

    //EntityModel<User> depend on dependency hateoas
    //with this method we are adding additional link to all users
    @GetMapping("/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int id){
        Optional<User> user = userJPARepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id -" + id);
        }
         EntityModel<Optional<User>> model = EntityModel.of(user);
        //link to all users
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        //add link to model
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    //delete user by id (int id)
    //DELETE /users/{id}
    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userJPARepository.deleteById(id);
    }

    //retrieve userById
    //GET http://localhost:8080/users?id=1
    @RequestMapping(value = "/users", params = "id")
    //@RequestParam - used to get request parameter with name id (?id=1)
    public User retrieveUserById(@RequestParam int id){
        Optional<User> user = userJPARepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id - " + id + " is not found");
        }
        return user.get();
    }

    //input - details of user
    //output - CREATED & Return the created URI
    //@RequestBody - what is pass from the request BODY tab will be mapped to User
    //@Valid - adds validation on user see validation on User class properties
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userJPARepository.save(user);
        //build URI http://localhost:8080/users/4
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userJPARepository.findById(id);

        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User with id - " + id + " is not found");
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsOfUser(@PathVariable int id){
        Optional<User> user = userJPARepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("User with id - " + id + " is not found");
        }
        return  user.get().getPosts();
    }
}
