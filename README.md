# ToDo API

Spring Boot REST API for managing tasks in a simple ToDo application. The app exposes CRUD endpoints for tasks, stores data with Spring Data JPA, and can run with either MySQL or an in-memory H2 database depending on the active Spring profile.

## Features

- List all tasks
- Create a new task
- Update an existing task
- Delete a task
- Persist tasks with JPA/Hibernate
- MySQL configuration for deployment
- H2 configuration for local/test usage

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- H2 Database
- Maven

## Project Structure

```text
src/main/java/com/example/demo
+-- DemoApplication.java
+-- controller
|   +-- ToDoController.java
+-- model
|   +-- Task.java
+-- repository
    +-- TaskRepository.java

src/main/resources
+-- application.properties
+-- application-mysql.properties

src/test
+-- java/com/example/demo/DemoApplicationTests.java
+-- resources/application-test.properties
```

## Task Model

Each task contains:

| Field | Type | Description |
| --- | --- | --- |
| `id` | `Long` | Auto-generated task ID |
| `title` | `String` | Task title |
| `completed` | `boolean` | Completion status |

Example JSON:

```json
{
  "id": 1,
  "title": "Study Spring Boot",
  "completed": false
}
```

## API Endpoints

Base URL when running locally:

```text
http://localhost:8080
```

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/tasks` | Get all tasks |
| `POST` | `/tasks` | Create a task |
| `PUT` | `/tasks/{id}` | Update a task |
| `DELETE` | `/tasks/{id}` | Delete a task |

### Get All Tasks

```bash
curl http://localhost:8080/tasks
```

### Create Task

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Buy groceries\",\"completed\":false}"
```

### Update Task

```bash
curl -X PUT http://localhost:8080/tasks/1 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Buy groceries\",\"completed\":true}"
```

### Delete Task

```bash
curl -X DELETE http://localhost:8080/tasks/1
```

## Configuration

The default active profile is controlled by this line in `application.properties`:

```properties
spring.profiles.active=${SPRING_PROFILES_ACTIVE:mysql}
```

That means the application uses the `mysql` profile by default unless `SPRING_PROFILES_ACTIVE` is set to another value.

### MySQL Profile

The MySQL profile uses environment variables, with defaults configured in `application-mysql.properties`:

```properties
MYSQLHOST
MYSQLPORT
MYSQLDATABASE
MYSQLUSER
MYSQLPASSWORD
```

Run with MySQL:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

### H2 Local Profile

The base `application.properties` contains an H2 in-memory database configuration. To run locally with H2 instead of MySQL, set the active profile to something other than `mysql`, or remove/override the default profile.

PowerShell example:

```powershell
$env:SPRING_PROFILES_ACTIVE="local"
.\mvnw.cmd spring-boot:run
```

H2 console is enabled at:

```text
http://localhost:8080/h2-console
```

Use this JDBC URL:

```text
jdbc:h2:mem:todo_db
```

## Running Tests

The test profile uses H2 with `create-drop`.

```bash
./mvnw test
```

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## CORS

The controller currently allows requests from:

```text
https://reactinterface-production.up.railway.app
```

Update the `@CrossOrigin` value in `ToDoController.java` if the frontend URL changes.

## Diagram

A sequence diagram is available in [DIAGRAM.md](DIAGRAM.md). The exported diagram files are stored in the `docs` directory.

## Notes

- `PUT /tasks/{id}` currently returns `null` if the task is not found.
- `DELETE /tasks/{id}` returns the text response `Task deleted!`.
- Database tables are managed by Hibernate with `spring.jpa.hibernate.ddl-auto=update` for the main application profiles.
