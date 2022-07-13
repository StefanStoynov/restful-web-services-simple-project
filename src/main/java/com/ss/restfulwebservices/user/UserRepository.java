package com.ss.restfulwebservices.user;

import com.ss.restfulwebservices.post.Post;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {

        Post post1 = new Post("simple post1");
        Post post2 = new Post("simple post2");
        Post post3 = new Post("simple post3");
        Post post4 = new Post("simple post4");
        List<Post> user1Posts = new ArrayList<>();
        user1Posts.add(post1);
        user1Posts.add(post2);
        user1Posts.add(post3);
        List<Post> user2Posts = new ArrayList<>();
        user2Posts.add(post4);
        users.add(new User(1, "Stefan", new Date(), user1Posts));
        users.add(new User(2, "Plamen", new Date(), user2Posts));
        users.add(new User(3, "Simeon", new Date(), new ArrayList<>()));

    }

    public List<User> findAll() {
        return Collections.unmodifiableList(this.users);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        this.users.add(user);
        return user;
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        if (name.isEmpty() && name.isBlank()) {
            return null;
        }
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User deleteUserById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
