FROM maven:3.9.8-amazoncorretto-17-al2023 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Pprod -DskipTests


FROM openjdk:24-slim-bullseys
WORKDIR /app
COPY --from=build /target/spring-data-jpa-tutorial-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT [“java”,“-jar”,“demo.jar”]