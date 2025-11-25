# Etapa de build con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos de proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto
RUN mvn clean package -DskipTests

# Etapa de runtime con JRE 21 minimal
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el jar generado de la etapa build
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=dev
ENV SERVER_PORT=5051

# Puerto expuesto
EXPOSE ${SERVER_PORT}

# Ejecutar Spring Boot
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
