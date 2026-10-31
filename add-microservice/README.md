# Add Microservice

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-springdoc%202.6.0-85EA2D?style=flat-square&logo=swagger&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Suma una lista de números enteros. Es el microservicio más pequeño del stack y sirve para mostrar la
estructura por capas, el registro en Eureka y el contrato OpenAPI sin ruido de negocio.

## Endpoint

| Método | Ruta | Parámetros |
|---|---|---|
| `GET` | `/add/` | `numbers` (repetible) |

```bash
curl "http://localhost:8200/add/?numbers=1&numbers=2&numbers=3"
```

```json
{
  "numbers": [1, 2, 3],
  "result": 6
}
```

Cada número va como un parámetro `numbers` repetido; no se admite `numbers=1,2,3`.

## Configuración

| Dato | Valor |
|---|---|
| Puerto | `8200` |
| Registro | `http://eureka:8761/eureka` (`EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`) |
| Swagger UI | <http://localhost:8200/add/doc/> |
| OpenAPI | `/add/add-docs` |

En local, sin Docker, apunta el registro a tu Eureka:

```bash
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka ./gradlew bootRun
```

Si Eureka no está levantado, el servicio arranca igual y deja un error en consola.

## Capas

```text
com.cedaniel200.microservices.add
├── domain/            # Adder y su implementación
└── infrastructure/    # controlador, configuración y VO de respuesta
```

## Tests

```bash
./gradlew test
```

- `AdderDefaultTest`: cuatro casos del dominio.
- `AddAcceptanceTest`: el controlador responde por HTTP real.

---

[Volver al README principal](../README.md)
