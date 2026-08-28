# My Greetings App

A Spring MVC REST API for creating, retrieving, updating, deleting, and
searching "greetings," backed by **PostgreSQL** via Spring's `JdbcTemplate`.
Packaged as a **WAR** file for deployment on a servlet container such as
Apache Tomcat.

## Features / API Endpoints

| Method | Endpoint                        | Description                                  |
|--------|----------------------------------|-----------------------------------------------|
| POST   | `/addgreeting`                  | Add a new greeting (JSON body: `userName`, `message`) |
| GET    | `/getgreeting/{id}`             | Get a greeting's message by ID               |
| PUT    | `/update/{id}`                  | Update a greeting's message by ID            |
| DELETE | `/delete/{id}`                  | Delete a greeting by ID                      |
| GET    | `/getAll`                       | Get all greetings                            |
| GET    | `/greetings/search?name={name}` | Search greetings by user name                |
| GET    | `/greetings/user/{name}`        | Get all greetings by a specific user         |

## Project Structure

```
MyGreetingsApp
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── bridgelabz
│       │           ├── Main.java
│       │           ├── model
│       │           │   └── Greeting.java
│       │           ├── DAO
│       │           │   └── GreetingDAO.java
│       │           ├── service
│       │           │   └── GreetingService.java
│       │           └── controller
│       │               └── GreetingController.java
│       ├── resources
│       │   └── ApplicationContext.xml
│       └── webapp
│           └── WEB-INF
│               ├── web.xml
│               └── dispatcher-servlet.xml
└── README.md
```

## Architecture

- **model.Greeting** — plain Java object representing a greeting (`id`, `userName`, `message`, `createdDate`).
- **DAO.GreetingDAO** — uses Spring's `JdbcTemplate` to run SQL against the `greetings` table (add, get by ID, get all, update, delete, search by name, get by user).
- **service.GreetingService** — thin service layer, `@Autowired` with `GreetingDAO`, called by the controller.
- **controller.GreetingController** — `@RestController` exposing the HTTP endpoints listed above.
- **ApplicationContext.xml** — configures the PostgreSQL `DataSource` and `JdbcTemplate` bean.
- **dispatcher-servlet.xml** — enables Spring MVC annotation scanning (`@RestController`, `@Service`, `@Repository`) and imports `ApplicationContext.xml`.
- **web.xml** — registers Spring's `DispatcherServlet` to handle all incoming requests (`/`).

> **Note:** `Main.java` is the default scratch class IntelliJ generates for new
> projects. It isn't part of the web application and isn't used to start the
> app — the app runs as a deployed WAR inside a servlet container instead.

## Requirements

- Java 17 or higher
- Maven
- PostgreSQL installed and running
- Apache Tomcat 10+ (or another Jakarta EE 10 compatible servlet container)

## Database Setup

1. Create a database named `greetingsdb`:
   ```sql
   CREATE DATABASE greetingsdb;
   ```

2. Create the `greetings` table:
   ```sql
   CREATE TABLE greetings (
       id           SERIAL PRIMARY KEY,
       user_name    VARCHAR(255) NOT NULL,
       message      VARCHAR(500) NOT NULL,
       created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

3. Update the datasource credentials in `src/main/resources/ApplicationContext.xml` to match your local PostgreSQL setup:
   ```xml
   <property name="url" value="jdbc:postgresql://localhost:5432/greetingsdb"/>
   <property name="username" value="your_postgres_username"/>
   <property name="password" value="your_postgres_password"/>
   ```

   > **Note:** Avoid committing real database credentials to a public repository.
   > Consider moving these to environment variables or a local, gitignored
   > config file for anything beyond local practice use.

## How to Run

### In IntelliJ IDEA

1. Open IntelliJ IDEA and choose **Open**, then select the project folder.
2. Let IntelliJ import the Maven project (it will read `pom.xml` automatically).
3. Make sure PostgreSQL is running and the `greetingsdb` database/table exist (see above).
4. Update `ApplicationContext.xml` with your own database credentials.
5. Configure a Tomcat run configuration (Run → Edit Configurations → add a Tomcat Server pointing at this project's artifact), or use a plugin such as Smart Tomcat.
6. Deploy and start the app, then hit the endpoints (see below).

### From the command line

Build the WAR file:
```
mvn clean package
```
The resulting `target/MyGreetingsApp-1.0-SNAPSHOT.war` can be deployed to a running Tomcat instance's `webapps` folder.

### Example requests (using curl)

```
curl -X POST http://localhost:8080/MyGreetingsApp/addgreeting ^
     -H "Content-Type: application/json" ^
     -d "{\"userName\":\"Nandini\",\"message\":\"Hello World\"}"

curl http://localhost:8080/MyGreetingsApp/getAll

curl http://localhost:8080/MyGreetingsApp/getgreeting/1

curl -X PUT http://localhost:8080/MyGreetingsApp/update/1 ^
     -H "Content-Type: application/json" ^
     -d "{\"message\":\"Updated message\"}"

curl -X DELETE http://localhost:8080/MyGreetingsApp/delete/1

curl "http://localhost:8080/MyGreetingsApp/greetings/search?name=Nandini"
```

(Adjust the context path/port if your Tomcat deployment differs.)
