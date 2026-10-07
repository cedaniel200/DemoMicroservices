# ==========================================
# ETAPA 1: Construcción
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS builder

# Directorio temporal de compilación
WORKDIR /home/microservices/fibonacci

# Copiamos todo el código para realizar la compilación limpia
COPY . .

# Compilamos y generamos el JAR empaquetado para producción (omitiendo tests para velocidad)
RUN ./gradlew clean bootJar -x test --no-daemon

# ==========================================
# ETAPA 2: Imagen Final de Producción (Usa solo JRE ligero)
# ==========================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos ÚNICAMENTE el archivo .jar compilado en la Etapa 1
COPY --from=builder /home/microservices/fibonacci/build/libs/*-SNAPSHOT.jar app.jar

# Buenas prácticas de seguridad: no ejecutar como usuario Root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Exponemos el puerto original de tu servicio
EXPOSE 8400

# Ejecuta directamente el JAR con la máquina virtual (JRE)
ENTRYPOINT ["java", "-jar", "app.jar"]
