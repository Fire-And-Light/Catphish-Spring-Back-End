FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY build/libs/demo-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]