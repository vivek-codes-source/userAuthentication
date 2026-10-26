# User Service

A Java-based backend microservice built with Spring Boot that handles user registration, authentication, user management, and session handling. The service integrates with Spring Security and Apache Kafka to support secure application access and event-driven communication between microservices.

## Overview

User Service provides REST APIs for user-related operations, including signup, login, session management, and user data handling.

The application follows a layered architecture with controllers, services, repositories, DTOs, and models. It uses Spring Security for security configuration and Apache Kafka to publish user-related events, enabling asynchronous communication with other microservices.

## Features

- **User Registration:** Handles signup requests and user account creation.
- **User Authentication:** Provides login and authentication-related operations.
- **Spring Security:** Configures application security and access control.
- **User Management:** Handles user-related business logic and data access.
- **Session Management:** Maintains user session information and session status.
- **Role Management:** Supports the representation of user roles.
- **Kafka Integration:** Publishes user-related events for inter-service communication.
- **DTO-Based Architecture:** Uses Data Transfer Objects (DTOs) for requests, responses, and event payloads.
- **Repository Layer:** Separates data access logic from business logic.
- **Layered Architecture:** Organizes the application into controllers, services, repositories, models, and configuration.
- **Persistence:** Uses Spring Data JPA with a relational database, depending on the application configuration.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend application framework |
| Spring Web | REST API development |
| Spring Security | Application security and access control |
| Spring Data JPA | Database persistence |
| Relational Database | User and session data storage |
| Apache Kafka | Event-driven communication |

## Project Structure

```text
UserService/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/UserService/
│   │   │       ├── Controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   └── UserController.java
│   │   │       ├── Dtos/
│   │   │       │   ├── LoginRequestDto.java
│   │   │       │   ├── SignupRequestDto.java
│   │   │       │   ├── UserCreatedEvent.java
│   │   │       │   ├── UserDto.java
│   │   │       │   └── ValidateRequestDto.java
│   │   │       ├── models/
│   │   │       │   ├── BaseModel.java
│   │   │       │   ├── Role.java
│   │   │       │   ├── Session.java
│   │   │       │   ├── SessionStatus.java
│   │   │       │   └── User.java
│   │   │       ├── Repository/
│   │   │       │   ├── SessionRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       ├── security/
│   │   │       │   └── SpringSecurity.java
│   │   │       ├── Service/
│   │   │       │   ├── AuthService.java
│   │   │       │   ├── KafkaProducerService.java
│   │   │       │   └── UserService.java
│   │   │       └── UserServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

The Java packages contain the application's API controllers, authentication and user services, data transfer objects, domain models, repositories, security configuration, and Kafka integration components.

## Modules

### 1. Authentication Module

Handles user registration and login operations.

**Components:**

- `AuthController.java` — Handles authentication-related HTTP requests.
- `AuthService.java` — Implements authentication and signup business logic.
- `LoginRequestDto.java` — Represents login request data.
- `SignupRequestDto.java` — Represents signup request data.
- `ValidateRequestDto.java` — Represents validation request data.

### 2. User Management Module

Manages user-related operations and business logic.

**Components:**

- `UserController.java` — Exposes user-related API endpoints.
- `UserService.java` — Implements user management operations.
- `UserRepository.java` — Handles user data access.
- `UserDto.java` — Represents user data transferred between application layers or API boundaries.

### 3. Security Module

Uses Spring Security to configure application security and access rules.

**Component:**

- `SpringSecurity.java` — Defines the application's security configuration.

### 4. Session Management Module

Manages user session information and session status.

**Components:**

- `Session.java` — Represents a user session.
- `SessionStatus.java` — Defines possible session states.
- `SessionRepository.java` — Handles session-related data access.

### 5. Role Management Module

Supports the representation of user roles within the application.

**Component:**

- `Role.java` — Represents role-related information used by the application.

### 6. Kafka Integration Module

Enables event-driven communication between microservices using Apache Kafka.

**Components:**

- `KafkaProducerService.java` — Publishes messages to Kafka topics.
- `UserCreatedEvent.java` — Represents user creation event data.

This integration allows other microservices to consume user-related events without requiring direct synchronous communication with the User Service.

## Application Flow

The User Service follows a layered architecture to process requests and manage user-related operations.

1. The client sends an HTTP request to the appropriate REST controller.
2. The controller receives the request and delegates the operation to the relevant service.
3. The service layer executes the required business logic.
4. Repositories handle database operations when required.
5. The application returns an appropriate response to the client.
6. Relevant user events can be published to Kafka for communication with other microservices.

## Architecture Overview

```text
                 Client Application
                         |
                         v
                  REST Controllers
                    /         \
                   v           v
           AuthController  UserController
                   |           |
                   v           v
              AuthService   UserService
                    \         /
                     v       v
                    Repositories
                         |
                         v
                      Database


                AuthService / UserService
                         |
                         v
                KafkaProducerService
                         |
                         v
                    Kafka Topic
                         |
                         v
                Other Microservices
