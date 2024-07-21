FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/finale-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "finale-0.0.1-SNAPSHOT.jar"]
