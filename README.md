# B2B Order & Billing Platform
B2B platform for order management, billing and payments, designed with a modular, testable core and evolving towards an event-driven architecture.

---

## Project Goals

This project aims to simulate a **real-world backend system** commonly found in enterprise and B2B environments.

The main goals are:
- Build a **clean, framework-independent domain core**
- Apply **clean architecture and DDD-inspired principles**
- Evolve incrementally from a **modular monolith** towards **event-driven and distributed architectures**
- Apply **performance testing, scalability strategies and resilience patterns**
- Showcase **production-oriented backend engineering skills**

---

## Architectural Approach

The project is built following these principles:

- **Domain-first design (Java pure)**
- Clear separation between:
    - Domain
    - Application
    - Infrastructure
- Frameworks (Spring) are treated as **delivery mechanisms**, not the core
- High testability: removing Spring should not break domain tests
- Incremental evolution instead of premature microservices

Initial architecture:
- Modular Monolith

Planned evolution:
- Event-driven communication
- Asynchronous processing
- Optional microservice extraction

---

## Core Domains (Bounded Contexts)

- Order Management
- Billing & Invoicing
- Payments
- Customer Accounts
- Notifications (async)

Each domain owns:
- Its business rules
- Its data model
- Its invariants

---

## Security

- OAuth2
- JWT-based authentication
- Role and scope-based authorization
- Designed for B2B multi-tenant scenarios

---

## Tech Stack

### Backend
- Java 17

### Data
- PostgreSQL

### Testing
- JUnit 5
- Mockito

### DevOps & Infrastructure
- Docker
- Docker Compose