```

**Note:** This diagram represents the logical architecture described in this README. The actual implementation may differ.

## Getting Started

### Prerequisites

Make sure the following tools and services are available:

- Java Development Kit (JDK) compatible with the project configuration.
- Apache Maven.
- Git.
- A relational database, if required by the application.
- Apache Kafka, if Kafka integration is enabled.

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd UserService
```

Replace `<your-repository-url>` with the actual Git repository URL.

### 2. Configure the Application

Open the application configuration file:

```text
src/main/resources/application.properties
```

Configure the required application properties, including:

- Database connection URL, username, and password.
- Server port and application settings.
- Kafka bootstrap server configuration.
- Kafka producer settings.
- Other environment-specific properties.

Ensure that the configured database and Kafka broker are running before starting the application.

**Important:** Do not commit database passwords, credentials, API keys, or other sensitive information to version control.

### 3. Build the Project

Run the following command to compile the application and package the project:

```bash
mvn clean install
```

### 4. Run the Application

Start the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```


### 5. Run Tests

Execute the configured project tests:

```bash
mvn test
```

To build the project and run the verification lifecycle, use:

```bash
mvn clean verify
```

## Kafka Integration

Apache Kafka enables asynchronous communication between the User Service and other microservices.

When a user-related event is generated, the application can publish the event to a configured Kafka topic through `KafkaProducerService`.

Other microservices can consume these events and perform their respective operations independently.

**Example workflow:**

1. A client submits a signup request.
2. `AuthController` forwards the request to `AuthService`.
3. `AuthService` processes the registration and saves the user.
4. The application publishes a `UserCreatedEvent` through `KafkaProducerService`.
5. Kafka delivers the event to configured consumers.
6. Other microservices can process the event without requiring a direct synchronous call to the User Service.

The actual topic names, event payloads, and consumer behavior depend on the application configuration and the connected microservices.

## Security Considerations

- Configure authentication and authorization according to application requirements.
- Validate incoming requests and handle invalid input appropriately.
- Store credentials and secrets in environment variables or a secure configuration provider.
- Use HTTPS in production environments.
- Apply appropriate access controls to protected endpoints.
- Avoid logging passwords, authentication tokens, or sensitive user information.
- Configure session handling and expiration according to security requirements.
- Use secure database credentials and restrict database access.
- Secure Kafka communication and access when required by the deployment environment.

## Future Enhancements

- Implement centralized exception handling.
- Improve input validation and API response handling.
- Enhance authentication and authorization mechanisms.
- Add additional Kafka consumer integrations for event-driven workflows.


## Author

**Vivek Sahu**

GitHub: [vivek-codes-source](https://github.com/vivek-codes-source)

---

This project demonstrates hands-on work with Java backend development, Spring Boot, REST APIs, authentication, Spring Security, session management, relational database persistence, and event-driven microservice communication using Apache Kafka.
