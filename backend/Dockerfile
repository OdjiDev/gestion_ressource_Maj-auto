# ==============================================================
# Étape 1 : Build Spring Boot
# ==============================================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -B

# ==============================================================
# Étape 2 : Runtime JRE
# ==============================================================
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="Odji" \
      description="Gestion Ressources - Backend Spring Boot"

WORKDIR /app

# Créer utilisateur non-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copier le JAR
COPY --from=build /app/target/*.jar app.jar

# Permissions
RUN chown -R appuser:appgroup /app

USER appuser

# Port (interne uniquement)
EXPOSE 8081

# Options JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75.0 \
    -XX:+UseG1GC \
    -Djava.security.egd=file:/dev/./urandom"

# Healthcheck (nécessite spring-boot-starter-actuator)
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]