# Retail Backend

This is the backend service for the retail application, built using **Spring Boot** with support for **Kafka, RabbitMQ, PostgreSQL, and Selenium-based UI testing**. 

mvn clean package -DskipTests
mvn flyway:migrate

Actuator endpoints will be available at:
http://localhost:9080/actuator/health
http://localhost:9080/actuator/info
http://localhost:9080/actuator

✅ Swagger UI: http://localhost:8080/swagger-ui.html
✅ Actuator Health Check: http://localhost:8080/actuator/health
✅ Actuator Info: http://localhost:8080/actuator/info

## Features
- REST API for managing orders and products
- PostgreSQL database integration
- Kafka/RabbitMQ for asynchronous messaging
- Integration and unit testing using Testcontainers
- Selenium-based UI testing
- CI/CD with GitHub Actions

## Setup Instructions
1. Clone the repository:  
   ```sh
   git clone https://github.com/your-repo/retail-backend.git
   cd retail-backend
   ```
2. Build the project:  
   ```sh
   mvn clean install
   ```
3. Run the application:  
   ```sh
   mvn spring-boot:run
   ```
4. Run tests:  
   ```sh
   mvn test
   ```

## Docker Compose Setup
You can run services selectively based on your needs by using different Docker Compose configurations.

### **1. Run Only PostgreSQL**
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15.2
    container_name: postgres_db
    environment:
      POSTGRES_DB: retaildb
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
    ports:
      - "5432:5432"
```

Start PostgreSQL only:
```sh
docker-compose -f docker-compose.postgres.yml up -d
```

### **2. Run Only Kafka**
```yaml
version: '3.8'
services:
  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka_broker
    environment:
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper:2181"
      KAFKA_ADVERTISED_LISTENERS: "PLAINTEXT://kafka:9092"
    ports:
      - "9092:9092"
```

Start Kafka only:
```sh
docker-compose -f docker-compose.kafka.yml up -d
```

### **3. Run Kafka + PostgreSQL + RabbitMQ**
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15.2
    container_name: postgres_db
    environment:
      POSTGRES_DB: retaildb
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
    ports:
      - "5432:5432"

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka_broker
    environment:
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper:2181"
      KAFKA_ADVERTISED_LISTENERS: "PLAINTEXT://kafka:9092"
    ports:
      - "9092:9092"

  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq_broker
    ports:
      - "5672:5672"
      - "15672:15672"
```

Start all services:
```sh
docker-compose -f docker-compose.full.yml up -d
```

## Environment Configuration
To switch between configurations dynamically, update the application properties in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/retaildb
    username: admin
    password: admin
  kafka:
    bootstrap-servers: localhost:9092
  rabbitmq:
    host: localhost
    port: 5672
```

Modify properties based on which services you are running.

---