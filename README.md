Java Projects Portfolio

This repository contains multiple Java projects demonstrating core, intermediate, and advanced concepts in Java — from data structures and console interaction to Spring Boot applications using Docker and MySQL.

🧩 Project 1: Binary Search Tree (BST) Operations
📘 Description

A simple Java console application implementing Binary Search Tree (BST) functionality.
It allows the user to interactively insert, delete, search, traverse (preorder, inorder, postorder), and check BST validity.

🛠️ Features

Insert and delete nodes from the BST

Search for elements

Preorder, Inorder, and Postorder traversals

Validate if a tree structure is a BST

Interactive menu-based system in console

🧠 Concepts Used

Recursion

Encapsulation

Binary tree manipulation

Java console I/O

▶️ How to Run

Open the project in IntelliJ IDEA or any IDE.

Run Main.java.

Use the console menu to perform BST operations.

💰 Project 2: Simple Banking Application
📘 Description

A Java console-based banking system that allows users to deposit, withdraw, and check balance with error handling.
Transaction history is saved to a text file for later review.

🛠️ Features

Deposit and withdraw funds

Balance inquiry

Prevents overdrafts

Transaction history saved to transactions.txt

Clean, menu-driven console interface

💾 File Handling

Every operation (deposit/withdraw/check) is logged into a text file for persistence.

▶️ How to Run

Open BankingApplication.java in your IDE.

Run the app and follow the console prompts.

Check transactions.txt for logged history.

📚 Project 3: Library Management System (Spring Boot + MySQL + Docker + Swagger)
📘 Description

A full-fledged Library Management System built using Spring Boot, JPA, Docker, and MySQL.
Implements CRUD operations for managing books, users, and transactions — with RESTful APIs and Swagger documentation.

🧱 Tech Stack
Component	Technology
Backend Framework	Spring Boot 3
ORM	Spring Data JPA (Hibernate)
Database	MySQL (Dockerized)
API Documentation	Swagger / SpringDoc OpenAPI
Build Tool	Maven
Language	Java 17+
🐳 Docker Setup

docker-compose.yml:

version: '3.8'
services:
  mysql:
    image: mysql:8.0
    container_name: library_mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: librarydb
      MYSQL_USER: library_user
      MYSQL_PASSWORD: password123
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

volumes:
  db_data:


Run MySQL in Docker:

docker-compose up -d

⚙️ Spring Boot Configuration

application.properties:

spring.application.name=library-management
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=library_user
spring.datasource.password=password123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=9095

📦 Project Structure
src/main/java/com/example/library_management
│
├── controller
│   ├── BookController.java
│   ├── UserController.java
│   └── TransactionController.java
│
├── model
│   ├── Book.java
│   ├── User.java
│   └── Transaction.java
│
├── repository
│   ├── BookRepository.java
│   ├── UserRepository.java
│   └── TransactionRepository.java
│
├── service
│   ├── BookService.java
│   ├── UserService.java
│   └── TransactionService.java
│
└── LibraryManagementApplication.java

🧠 Core Features

Manage Books, Users, and Borrow Transactions

CRUD REST APIs for all entities

Auto table creation via Hibernate (ddl-auto=update)

Integrated with Swagger UI

🧪 API Testing (Swagger)

Run the application and open:

👉 http://localhost:9095/swagger-ui/index.html

From there, you can test:

GET /api/books

POST /api/books

DELETE /api/books/{id}

POST /api/transactions/borrow

POST /api/transactions/return

🔒 Optional Enhancements

Add DTOs for request/response mapping

Add exception handling with @ControllerAdvice

Secure Swagger UI or endpoints with Spring Security

Add pagination and sorting for books/users

🧾 Summary
Project	Description	Technologies
Binary Search Tree	Interactive data structure with insert, delete, search, and traversal	Core Java
Simple Banking System	Console-based banking app with file persistence	Core Java
Library Management System	Full RESTful CRUD app with Spring Boot, MySQL (Docker), Swagger	Spring Boot, Docker, JPA