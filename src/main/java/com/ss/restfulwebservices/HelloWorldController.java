package com.ss.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//tell the Spring that this class is Controller
@RestController
public class HelloWorldController {
    //GET
    //URI - /hello-world
    @GetMapping(path = "/hello-world")
    //method to return hello world
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }
}
