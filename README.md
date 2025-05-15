# Product Service – spring-product-docker

## Overview

The Product Service is a core component of the supply chain management application. It is responsible for managing the catalog of products available in the system. This includes storing and retrieving product details such as name, description, and price.

Designed as an independent microservice, the Product Service supports basic CRUD operations and serves as a foundational service that is referenced by both the Inventory and Order services. It ensures that all inventory and order operations are based on valid and existing product data.

The service is built using Spring Boot and uses a dedicated MySQL database for persistence. It is containerized using Docker for ease of deployment and scalability.

---

## Features

- CRUD operations for managing product data.
- Stores key product attributes: name, description, price.
- Provides RESTful API endpoints for integration with other services.
- Ensures products are uniquely identified and retrievable for inventory and order validation.

---

## Architecture & Design

The Product Service follows the **database-per-service** pattern and connects to its own MySQL database (`productdb`). It is deployed as a container alongside its database container, and both are connected via a private Docker network. 

It is also connected to a shared network (`spring-shared-network`) to allow REST-based communication with other services like Inventory and Order.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Docker

---

## Environment Variables

Set the following environment variables when running the container:

- `MYSQL_HOST` – Hostname of the MySQL container (e.g., `mysqldb`)
- `MYSQL_PORT` – MySQL port (default: `3306`)
- `MYSQL_USER` – MySQL username (e.g., `root`)
- `MYSQL_PASSWORD` – MySQL password (e.g., `123456`)

---

## Docker Usage

### Build the Docker Image

```bash
docker build -t spring-product-docker .
