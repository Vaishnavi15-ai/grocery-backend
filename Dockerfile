# Use an official Gradle image to build the app
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy the gradle wrapper and required files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the application, skipping tests to speed up the build
RUN ./gradlew build -x test

# Use a smaller JDK image to run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
