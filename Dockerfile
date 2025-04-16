# Build Stage
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Copy entire project and build it
COPY . .
RUN ./gradlew build --no-daemon

# Runtime Stage
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only the built jar from previous stage
COPY --from=builder /app/app/build/libs/*.jar app.jar

# Set the port (Render uses $PORT)
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "app.jar"]