# restful-web-services-simple-project
REST - REpresentational State Transfer
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
    - Retrieve all posts for a User - GET /users/{id}/posts
    - Create a posts for a User     - POST /users/{id}/posts
    - Retrieve details of a post    - GET /users/{id}/posts/{post_id}