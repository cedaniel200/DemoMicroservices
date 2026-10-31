# Eureka Server

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Registro de servicios del stack. Cada microservicio se da de alta aquí y el gateway resuelve los
nombres lógicos (`ADD-MICROSERVICE`, `SUBTRACT-MICROSERVICE`, `FIBONACCI-MICROSERVICE`) contra este
registro, sin conocer IPs ni puertos.

## Ejecución

Desde la raíz del repositorio, con el resto del stack:

```bash
docker compose up --build
```

Solo este servicio, en local:

```bash
./gradlew bootRun
```

| Dato | Valor |
|---|---|
| Puerto | `8761` (`SERVER_PORT`) |
| Panel | <http://localhost:8761> |
| Registro propio | Desactivado (`registerWithEureka: false`, `fetchRegistry: false`) |

## Configuración

```yaml
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
```

El resto de servicios apuntan a `http://eureka:8761/eureka`, el nombre del servicio en Compose.

## Verificación

El panel responde `200` cuando el servidor está arriba:

```bash
curl -o /dev/null -w "%{http_code}\n" http://localhost:8761/
```

## Tests

Este módulo no tiene pruebas propias.

---

[Volver al README principal](../README.md)
