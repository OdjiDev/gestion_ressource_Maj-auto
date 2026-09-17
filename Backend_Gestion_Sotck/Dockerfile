# Étape 1 : Build Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Cache des dépendances Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Compilation
COPY src ./src
RUN mvn package -DskipTests

# Étape 2 : Image d'exécution (JRE)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
