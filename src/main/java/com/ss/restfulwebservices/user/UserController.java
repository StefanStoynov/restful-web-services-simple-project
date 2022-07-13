package com.ss.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //retrieve all users
    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    //retrieve user(int id)
    //GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = userRepository.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("id -" + id);
        }
        return user;
    }

    //delete user by id (int id)
    //DELETE /users/{id}
    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userRepository.deleteUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id - " + id + " is not found");
        }
    }

    //retrieve userById
    //GET http://localhost:8080/users?id=1
    @RequestMapping(value = "/users", params = "id")
    //@RequestParam - used to get request parameter with name id (?id=1)
    public User retrieveUserById(@RequestParam int id){
        User user = userRepository.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("User with id - " + id + " is not found");
        }
        return user;
    }

    //retrieve userByName
    //GET http://localhost:8080/users?name=Stefan
    @RequestMapping(value = "/users", params = "name")
    public User retrieveUserByName(@RequestParam String name){
        User user = userRepository.findUserByName(name);
        if (user == null) {
            throw new UserNotFoundException("name -" + name);
        }
        return user;
    }


    //input - details of user
    //output - CREATED & Return the created URI
    //@RequestBody - what is pass from the request BODY tab will be mapped to User
    //@Valid - adds validation on user see validation on User class properties
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        //build URI http://localhost:8080/users/4
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }
}
