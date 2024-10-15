# Bond API 

## Overview

This is a Spring Boot-based API that allows you to manage bonds. You can create, retrieve, update, and delete bonds using this API. The API is documented using Swagger, and you can interact with it using the Swagger UI.

## Features

- Create new bonds with name, price, and interest rate.
- Retrieve all bonds or a specific bond by ID or name.
- Update bond details.
- Delete bonds by ID.
- Detailed error handling with appropriate HTTP status codes.

## Technologies

- **Spring Boot** - Backend framework
- **Java** - Programming language
- **Maven** - Dependency management
- **Lombok** - For reducing boilerplate code
- **Jakarta Validation** - Validation of DTOs
- **Swagger/OpenAPI** - API documentation
- **H2 Database (Optional)** - In-memory database for development/testing
- **Postman** - API testing
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework for unit tests
- **Docker (Optional)** - Containerization
- **Spring Data JPA** - Data access layer


## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Postman (for testing API requests)
- (Optional) Docker, if you wish to use a containerized database.
- (Optional) IDE (Eclipse, IntelliJ IDEA, etc.)
- (Optional) Lombok plugin for your IDE
- (Optional) H2 Database (if you don't want to use Docker)
- (Optional) Swagger UI (included in the project)
- (Optional) JUnit 5 and Mockito (for running tests)
- (Optional) Spring Data JPA (for data access)
- (Optional) Jakarta Validation (for validating DTOs)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/teamcubation/byma-sbx-bonos.git
cd bond-api
```

### 2. Build the Project
Use Maven to install dependencies and build the project:
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

By default, the application runs on http://localhost:8080. You can access the API via this URL.

### 4. Access Swagger UI
Once the application is running, you can view the API documentation and interact with it through the Swagger UI:
```bash
http://localhost:8080/swagger-ui/index.html
```


### 5. Sample API Requests
Here are some sample requests you can use to interact with the API via Postman:

1. Create a Bond (POST /api/bonds)

POST http://localhost:8080/api/bonds
```json
{
  "name": "Corporate Bond",
  "price": 1000.50,
  "interestRate": 2.75
}
```

2. Get All Bonds (GET /api/bonds)
 GET http://localhost:8080/api/bonds

3. Get Bond by ID (GET /api/bonds/{id})
 GET http://localhost:8080/api/bonds/1

4. Update Bond (PUT /api/bonds/{id})
    PUT http://localhost:8080/api/bonds/1
    ```json
    {
    "name": "Updated Corporate Bond",
    "price": 1050.75,
    "interestRate": 3.00
    }
    ```
   
5. Delete Bond (DELETE /api/bonds/{id})
    DELETE http://localhost:8080/api/bonds/1

Running Tests
To run unit tests and integration tests:
```bash
mvn test
```


