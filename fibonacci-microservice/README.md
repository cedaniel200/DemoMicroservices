# Fibonacci Microservice

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Resilience4j](https://img.shields.io/badge/Resilience4j-circuit%20breaker-2E7D32?style=flat-square)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Calcula el número de Fibonacci de un valor dado o de un valor aleatorio. El aleatorio lo pide a un
**servicio externo** (DemoService) y esa llamada está protegida con un **circuit breaker**.

## Endpoints

| Método | Ruta | Parámetros | Ejemplo |
|---|---|---|---|
| `GET` | `/fibonacci/` | `number` | `/fibonacci/?number=10` |
| `GET` | `/fibonacci/random` | — | `/fibonacci/random` |

```bash
curl "http://localhost:8400/fibonacci/?number=10"
```

```json
{
  "number": 10,
  "result": 55
}
```

`/fibonacci/random` devuelve la misma forma, con el número que entregó el servicio externo (entre 0 y
9) y su Fibonacci.

## El servicio externo

La llamada sale con **Retrofit + OkHttp** a `/random?bound=10` de
[DemoService](https://github.com/cedaniel200/DemoService), que simula un servicio de terceros:

| Dato | Valor |
|---|---|
| URL por defecto | `http://127.0.0.1:9000` |
| Variable de entorno | `RANDOM_NUMBER_URL` |
| En Compose | `http://host.docker.internal:9000` |

## Circuit breaker

```yaml
resilience4j:
  circuitbreaker:
    instances:
      randomService:
        slidingWindowSize: 20
        minimumNumberOfCalls: 10
        failureRateThreshold: 50
        slowCallDurationThreshold: 2000ms
        slowCallRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
```

- **Cerrado**: pasa la llamada al servicio externo.
- **Abierto**: no lo intenta y devuelve un número aleatorio local (`fallbackGetRandomNumber`).
- **Semiabierto**: deja pasar 3 llamadas para comprobar si el servicio volvió.

Por eso `/fibonacci/random` responde **200** aunque DemoService esté apagado: el cliente no ve el
fallo, ve el valor de respaldo.

## Configuración

| Dato | Valor |
|---|---|
| Puerto | `8400` |
| Registro | `http://eureka:8761/eureka` (`EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`) |
| Swagger UI | <http://localhost:8400/fibonacci/doc/> |
| OpenAPI | `/fibonacci/fibonacci-docs` |

## Capas

```text
com.cedaniel200.microservice.fibonacci
├── application/       # FibonacciCalculatorDefault
├── domain/            # modelo, repositorio y validaciones
└── infrastructure/    # controlador, configuración, cliente Retrofit
```

## Tests

```bash
./gradlew test
```

- `FibonacciCalculatorDefaultTest`: cálculo y validación.
- `RandomClientIntegrationTest`: cliente HTTP contra WireMock.
- `FibonacciAcceptanceTest`: los dos endpoints por HTTP real, con WireMock en el puerto 9000.

---

[Volver al README principal](../README.md)
