# Etapa de build con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos de proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto y empaquetar (skip tests para acelerar, puedes quitar -DskipTests)
RUN mvn clean package -DskipTests

# Etapa de runtime con JRE 21 minimal
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el jar generado de la etapa build
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone tu app Spring Boot (ajusta si usas otro)
EXPOSE 5100

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
