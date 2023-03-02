#Book My Show
Book My Show is a web application for booking movie tickets online. It provides users with the convenience of browsing movies, selecting show timings, and booking tickets from the comfort of their homes.

Features:
Browse movies and view their details
View show timings for a movie
Book tickets for a show
Add new movies to the system
Add new show timings for a movie
Swagger UI for API documentation

Technologies Used:
Java
Spring Boot
Maven
Swagger UI
APIs
SQL Database
Spring Data JPA
Model Mapper
Mail Sender
Swagger UI

This project includes the following APIs:

/movies - GET: Get a list of all movies
/movies/{id} - GET: Get details of a movie by its ID
/movies - POST: Add a new movie
/movies/{id}/shows - GET: Get show timings for a movie by its ID
/shows - POST: Add a new show
/shows/{id}/book - POST: Book tickets for a show by its ID
/error - GET: Get a custom error message

Getting Started:
To run the application, clone the repository and run the following command:

Copy code
mvn spring-boot:run
This will start the application on localhost:8888.

To view the Swagger UI documentation, navigate to localhost:8888/swagger-ui.html.

Contributors
Mayur
