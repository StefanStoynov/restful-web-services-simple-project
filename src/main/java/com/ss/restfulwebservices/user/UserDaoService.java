package com.ss.restfulwebservices.user;

import antlr.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Stefan", new Date()));
        users.add(new User(2, "Plamen", new Date()));
        users.add(new User(3, "Simeon", new Date()));
    }

    public List<User> findAll(){
        return Collections.unmodifiableList(this.users);
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        this.users.add(user);
        return user;
    }

    public User findUser(int id){
        for (User user : users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name){
        if (name.isEmpty() && name.isBlank()){
            return null;
        }
        for (User user : users) {
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

}
