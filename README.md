# Spring Boot REST Client

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-rest-client/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-rest-client/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

A comprehensive Spring Boot application demonstrating the use of Spring's modern RestClient for consuming REST APIs,
with full CRUD operations, database integration, and API documentation.

## Technologies Used

- **Java 25** - Latest LTS Java release
- **Spring Boot 4.1.1** - Spring Framework 7 based Spring Boot
- **Spring Data JPA** - Data persistence layer
- **Hibernate 7.4** - ORM framework
- **MySQL 9.1** - Database (via Docker Compose)
- **Spring RestClient** - Modern HTTP client (`spring-boot-starter-restclient`)
- **Jackson 3** - JSON (de)serialization (`tools.jackson`)
- **Lombok** - Reduces boilerplate code
- **SpringDoc OpenAPI 3.1.1** - API documentation (Swagger UI)
- **JUnit 5** - Unit and integration testing
- **Testcontainers 2.0** - Database testing with containers (`testcontainers-mysql`)
- **Docker Compose** - Container orchestration for MySQL

## Features

- Modern RestClient implementation for consuming external APIs
- Full CRUD operations for Employee and User entities
- RESTful API endpoints with proper HTTP methods
- Automatic database schema management with Hibernate
- Comprehensive unit tests with @RestClientTest
- Integration tests (disabled by default, requires running application)
- API documentation with Swagger UI
- Docker Compose integration for MySQL database
- Development tools with hot reload

## Prerequisites

- Java 25 (JDK 25) or higher
- Maven 3.9+ (or use the bundled Maven Wrapper `./mvnw`)
- Docker and Docker Compose (for database)

## Project Structure

```
src/main/java/id/my/hendisantika/springbootrestclient/
├── client/
│   ├── PostClient.java          # Client for consuming JSONPlaceholder API
│   └── RestClientService.java   # Service demonstrating RestClient usage
├── controller/
│   ├── EmployeeController.java  # Employee REST endpoints
│   └── UserController.java      # User REST endpoints
├── converter/
│   └── EmployeeConverter.java   # DTO/Entity converter
├── dto/
│   ├── EmployeeDto.java         # Employee data transfer object
│   └── Post.java                # Post DTO for external API
├── entity/
│   ├── Employee.java            # Employee JPA entity
│   └── User.java                # User JPA entity
├── repository/
│   ├── EmployeeJdbcRepository.java
│   ├── EmployeeRepository.java
│   └── UserRepository.java
├── service/
│   ├── EmployeeService.java
│   └── UserService.java
└── SpringBootRestClientApplication.java
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/hendisantika/spring-boot-rest-client.git
cd spring-boot-rest-client
```

### 2. Build the project

```bash
./mvnw clean install
```

### 3. Run the tests

```bash
./mvnw test
```

All unit tests should pass:

- PostClientTest - Tests for consuming external APIs
- EmployeeClientTest - Tests for RestClientService
- SpringBootRestClientApplicationTests - Context loading test

Note: Integration tests in `RestClientTest.java` are disabled by default as they require the application to be running.

### 4. Run the application

```bash
mvn spring-boot:run
```

The application will:

1. Start Docker Compose and create a MySQL container
2. Initialize the database schema automatically
3. Start the embedded Tomcat server on port 8080
4. Enable Swagger UI for API documentation

## API Endpoints

### Employee Endpoints

- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `POST /api/employees` - Create new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### User Endpoints

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## API Documentation

Once the application is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui
```

OpenAPI JSON specification is available at:

```
http://localhost:8080/v3/api-docs
```

## Example Usage

### Create Employee

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }'
```

### Get All Employees

```bash
curl http://localhost:8080/api/employees
```

### Update Employee

```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com"
  }'
```

### Delete Employee

