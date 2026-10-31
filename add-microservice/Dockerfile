# ==========================================
# ETAPA 1: Construcción
# ==========================================
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /home/microservices/add

COPY . .

# Compilamos y generamos el JAR de producción (omitimos tests: se ejecutan en CI/local)
RUN chmod +x gradlew && ./gradlew clean bootJar -x test --no-daemon

# ==========================================
# ETAPA 2: Imagen final de producción (solo JRE)
# ==========================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copiamos únicamente el JAR compilado en la etapa anterior
COPY --from=builder /home/microservices/add/build/libs/*-SNAPSHOT.jar app.jar

# Buenas prácticas de seguridad: no ejecutar como root
RUN groupadd --system spring && useradd --system --gid spring spring
USER spring:spring

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "app.jar"]
