package com.ss.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("id -" + id);
        }
        return user;
    }

    //retrieve userById
    //GET http://localhost:8080/users?id=1
    @RequestMapping(value = "/users", params = "id")
    //@RequestParam - used to get request parameter wit name id (?id=1)
    public User retrieveUserById(@RequestParam int id){
        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("id -" + id);
        }
        return user;
    }

    //retrieve userByName
    //GET http://localhost:8080/users?name=Stefan
    @RequestMapping(value = "/users", params = "name")
    public User retrieveUserByName(@RequestParam String name){
        User user = userService.findUserByName(name);
        if (user == null) {
            throw new UserNotFoundException("name -" + name);
        }
        return user;
    }


    //input - details of user
    //output - CREATED & Return the created URI
    //@RequestBody - what is pass from the request BODY will be mapped to User
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = userService.save(user);
        //build URI http://localhost:8080/users/4
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }

//    @GetMapping("/users/{name}")
//    public User retrieveUserByName(@PathVariable String name){
//        User user = userService.findUserByName(name);
//        return user;
//    }
}