```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

## RestClient Implementation

This project demonstrates Spring's modern `RestClient` API for consuming external REST services. The `PostClient` class
shows how to:

- Configure RestClient with base URLs
- Make GET requests to external APIs
- Parse JSON responses into Java objects
- Handle errors and exceptions

Example from `PostClient.java`:

```java

@Service
public class PostClient {
    private final RestClient restClient;

    public PostClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
```

## Database Configuration

The application uses Docker Compose to automatically start a MySQL container. Configuration is in `compose.yml`:

- **Database**: MySQL 9.1
- **Port**: 3307 (host) → 3306 (container)
- **Database Name**: rest_client
- **Username**: hendisantika
- **Password**: 53cret

Database schema is automatically created by Hibernate on startup.

## Testing

### Unit Tests

Run unit tests with:

```bash
mvn test
```

Unit tests use `@RestClientTest` to test REST client implementations without starting the full application context.

### Integration Tests

Integration tests in `RestClientTest.java` are disabled by default. To run them:

1. Start the application: `./mvnw spring-boot:run`
2. Remove the `@Disabled` annotation from `RestClientTest.java`
3. Run the tests: `mvn test`

## Configuration

Application properties can be configured in `src/main/resources/application.properties`:

- Server port
- Database connection
- Hibernate settings
- Logging levels
- API documentation settings

## Upgrading to Spring Boot 4 / JDK 25

Notable changes applied in this project:

- `java.version` is set to `25` and the compiler plugin uses `${java.version}`
- `spring-boot-starter-web` → `spring-boot-starter-webmvc`, plus `spring-boot-starter-restclient`
- `RestClientCustomizer` moved to `org.springframework.boot.restclient`
- `@RestClientTest` moved to `org.springframework.boot.restclient.test.autoconfigure`
  (requires `spring-boot-starter-restclient-test`)
- Jackson 3: `com.fasterxml.jackson.databind.ObjectMapper` → `tools.jackson.databind.ObjectMapper`
- Testcontainers 2.x renamed its modules: `junit-jupiter` → `testcontainers-junit-jupiter`,
  `mysql` → `testcontainers-mysql`

## Continuous Integration

GitHub Actions workflow [`.github/workflows/maven.yml`](.github/workflows/maven.yml) runs on every push / pull request
to `main` (and manually via *workflow_dispatch*):

1. Checkout the code
2. Set up Testcontainers Cloud (optional, uses the `TC_CLOUD_TOKEN` secret)
3. Set up **Temurin JDK 25** with Maven cache
4. Build & test with `mvn -B package`
5. Submit the Maven dependency graph (on `main` only; requires *Dependency graph* to be enabled in the repository
   settings)

## Dependency Updates (Dependabot)

[`.github/dependabot.yml`](.github/dependabot.yml) keeps dependencies up to date every Monday 21:00 (Asia/Jakarta):

| Ecosystem        | What is updated                  | Grouping                                                        |
|------------------|----------------------------------|-----------------------------------------------------------------|
| `maven`          | `pom.xml` dependencies & plugins | `spring-boot`, `springdoc`, `testcontainers`, `maven-plugins`, `minor-and-patch` |
| `github-actions` | Actions used in workflows        | single `github-actions` group                                   |
| `docker-compose` | Images in `compose.yml` (MySQL)  | -                                                               |

Pull requests are labelled (`dependencies`, `java`, `github-actions`, `docker`) and use conventional commit prefixes
(`build(deps)`, `ci(deps)`, `build(docker)`).

## Development

The project includes Spring DevTools for hot reload during development. Any changes to Java files will automatically
restart the application.

## Production Considerations

For production deployment:

1. Disable Swagger UI:
   ```properties
   springdoc.swagger-ui.enabled=false
   springdoc.api-docs.enabled=false
   ```

2. Configure proper database credentials
3. Enable security (not included in this demo)
4. Configure proper logging levels
5. Use production-grade database instead of Docker Compose

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34

## Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring RestClient Guide](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) - Free fake API for testing
