```markdown
# Party Manager - Backend

This is the backend of the **Party Manager**, a full-stack CRUD application developed using **Java Spring Boot**. The backend provides RESTful API endpoints to manage user authentication, party creation, editing, viewing, and deletion. Authentication is handled using **JWT (JSON Web Token)**, and the data is stored in a **PostgreSQL** database.

## Features

- User authentication with JWT.
- CRUD operations for managing parties.
- Data persistence using PostgreSQL.
- RESTful API design.
- One-to-many relationship between **users** and **parties**.
- Backend structure following a **3-layer architecture** (Controller, Service, Repository).

## Tech Stack

- **Java Spring Boot**: A Java-based framework for building robust and scalable backend services.
- **Spring Security**: For authentication and authorization with JWT.
- **Spring Data JPA**: Provides the abstraction for database access and interaction with PostgreSQL.
- **PostgreSQL**: The relational database used for data persistence.
- **JWT**: Used for securing APIs by authenticating users.
- **Maven**: For project management and dependency handling.

## Project Setup

### Prerequisites

- **Java 17** or higher
- **Maven**
- **PostgreSQL**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/party-manager-backend.git
   cd party-manager-backend
   ```

2. Set up the PostgreSQL database:

   Create a PostgreSQL database called `party_manager_db` (or any other name) and configure the connection properties in the `application.properties` file:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/party_manager_db
   spring.datasource.username=your-username
   spring.datasource.password=your-password

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:3001`.

### API Endpoints

| Method | Endpoint            | Description                |
|--------|---------------------|----------------------------|
| POST   | `/auth/register`     | Registers a new user       |
| POST   | `/auth/login`        | Logs in and returns a JWT  |
| GET    | `/parties`           | Retrieves all parties      |
| GET    | `/parties/{id}`      | Retrieves a party by ID    |
| POST   | `/parties`           | Creates a new party        |
| PUT    | `/parties/{id}`      | Updates an existing party  |
| DELETE | `/parties/{id}`      | Deletes a party by ID      |

### Database Structure

The application uses two tables, `users` and `parties`, with a **one-to-many relationship**:

#### **users**

| Column   | Type   | Description        |
|----------|--------|--------------------|
| id       | int    | Primary Key        |
| name     | String | User's name        |
| email    | String | User's email       |
| password | String | User's password    |

#### **parties**

| Column     | Type   | Description          |
|------------|--------|----------------------|
| id         | int    | Primary Key          |
| title      | String | Title of the party   |
| location   | String | Party location       |
| description| String | Description of party |
| image_url  | String | URL of party image   |
| party_date | Date   | Date of the party    |
| start_time | Time   | Start time of party  |
| end_time   | Time   | End time of party    |
| user_id    | int    | Foreign Key (User)   |

### Folder Structure

- `src/main/java`: Contains the Java source code.
  - `controller/`: Contains the REST API controllers.
  - `service/`: Contains the business logic of the application.
  - `repository/`: Contains the data access logic.
  - `model/`: Contains the entity classes for `User` and `Party`.
  - `config/`: Spring Security configuration for JWT authentication.
  
### Contribution

This project was developed solo over the course of 2 weeks with the goal of building a full-stack CRUD application. Contributions and feedback are welcome!

---

## Author

- **Mercedes Celedon**  
- [LinkedIn](https://www.linkedin.com/in/mercedes-celedon-perez/)  
- [GitHub](https://github.com/Mercedes-Celedon)

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```
