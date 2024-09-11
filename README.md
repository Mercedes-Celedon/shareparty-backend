---

### Backend README (Java, Spring Boot, Security, JPA, JWT, PostgreSQL)

```markdown
# Party Management Backend

This repository contains the backend of the Party Management system, developed using **Java Spring Boot**. The backend provides a secure REST API for managing parties and users, including authentication and authorization via **JWT (JSON Web Token)**. The database used is **PostgreSQL** with two main tables: **users** and **parties**.

## Project Overview

The backend supports all the necessary CRUD operations for the Party Management system and handles user authentication and authorization. The app is built with a layered architecture, separating concerns between the controller, service, and repository layers.

- **Technology Stack:**
  - **Java 17+**
  - **Spring Boot** for building the backend.
  - **Spring Security** with JWT for authentication and authorization.
  - **JPA/Hibernate** for database interactions.
  - **PostgreSQL** as the relational database.
  - **Maven** for dependency management.

---

## Features

- **JWT Authentication:** Secure login and token-based access control.
- **CRUD Operations:** Create, read, update, and delete parties and users.
- **Validation:** Input validation for both user and party data.
- **Database:** Two main tables, `users` and `parties`, with a one-to-many relationship.
- **Role-based Access:** Only authenticated users can manage their parties.

---

## Installation & Setup

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Steps to run the project locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/party-management-backend.git
   cd party-management-backend
2. **Configure the database:**
   In the application.properties or application.yml file, update the PostgreSQL connection details:
    spring.datasource.url=jdbc:postgresql://localhost:5432/party_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
3. **Build the project:**
   mvn clean install
4. **Run the application:**
   mvn spring-boot:run
5. **Access the API:**
   The API will be running at http://localhost:3001

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

- **Your Name**  
- [LinkedIn](https://www.linkedin.com/in/mercedes-celedon-perez/)  
- [GitHub]([https://github.com/your-username](https://github.com/Mercedes-Celedon))

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
