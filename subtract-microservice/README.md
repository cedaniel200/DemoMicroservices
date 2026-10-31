# Subtract Microservice

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-springdoc%202.6.0-85EA2D?style=flat-square&logo=swagger&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Resta una lista de números enteros, en orden. Mismo esqueleto que el servicio de suma: capas,
registro en Eureka y contrato OpenAPI.

## Endpoint

| Método | Ruta | Parámetros |
|---|---|---|
| `GET` | `/subtract/` | `numbers` (repetible) |

```bash
curl "http://localhost:8300/subtract/?numbers=10&numbers=3"
```

```json
{
  "numbers": [10, 3],
  "result": 7
}
```

## Configuración

| Dato | Valor |
|---|---|
| Puerto | `8300` |
| Registro | `http://eureka:8761/eureka` (`EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`) |
| Swagger UI | <http://localhost:8300/subtract/doc/> |
| OpenAPI | `/subtract/subtract-docs` |

En local, sin Docker, apunta el registro a tu Eureka:

```bash
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka ./gradlew bootRun
```

## Capas

```text
com.cedaniel200.microservice.subtract
├── domain/            # Subtractor y su implementación
└── infrastructure/    # controlador, configuración y VO de respuesta
```

## Tests

```bash
./gradlew test
```

- `SubtractorDefaultTest`: cuatro casos del dominio.
- `SubtractAcceptanceTest`: el controlador responde por HTTP real.

---

[Volver al README principal](../README.md)
