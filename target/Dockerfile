# Fase de build
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -f pom.xml clean package

#Fase de runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app-texoit.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app-texoit.jar"]

