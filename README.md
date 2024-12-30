# Spring Boot REST API Boilerplate

![PagueiBaratoLogo](https://raw.githubusercontent.com/iambishalsaha/spring-boot-rest-api-boilerplate/refs/heads/main/github-banner.webp)


A boilerplate for Spring Boot with JWT authentication, email functionalities, and
much more.

## Overview

**Spring Boot REST API Boilerplate** is a Spring Boot boilerplate that
provides a solid foundation for building web applications with
JWT authentication, CORS filters, email functionalities, and more.
It comes with a set of features and configurations more fast as possible your development.
I will commit some improvements over time here and I will also
create a boilerplate in Kotlin as the next objective,
if possible send improvements to this project, I will insert crud abstraction here too and in Kotlin crud it will also
be something very similar to this.

## Features

- JWT authentication
- CORS filter configuration
- Token provider and JWT authentication filter
- User roles with permissions
- Email functionalities (account creation, email verification, login, password reset, password forget)
- Example controller, service, repository, entity, and domain
- Exception handling
- Seeder for initial user creation with example permissions
- Lombok for simplified mapping and reduced boilerplate code
- Dockerfile for containerization
- Docker Compose for easy database setup

## Upcoming Features

- Enable/Disable user.
- Lock/Unlock user account upon consecutive failed login attempts.

## Testing

### Performance Testing

- **jMeter**

## Code Quality & Coverage

In **Spring Boot REST API Boilerplate**, integrating SonarQube with JaCoCo has been instrumental in maintaining high code
quality and ensuring thorough test coverage. JaCoCo is configured in the build system (Maven or Gradle) to generate
detailed XML reports on line, branch, and instruction coverage during test execution. These coverage reports are
seamlessly fed into SonarQube, which acts as the central hub for code quality analysis. SonarQube not only visualizes
test coverage but also provides actionable insights into potential code smells, vulnerabilities, and duplications in the
project. This setup enables you to monitor untested paths in **Spring Boot REST API Boilerplate** endpoints, enforce minimum coverage
thresholds, and ensure that critical business logic is rigorously tested.

- [sonarQube](https://www.sonarsource.com/products/sonarqube/)
- [JaCoCo](https://www.eclemma.org/jacoco/)