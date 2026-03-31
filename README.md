
# ⚖️ Quantity Measurement App

A Java application built using **Data Driven Testing (DDT)** that progressively evolves from a simple unit comparison utility into a full **Spring Boot REST API** with database persistence.

---

## 🚀 Tech Stack

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![JPA](https://img.shields.io/badge/Spring%20Data-JPA-green?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square)
![JUnit](https://img.shields.io/badge/JUnit-5-yellow?style=flat-square)

---

## 📖 Project Journey

This project was built **use case by use case**, each one adding a new layer of functionality on top of the previous.

---

### 🔵 Phase 1 — Length Measurement (UC1–UC7)

| UC | Branch | Description |
|----|--------|-------------|
| UC1 | `feature/UC1-FeetMeasurementEquality` | Compare two feet values for equality |
| UC2 | `feature/UC2-FeetAndInchesMeasurementEquality` | Compare two inch values for equality |
| UC3 | `feature/UC3-GenericQuantityClassForDRYPrinciple` | Refactor to generic `LengthUnit` enum with conversion factors |
| UC4 | `feature/UC4-ExtendedUnitSupport` | Add `YARDS` unit (1 yard = 3 feet = 36 inches) |
| UC5 | `feature/UC5-Unit-to-Unit-Conversion` | Convert between units (e.g. 1 FEET → 12 INCHES) |
| UC6 | `feature/UC6-Addition-Of-Two-Length-Units` | Add two quantities of different units |
| UC7 | `feature/UC7-Addition-With-Target-Unit-Specification` | Result of addition returned in first operand's unit |

---

### 🟣 Phase 2 — Generic Units (UC8–UC12)

| UC | Branch | Description |
|----|--------|-------------|
| UC8 | `feature/UC8-Refactoring-Unit-Enum-To-Standalone` | Extract `IMeasurable` interface — decouple core logic from specific units |
| UC9 | `feature/UC9-Weight-Measurement` | Add `WeightUnit` (KILOGRAM, GRAM, POUND) |
| UC10 | `feature/UC10-Generic-Quantity-Class-with-Unit-Interface` | Make `Quantity<U extends IMeasurable>` fully generic |
| UC11 | `feature/UC11-Volume-Measurement` | Add `VolumeUnit` (LITRE, MIILILITRE, GALLON) |
| UC12 | `feature/UC12-Subtraction-and-Division-Operations` | Add `subtract()` and `divide()` operations |

---

### 🟠 Phase 3 — Architecture (UC13–UC15)

| UC | Branch | Description |
|----|--------|-------------|
| UC13 | `feature/UC13-Centralized-Arithmetic-Logic` | Centralize arithmetic into `ArithmeticOperation` enum inside `Quantity` |
| UC14 | `feature/UC14-Temperature-Measurement` | Add `TemperatureUnit` (CELSIUS, FAHRENHEIT) with non-linear lambda conversion. Temperature blocks arithmetic operations |
| UC15 | `feature/UC15-N-Tier` | Full N-Tier architecture — Controller → Service → Repository → Model |

---

### 🟡 Phase 4 — Database (UC16)

| UC | Branch | Description |
|----|--------|-------------|
| UC16 | `feature/UC16-Database-Integration-with-JDBC` | JDBC database integration with H2, connection pooling, `ApplicationConfig`, `ConnectionPool`, `DatabaseException`, and `QuantityMeasurementDatabaseRepository` |

**Key concepts introduced:**
- JDBC connection management
- Connection pooling
- Parameterized SQL queries (SQL injection prevention)
- `application.properties` configuration
- Custom exception hierarchy

---

### 🟢 Phase 5 — Spring Boot REST API (UC17)

| UC | Branch | Description |
|----|--------|-------------|
| UC17 | `feature/UC17-Spring-Backend` | Full Spring Boot REST API with JPA, embedded Tomcat, H2 console, Security config, and Global Exception Handler |

**Key concepts introduced:**
- Spring Boot auto-configuration
- Spring Data JPA (replaces manual JDBC)
- REST Controllers with `@RestController`
- Dependency Injection with `@Autowired`
- Global Exception Handling with `@RestControllerAdvice`
- H2 in-memory database with JPA auto table creation

---

### 🔴 Phase 6 — Security (UC18)

| UC | Branch | Description |
|----|--------|-------------|
| UC18 | `feature/UC18-Authentication` | JWT token based API security |

**Key concepts introduced:**
- JWT token generation and validation
- Protected API endpoints
- Spring Security filter chain
- Stateless session management

---

## ⚙️ Running the App

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps
```bash
# Clone the repo
git clone https://github.com/GuptaDivi3007/QuantityMeasurementApp.git

# Run the app
mvn spring-boot:run
```

App starts at: `http://localhost:8080`

---

## 🧪 Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=QuantityMeasurementAppTest
```

---

## 📐 Supported Units

| Category | Units |
|----------|-------|
| Length | `FEET`, `INCHES`, `YARD`, `CENTIMETERS` |
| Weight | `KILOGRAM`, `GRAM`, `POUND` |
| Volume | `LITRE`, `MIILILITRE`, `GALLON` |
| Temperature | `CELSIUS`, `FAHRENHEIT` *(comparison & conversion only)* |

---

## 🧠 Key Concepts Learned

- Data Driven Testing (DDT)
- Object Oriented Design (Generics, Interfaces, Enums)
- N-Tier Architecture (Controller → Service → Repository)
- JDBC & Connection Pooling
- Spring Boot & Dependency Injection
- Spring Data JPA & ORM
- REST API Design
- Global Exception Handling
- H2 In-Memory Database

---

