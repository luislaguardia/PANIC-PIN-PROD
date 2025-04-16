# Use a Java 17 base image
FROM eclipse-temurin:17-jdk as builder

# Set working directory
WORKDIR /app

# Copy the whole project
COPY . .

# Build the app using Gradle Wrapper
RUN ./gradlew build --no-daemon

# --- Runtime stage ---
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built JAR from previous image
COPY --from=builder /app/app/build/libs/*.jar app.jar

# Expose the port (Render sets PORT env var)
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "app.jar"]