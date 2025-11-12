# 📚 Library Management System – Backend (Microservices)

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Dockerized-blue?logo=postgresql)
![JUnit](https://img.shields.io/badge/Testing-JUnit%20%7C%20Mockito-yellow)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?logo=docker)

---

## 🏗️ Overview

This project is a **Library Management System** built using **Spring Boot Microservices Architecture**.  
It provides modular services for books, members, transactions and authentication.  
The system is **fully containerized using Docker** and uses **PostgreSQL** as the database.

---

## 🔹 Microservices

| Service Name         | Description |
|----------------------|-------------|
| **book-service**      | Manages book data, CRUD operations, and pagination. |
| **member-service**    | Handles library member details and related operations. |
| **transaction-service** | Manages book issue and return transactions. |
| **auth-service**      | Handles user authentication and authorization (JWT). |
| **api-gateway**       | Routes and secures API requests between clients and services. |
| **service-registry**  | Manages service discovery (Eureka Server). |

---

## ⚙️ Tech Stack

- **Backend:** Spring Boot, Spring Cloud
- **Database:** PostgreSQL (Dockerized)
- **Build Tool:** Maven
- **Containerization:** Docker, Docker Compose
- **Service Discovery:** Spring Cloud Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Authentication:** JWT
- **Testing:** JUnit, Mockito
- **Features:** Pagination, CRUD operations, Microservices architecture

---

## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 21+
- Maven 3+
- Docker & Docker Compose


### 2️⃣ Clone the Repository
```bash
git clone https://github.com/notsanta20/lib_mngmnt_system_backend.git
cd lib_mngmnt_system_backend
```

### 3️⃣ Build and run with Docker Compose
```bash
docker-compose up --build
```
### This will start:
- All microservices
- PostgreSQL database
- API Gateway
- Eureka Service Registry

### To stop:
```bash
docker-compose down
```

---

## 🔗 API Overview

| Method | Path          | Description   |
|--------|---------------|---------------|
| POST   | /api/register | register user |
| POST    | /api/login    | login user    |







