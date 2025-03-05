# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from Maven's target directory
# Use wildcard to account for versioned JAR filenames
COPY target/retail-backend-*.jar app.jar

# Expose the application's port (matches application.properties)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
