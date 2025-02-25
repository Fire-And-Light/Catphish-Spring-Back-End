FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/default.jpg .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]