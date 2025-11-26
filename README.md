# Spring Boot User API

A RESTful backend service built with Java 17, Spring Boot, PostgreSQL, JWT authentication, and Docker.  
This project demonstrates clean architecture, containerized deployment, and secure user management.

## Features
- User registration and login with JWT authentication
- Role-based access control
- CRUD operations for users
- PostgreSQL persistence with JPA/Hibernate
- Dockerized setup with docker-compose
- Secure password hashing with BCrypt

## Project Structure
springboot-user-api/
 ├── pom.xml
 ├── Dockerfile
 ├── docker-compose.yml
 ├── README.md
 ├── src/
 │   ├── main/
 │   │   ├── java/com/example/userapi/
 │   │   │   ├── UserApiApplication.java
 │   │   │   ├── entity/User.java
 │   │   │   ├── repository/UserRepository.java
 │   │   │   ├── dto/*.java
 │   │   │   ├── service/UserService.java
 │   │   │   ├── controller/*.java
 │   │   │   ├── security/*.java
 │   │   │   └── config/SecurityConfig.java
 │   │   └── resources/application.yml

## Setup and Run

### Build locally
mvn clean package

### Run with Docker
docker-compose up --build

This will start:
- PostgreSQL on port 5432
- Spring Boot API on port 8080

## Environment Variables
- JWT_SECRET → secret key for signing tokens
- SPRING_DATASOURCE_URL → database connection string
- SPRING_DATASOURCE_USERNAME → database username
- SPRING_DATASOURCE_PASSWORD → database password

Defaults are set in docker-compose.yml.

## API Endpoints

### Auth
- POST /api/auth/signup → Register new user  
  Example request:
  {
    "username": "john",
    "email": "john@example.com",
    "password": "secret123"
  }

- POST /api/auth/login → Authenticate and receive JWT  
  Example request:
  {
    "usernameOrEmail": "john",
    "password": "secret123"
  }

### Users (JWT required)
- GET /api/users → List all users
- GET /api/users/{id} → Get user by ID
- DELETE /api/users/{id} → Delete user

Include JWT in the Authorization header:
Authorization: Bearer <token>

## Testing
You can test endpoints using Postman or cURL. Example:
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <token>"
