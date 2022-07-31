# restful-web-services-simple-project
REST - REpresentational State Transfer<br>
REST is a style of software architecture for distributed hypermedia systems

#Description of the project:

Social Media Application

one  -> many
User -> Posts

In this project we will implement following RESTful Web Services:
    - Retrieve all Users            - GET /users
    - Create a User                 - POST /users
    - Retrieve one User             - GET /users/{id} -> /users/1
    - Delete a User                 - DELETE /users/{id} -> /users/1
    - Retrieve all messages for a User - GET /users/{id}/messages
    - Create a messages for a User     - POST /users/{id}/messages
    - Retrieve details of a message    - GET /users/{id}/messages/{post_id}

#Questions and Answers <br>
  - What is dispatcher servlet? -> Knows all about the mappings
  - Who is configuring dispatcher servlet? -> Spring boot
  - What does dispatcher servlet do? -> Handles all the requests and returns response
  - How does the HelloWorldBean object get converted to Json? -> Jackson bean
  - Who is configuring the error mapping? -> Spring boot autoconfiguration

#Error Handling <br>
see folder exception

#Swagger
when we use hateoas there is additional dependency<br>
documentation - https://springdoc.org/<br>
localhost url - http://localhost:8080/swagger-ui/index.html<br>

#Hal Explorer
provides a visualization for actuator, works with <b>hateoas dependency<b> <br>
localhost:8080 <br>
http://localhost:8080/explorer/index.html#uri=/actuator