# ==========================================
# ETAPA 1: Construcción
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /home/microservices/add

COPY . .

# Compilamos y generamos el JAR empaquetado para producción (omitiendo tests para velocidad)
RUN ./gradlew clean bootJar -x test --no-daemon

# ==========================================
# ETAPA 2: Imagen Final de Producción (Usa solo JRE ligero)
# ==========================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos ÚNICAMENTE el archivo .jar compilado en la Etapa 1
COPY --from=builder /home/microservices/add/build/libs/*-SNAPSHOT.jar app.jar

# Buenas prácticas de seguridad: no ejecutar como usuario Root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "app.jar"]