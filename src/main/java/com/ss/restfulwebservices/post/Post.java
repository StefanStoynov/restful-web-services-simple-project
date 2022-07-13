package com.ss.restfulwebservices.post;

public class Post {
    private String message;

    public Post() {
    }

    public Post(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post='" + message + '\'' +
                '}';
    }
}
