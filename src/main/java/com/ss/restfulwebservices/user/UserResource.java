package com.ss.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    //retrieve all users
    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    //retrieve user(int id)
    //GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return userService.findUser(id);
    }

    //input - details of user
    //output - CREATED & Return the created URI
    //@RequestBody - what is pass from the request body will be mapped to User
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = userService.save(user);

    }
}
