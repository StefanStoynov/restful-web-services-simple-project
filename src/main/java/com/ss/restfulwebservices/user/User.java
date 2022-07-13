package com.ss.restfulwebservices.user;

import com.ss.restfulwebservices.post.Post;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    @Size(min = 2, message = "minimum length of the name is two characters")
    private String name;

    //validation for birthday must be in the past
    @Past
    private Date birthDate;

    private List<Post> posts;

    protected User() {
    }

    public User(Integer id, String name, Date birthDate, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPosts(Post post) {
        this.posts.add(post);
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", posts: " + posts +
                '}';
    }
}
