FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# Give execute permissions to mvnw script
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/inventorymanagement-0.0.1-SNAPSHOT.jar"]
