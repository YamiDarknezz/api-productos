# Etapa de build con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos de proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto (ajusta -DskipTests si necesitas ejecutar tests)
RUN mvn clean package -DskipTests

# Etapa de runtime con JRE 21 minimal
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el jar generado de la etapa build
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto que usa Spring Boot (se puede sobreescribir con una variable)
EXPOSE 5100

# Ejecutar Spring Boot con paso de variables si es necesario
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${SERVER_PORT:-5100}"]
