FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/shopping-cart-0.0.1-SNAPSHOT.jar "shopping-cart.jar"
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "shopping-cart.jar"]