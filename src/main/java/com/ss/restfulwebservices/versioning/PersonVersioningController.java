package com.ss.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Amador Rivas");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2 (){
        return new PersonV2(new Name("Antonio", "Resio"));
    }

    //another way of versioning is to use request param

    //http://localhost:8080/person/param?versioning=1
    @GetMapping(value = "/person/param", params = "versioning=1")
    public PersonV1 personParamV1(){
        return new PersonV1("Amador Rivas");
    }

    //http://localhost:8080/person/param?versioning=2
    @GetMapping(value = "/person/param", params = "versioning=2")
    public PersonV2 personParamV2 (){
        return new PersonV2(new Name("Antonio", "Resio"));
    }

    //another way of versioning is to use header

    //http://localhost:8080/person/header
    //when we call this endpoint we have to set into Postman Header: X_API_VERSION, and Value: 1
    @GetMapping(value = "/person/header", headers = "X_API_VERSION=1")
    public PersonV1 personHeaderV1(){
        return new PersonV1("Amador Rivas");
    }

    //http://localhost:8080/person/header
    //when we call this endpoint we have to set into Postman Header: X_API_VERSION, and Value: 2
    @GetMapping(value = "/person/header", headers = "X_API_VERSION=2")
    public PersonV2 personHeaderV2 (){
        return new PersonV2(new Name("Antonio", "Resio"));
    }

    //another way of versioning is to use produces

    //http://localhost:8080/person/header
    //when we call this endpoint we have to set into Postman as key Accept and value of the key : application/vnd.company.app-v1+json
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProducesV1(){
        return new PersonV1("Amador Rivas");
    }

    //http://localhost:8080/person/header
    //when we call this endpoint we have to set into Postman as key Accept and value of the key : application/vnd.company.app-v2+json
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personProducesV2 (){
        return new PersonV2(new Name("Antonio", "Resio"));
    }


}
