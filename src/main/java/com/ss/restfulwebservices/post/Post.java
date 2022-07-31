package com.ss.restfulwebservices.post;

import com.ss.restfulwebservices.user.User;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String message;

    //use fetch= FetchType.LAZY to prevent recursion
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;

    public Post() {
    }

    public Post(Integer id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
