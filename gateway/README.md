# Gateway Server

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring%20Cloud%20Gateway-2023.0.3-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Punto de entrada único del stack. El cliente habla solo con este servicio y el gateway enruta por
nombre lógico a través de Eureka (`lb://`), repartiendo la carga entre las instancias disponibles.

## Rutas

| Prefijo | Destino | Servicio |
|---|---|---|
| `/add/**` | `lb://ADD-MICROSERVICE` | Suma |
| `/subtract/**` | `lb://SUBTRACT-MICROSERVICE` | Resta |
| `/fibonacci/**` | `lb://FIBONACCI-MICROSERVICE` | Fibonacci |

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: addMicroservice
          uri: lb://ADD-MICROSERVICE
          predicates:
            - Path=/add/**
```

## Ejecución

```bash
docker compose up --build
```

Solo este servicio, en local:

```bash
./gradlew bootRun
```

| Dato | Valor |
|---|---|
| Puerto | `8762` |
| Descubrimiento | `http://eureka:8761/eureka` |

## Ejemplos

```bash
curl "http://localhost:8762/add/?numbers=1&numbers=2&numbers=3"
curl "http://localhost:8762/fibonacci/?number=10"
```

## Tests

```bash
./gradlew test
```

`GatewayApplicationTest` comprueba que el contexto de Spring arranca.

---

[Volver al README principal](../README.md)
