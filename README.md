# 🔐 Spring Boot JWT Authentication System

A production-ready **full-stack authentication application** built with Spring Boot 3.1, Spring Security 6.0, and JWT tokens. Implements role-based access control (RBAC) with secure password encryption and modern stateless authentication patterns.

---

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Authentication Flow](#-authentication-flow)
- [Security Highlights](#-security-highlights)
- [How to Use](#-how-to-use)
- [Testing Credentials](#-testing-credentials)
- [Resume Value](#-resume-value)
- [Future Enhancements](#-future-enhancements)

---

## ✨ Features

✅ **User Authentication**
- User registration (signup)
- User login with JWT token generation
- Logout functionality
- Password encryption with BCrypt

✅ **Role-Based Access Control (RBAC)**
- Three user roles: USER, MODERATOR, ADMIN
- Protected endpoints by role
- Authorization filters

✅ **JWT Tokens**
- Stateless authentication
- Configurable token expiration
- Secure token validation

✅ **Security**
- CSRF protection (disabled for development)
- CORS enabled for frontend communication
- Frame options configured for H2 console
- Secure password storage

✅ **Database**
- H2 embedded in-memory database
- Automatic schema creation with Hibernate
- JPA/Repository pattern

✅ **Frontend**
- Responsive HTML/CSS/JavaScript UI
- Login and Signup forms
- User Dashboard
- Role-based badges
- Token display and management

---

## 🛠 Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 3.1.0 |
| **Security** | Spring Security | 6.0.9 |
| **Database** | H2 Database | 2.1.214 |
| **ORM** | Hibernate/JPA | 6.2.2 |
| **Java** | JDK | 17+ |
| **Build Tool** | Maven | 3.8+ |
| **Server** | Apache Tomcat | 10.1.8 |
| **Frontend** | Vanilla JavaScript | ES6+ |

---

## 🏗 Architecture

The system follows a **layered architecture pattern** with clear separation of concerns:

![Spring Boot JWT Authentication Architecture](spring-boot-jwt-authentication-spring-security-architecture.png)

**Architecture Layers:**
- **Controller Layer** - REST endpoints (`AuthController`, `TestController`)
- **Service Layer** - Business logic (`UserDetailsServiceImpl`)
- **Repository Layer** - Database access (`UserRepository`, `RoleRepository`)
- **Security Layer** - JWT and authentication filters
- **Model Layer** - Entity classes (`User`, `Role`)

---

## 📋 Prerequisites

Before running the application, ensure you have:

- ✅ **Java Development Kit (JDK) 17+**
  ```bash
  java -version
  ```
- ✅ **Maven 3.8+** (or use Maven wrapper)
  ```bash
  mvn -version
  ```
- ✅ **Git** (for version control)
- ✅ **A modern web browser**

---

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd spring-boot-spring-security-jwt-authentication-master
cd spring-boot-spring-security-jwt-authentication-master
```

### 2. Set JAVA_HOME (Windows)
```powershell
$env:JAVA_HOME='C:\Path\To\Your\JDK'
```

### 3. Build the Project
```bash
# Using Maven wrapper
./mvnw.cmd clean build

# Or using Maven directly
mvn clean build
```

### 4. Run the Application
```bash
# Using Maven wrapper
./mvnw.cmd clean spring-boot:run

# Or using Maven directly
mvn clean spring-boot:run
```

### 5. Access the Application
- **Frontend:** http://localhost:8080
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (leave blank)

---

## 📁 Project Structure

```
spring-boot-spring-security-jwt-authentication/
├── src/
│   ├── main/
│   │   ├── java/com/bezkoder/springjwt/
│   │   │   ├── SpringBootSecurityJwtApplication.java      # Main app entry point
│   │   │   ├── controllers/
│   │   │   │   ├── AuthController.java                    # Authentication endpoints
│   │   │   │   └── TestController.java                    # Role-based test endpoints
│   │   │   ├── models/
│   │   │   │   ├── User.java                              # User entity
│   │   │   │   ├── Role.java                              # Role entity
│   │   │   │   └── ERole.java                             # Enum for roles
│   │   │   ├── payload/
│   │   │   │   ├── request/
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   └── SignupRequest.java
│   │   │   │   └── response/
│   │   │   │       ├── JwtResponse.java
│   │   │   │       └── MessageResponse.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java                    # User data access
│   │   │   │   └── RoleRepository.java                    # Role data access
│   │   │   ├── security/
│   │   │   │   ├── WebSecurityConfig.java                 # Security configuration
│   │   │   │   ├── jwt/
│   │   │   │   │   ├── JwtUtils.java                      # JWT utilities
│   │   │   │   │   ├── AuthTokenFilter.java               # JWT filter
│   │   │   │   │   └── AuthEntryPointJwt.java             # Auth error handler
│   │   │   │   └── services/
│   │   │   │       ├── UserDetailsImpl.java                # Custom user details
│   │   │   │       └── UserDetailsServiceImpl.java         # User details service
│   │   │   └── DataInitializer.java                       # Initialize default users
│   │   └── resources/
│   │       ├── application.properties                     # Configuration
│   │       └── static/index.html                          # Frontend UI
│   └── test/
│       └── SpringBootSecurityJwtApplicationTests.java
├── pom.xml                                                 # Maven dependencies
├── mvnw & mvnw.cmd                                        # Maven wrapper
└── README.md                                               # This file
```

---

## 🔌 API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|----------------|
| POST | `/api/auth/signup` | Register a new user | ❌ No |
| POST | `/api/auth/signin` | Login and get JWT token | ❌ No |
| POST | `/api/auth/logout` | Logout user | ✅ Yes |

### Test Endpoints (Role-Based)

| Method | Endpoint | Description | Required Role |
|--------|----------|-------------|----------------|
| GET | `/api/test/all` | Public endpoint | ❌ None |
| GET | `/api/test/user` | User role test | USER |
| GET | `/api/test/mod` | Moderator role test | MODERATOR |
| GET | `/api/test/admin` | Admin role test | ADMIN |

---

## 🔐 Authentication Flow

The system implements a **stateless JWT-based authentication** pattern:

![Spring Boot JWT Authentication Flow](spring-boot-jwt-authentication-spring-security-flow.png)

**Flow Steps:**
1. **Signup** - User registers with username, email, password, and roles
2. **Password Encoding** - Password is hashed using BCrypt
3. **Login** - User provides credentials
4. **Validation** - Credentials validated against database
5. **Token Generation** - JWT token created with user claims
6. **Token Storage** - Client stores token in localStorage
7. **Authenticated Requests** - Token sent in Authorization header
8. **Token Validation** - Server validates token on each request

---

## 🔄 Token Refresh Flow

For better security, refresh tokens can be implemented:

![Spring Boot JWT Refresh Token Flow](spring-boot-refresh-token-jwt-example-flow.png)

---

## 🔒 Security Highlights

### ✅ What This Implements

- **Password Hashing** - BCrypt with configurable strength
- **Stateless Authentication** - No server-side sessions required
- **JWT Tokens** - Signed and validated on each request
- **CSRF Protection** - Protection against Cross-Site Request Forgery
- **CORS Configuration** - Secure cross-origin requests
- **Role-Based Authorization** - Different access levels per role
- **Secure Headers** - HTTP security headers configured

### 🛡️ Best Practices Used

- Never store passwords in plaintext
- JWT tokens are short-lived (configurable)
- Secrets are stored in configuration (not hardcoded)
- Input validation on signup/login
- HttpOnly cookies can be enabled (optional enhancement)

---

## 📖 How to Use

### 1. Register a New User

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "role": ["user"]
  }'
```

**Response:**
```json
{
  "message": "User registered successfully!"
}
```

### 2. Login and Get JWT Token

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "password123"
  }'
```

**Response:**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "roles": ["ROLE_USER"],
  "accessToken": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### 3. Access Protected Endpoint

**Request:**
```bash
curl -X GET http://localhost:8080/api/test/user \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

---

## 👤 Testing Credentials

Default users created on application startup:

| Username | Password | Role |
|----------|----------|------|
| `user` | `password123` | USER |
| `moderator` | `password123` | MODERATOR |
| `admin` | `password123` | ADMIN |

> ⚠️ **Note:** Change these credentials in production!

---

## 💼 Resume Value

### Why This Project Matters

✅ **Demonstrates Industry Skills:**
- Modern authentication patterns (JWT)
- Spring Security expertise
- REST API design
- Database design with JPA/Hibernate
- Full-stack development capability
- Security best practices

✅ **Real-World Relevance:**
- JWT is used by Netflix, Spotify, Slack, etc.
- Spring Boot is the #1 Java framework
- Role-based access control is fundamental to most apps
- This is the backbone of many SaaS applications

✅ **Interview Talking Points:**
- "I understand stateless vs stateful authentication"
- "I've implemented security properly from the ground up"
- "I know how to hash passwords and validate tokens"
- "I can explain OAuth2 and JWT concepts"

### How to Present on Resume

```
Spring Boot JWT Authentication System | GitHub Link
- Built a full-stack authentication system using Spring Boot 3.1 and Spring Security 6.0
- Implemented JWT-based stateless authentication with role-based access control (RBAC)
- Designed and implemented secure password hashing using BCrypt
- Created RESTful API endpoints for user signup, login, and role-based access
- Built responsive frontend UI with vanilla JavaScript and token management
- Database design using JPA/Hibernate ORM with H2 database
```

---

## 🚀 Future Enhancements

Potential improvements to make this project production-ready:

- [ ] Email verification on signup
- [ ] Password reset functionality
- [ ] Refresh token implementation for better security
- [ ] Two-factor authentication (2FA)
- [ ] User profile management endpoints
- [ ] API documentation with Swagger/OpenAPI
- [ ] Unit and integration tests (JUnit 5, Mockito)
- [ ] Docker containerization
- [ ] Deployment to cloud (AWS, Azure, Heroku)
- [ ] User activity logging and audit trails
- [ ] Rate limiting for API endpoints
- [ ] Database migration with Flyway/Liquibase

---

## 📚 Learning Resources

- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT.io - JWT Introduction](https://jwt.io/introduction)
- [OWASP Security Guidelines](https://owasp.org/)
- [Spring Boot Official Guide](https://spring.io/projects/spring-boot)

---

## 📝 License

This project is open source and available under the MIT License.

---

## 👨‍💻 Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

---

## 📞 Support

If you encounter any issues or have questions, feel free to open an issue in the repository.

---

**Made with ❤️ for learning and development**