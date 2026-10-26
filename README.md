# User Authentication Service

A backend authentication service built using **Java, Spring Boot, Spring Security, JWT, and MySQL** to provide secure user authentication through REST APIs.

## Overview

The User Authentication Service demonstrates how authentication can be implemented in a Java backend application. It uses Spring Security to manage access control and JSON Web Tokens (JWT) to support token-based authentication.

The service is designed to provide an authentication layer that can be integrated with other backend applications.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot
- **Security:** Spring Security
- **Authentication:** JSON Web Token (JWT)
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **API Style:** REST

## Features

- REST APIs for user authentication.
- JWT-based token authentication.
- Spring Security integration.
- MySQL database integration for user data.
- Role-based access control, if configured.
- Protected endpoints for authenticated requests.
- Structured backend architecture using Spring Boot.

## Architecture

```text
Client
  |
  v
Authentication Controller
  |
  v
Authentication Service
  |
  +------> User Repository ------> MySQL
  |
  v
Spring Security + JWT
  |
  v
Authentication Response
```

## Getting Started

### Prerequisites

- Java JDK 17 or compatible version
- Maven
- MySQL Server
- IntelliJ IDEA or another Java IDE

### 1. Clone the Repository

```bash
git clone https://github.com/vivek-codes-source/userAuthentication.git
cd userAuthentication
```

### 2. Configure the Database

Create a MySQL database for the application.

```sql
CREATE DATABASE user_authentication;
```

Configure the database connection in `src/main/resources/application.properties` according to your local MySQL setup.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_authentication
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Use the database name and property names expected by your actual application.

### 3. Configure JWT

Set the JWT secret and other required security properties using environment variables or your application's configuration.

Do not commit real secrets, passwords, or production credentials to GitHub.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, build and run the JAR:

```bash
mvn clean package
java -jar target/<application-jar>.jar
```

Replace `<application-jar>` with the actual generated JAR filename.

## API Testing

Test the authentication endpoints using Postman or another REST API client.

The exact endpoints and request bodies depend on the controller implementation.

Typical authentication workflows include:

1. Submit user credentials.
2. Validate credentials through the authentication layer.
3. Generate a JWT after successful authentication.
4. Send the token with requests to protected endpoints.
5. Validate the token before granting access to secured resources.

## Security Concepts

This project provides an opportunity to understand:

- Authentication vs. authorization
- Spring Security filter chain
- JWT generation and validation
- Stateless authentication
- Securing REST endpoints
- Database-backed user authentication

## Future Enhancements

- Refresh token support
- Password reset and email verification
- Improved exception handling and validation
- Unit and integration testing
- Docker-based deployment
- API documentation using Swagger / OpenAPI

## Author

**Vivek Kumar Sahu**

GitHub: [vivek-codes-source](https://github.com/vivek-codes-source)

---

Built as part of my journey toward developing secure Java backend applications using Spring Boot and modern authentication practices.
