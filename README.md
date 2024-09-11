```markdown
# Party Manager - Frontend

This is the frontend of the **Party Manager**, a full-stack CRUD application for managing parties and events. The project was built using **React.js**, **Vite**, **Ant Design**, and **Axios**. The frontend interacts with the backend via RESTful APIs for data management, such as party creation, editing, viewing, and deletion.

## Features

- View a list of all parties.
- Create new parties with detailed information.
- Edit party details including title, description, date, and time.
- Delete parties from the system.
- User authentication and session management using JWT.
- Responsive and user-friendly UI with **Ant Design** components.

## Tech Stack

- **React.js**: A JavaScript library for building user interfaces.
- **Vite**: A fast build tool that provides an efficient development environment.
- **Ant Design**: A comprehensive design system for building enterprise-level web apps.
- **Axios**: Promise-based HTTP client for making requests to the backend API.
- **React Router**: Handles routing and navigation between different views.

## Project Setup

### Prerequisites

- **Node.js** (v14 or higher)
- **npm** or **yarn**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/party-manager-frontend.git
   cd party-manager-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   # or
   yarn install
   ```

3. Create an `.env` file with the following contents:
   ```bash
   VITE_API_URL=http://localhost:3001  # URL to the backend API
   ```

4. Run the development server:
   ```bash
   npm run dev
   # or
   yarn dev
   ```

   This will start the frontend on `http://localhost:3000`.

### Deployment

To build for production:
```bash
npm run build
# or
yarn build
```

The built files will be located in the `dist/` folder, ready for deployment.

## Usage

Once running, the frontend will allow users to:

1. **Register/Login**: A user can register or log in using JWT-based authentication.
2. **View Parties**: The dashboard displays a list of all parties.
3. **Add Party**: Users can add a new party by filling in the required fields such as title, description, date, time, and location.
4. **Edit/Delete Party**: Each party has buttons to either edit or delete the party.

## Folder Structure

- `src/`: Contains all the React components and project files.
  - `components/`: Reusable UI components such as buttons and forms.
  - `pages/`: The main page components for routing (Home, Create Party, Edit Party, etc.).
  - `context/`: Contains the authentication context to manage user sessions.
  - `services/`: Axios configuration and API service files.
  - `App.jsx`: Main application entry point.
  - `routes/`: Route definitions using React Router.

## Contribution

This project was built solo over the course of 2 weeks with the objective of creating a full-stack CRUD application. Contributions or feedback are welcome!

---

## Author

- **Your Name**  
- [LinkedIn](https://www.linkedin.com/in/your-linkedin-profile)  
- [GitHub](https://github.com/your-username)

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

---

### 2. **README.md para el repositorio del Backend (Java Spring Boot, JWT, PostgreSQL)**

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

- **Java 11** or higher
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

- **Your Name**  
- [LinkedIn](https://www.linkedin.com/in/mercedes-celedon-perez/)  
- [GitHub]([https://github.com/your-username](https://github.com/Mercedes-Celedon))

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

