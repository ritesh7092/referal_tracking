# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies first
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a lightweight OpenJDK image
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from the previous stage; adjust the wildcard if needed
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app uses
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
