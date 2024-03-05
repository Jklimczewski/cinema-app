# Cinema-App

## :notebook: Description

I've made this project during my last year of studies while learning the `Spring Boot` framework. As a user inteface and template engine I used `Thymeleaf`.

### 🧰 Architecture

- everything written in Java, managed by Spring Boot and connected to `PostgreSQL` database
- implementation of `Spring Boot security` in version 6
- mapping from / to DTOs with the help of [MapStruct](https://mapstruct.org/) library
- amazingly looking Thymeleaf templates thanks to [Tailwind](https://tailwindcss.com/)
- and of course some good old `Lombok` annotations

## :star: Main features

- user register / login using email and password authentication and implementing the `UserDetailsService` interface
- auth and cinema `Controllers` in order to let the user authenticate and interact with the server
  - make a reservation from the cinema schedule
  - browse the shopping cart
  - update / delete or finalize the reservation process
- additionally CRUD operations on most classes through endpoints of `RestContollers` and visualized with [Postman](https://www.postman.com/) in [this](src/main/resources) directory
- every entity action seperated inside layers ( `Repository` -> `Service` -> `Dto` -> `Controller` )
- API documentation available with [Swagger](https://swagger.io/) at `/swagger-ui/index.html`
