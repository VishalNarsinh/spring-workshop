# Spring Workshop 01

Spring Boot workshop project using Java, Spring Web, Spring Data JPA, Validation, and MySQL.

## Tech Stack
- Java 
- Spring Boot 4.0.5
- Maven Wrapper (`mvnw` / `mvnw.cmd`)
- MySQL (runtime)

## Prerequisites
- JDK installed
- MySQL running locally on `localhost:3306`
- Database user configured as in `src/main/resources/application.properties`

Current default DB config:
- URL: `jdbc:mysql://localhost:3306/spring_workshop?createDatabaseIfNotExist=true`
- Username: `root`
- Password: `root`

## Run Locally
App starts at:
- `http://localhost:8080`

## API Collection
Import Postman collection:
- `Spring Workshop.postman_collection.json`

Set Postman variable:
- `host = http://localhost:8080`

## Configuration Notes
- Main config: `src/main/resources/application.properties`
- JPA DDL mode is currently `update` for local workshop convenience.
