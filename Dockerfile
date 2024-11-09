FROM openjdk:21-jdk-slim

ENV PORT 8080

WORKDIR /app

COPY build/libs/HTTP-Server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "app.jar"]
