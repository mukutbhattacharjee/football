FROM openjdk:8-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/football-standings.jar
COPY ${JAR_FILE} /app/football-standings.jar

ENTRYPOINT ["java","-jar","/app/football-standings.jar"]