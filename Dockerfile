FROM openjdk:17-jdk-slim

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ARG SPRING_PROFILE=dev
ENV SPRING_PROFILE=$SPRING_PROFILE

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "/app.jar"]