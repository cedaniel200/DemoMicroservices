# Demo Microservices — Calculator API

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker&logoColor=white)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-blue?style=flat-square)](LICENSE)

Ejemplo de arquitectura de microservicios con los patrones y las pruebas que se le pueden aplicar. Los
servicios son deliberadamente pequeños: el foco está en la **implementación de patrones y buenas
prácticas**, no en la lógica de negocio, que es una calculadora.

## Servicios

| Servicio | Puerto | Qué hace |
|---|---:|---|
| `eureka-server` | 8761 | Registro y descubrimiento de servicios |
| `gateway` | 8762 | Punto de entrada único y enrutamiento |
| `add-microservice` | 8200 | Suma N números |
| `subtract-microservice` | 8300 | Resta N números |
| `fibonacci-microservice` | 8400 | Calcula Fibonacci y consume el servicio externo de números aleatorios |
| `DemoService` (externo) | 9000 | Simula un servicio de terceros: entrega el número aleatorio |

El cliente entra por el gateway y este enruta por nombre lógico (`lb://ADD-MICROSERVICE`), sin conocer
IPs ni puertos.

## Patrones y buenas prácticas

- **Service discovery** con Eureka: los servicios se registran y se buscan por nombre.
- **API Gateway** con rutas por `Path` hacia cada servicio.
- **Circuit breaker** con Resilience4j en Fibonacci: si el servicio externo falla, degrada a un
  número aleatorio local en vez de propagar el error.
- **Cliente HTTP** con Retrofit + OkHttp contra el servicio externo.
- **Contratos OpenAPI** por servicio (springdoc), accesibles a través del gateway.
- **Separación en capas**: `domain` e `infrastructure` en cada microservicio, y `application` en
  Fibonacci.
- **Configuración por entorno** con `application.yml` y variables de entorno.

## Pruebas

| Tipo | Dónde | Qué cubre |
|---|---|---|
| Unitarias | `*/src/test`, `*DefaultTest` | Dominio: suma, resta, Fibonacci, validaciones |
| Componentes / aceptación | `*AcceptanceTest` | El controlador y su respuesta HTTP real |
| Integración | `RandomClientIntegrationTest` | El cliente HTTP contra WireMock |
| E2E | `e2e/` (Serenity + Cucumber) | Flujo completo por el gateway |

## Variables de entorno

Los servicios se configuran con variables de entorno; los valores por defecto son los de Compose.

| Variable | Servicios | Por defecto | Para qué |
|---|---|---|---|
| `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` | `gateway`, `add`, `subtract`, `fibonacci` | `http://eureka:8761/eureka` | Dónde registrar el servicio |
| `RANDOM_NUMBER_URL` | `fibonacci` | `http://127.0.0.1:9000` (`host.docker.internal:9000` en Compose) | URL del servicio externo de números |
| `SERVER_PORT` | todos | `8761`, `8762`, `8200`, `8300`, `8400` | Puerto de escucha, si se quiere cambiar |
| `base.url` (o `BASE_URL`) | `e2e` | `http://localhost:8762` | Gateway contra el que corren las pruebas |

## Cómo ejecutarlo

Requiere Docker. El stack completo se levanta con una sola orden:

```bash
docker compose up --build
```

Gateway: <http://localhost:8762>. Desde ahí:

```bash
curl "http://localhost:8762/add/?numbers=1&numbers=2&numbers=3"
curl "http://localhost:8762/subtract/?numbers=10&numbers=3"
curl "http://localhost:8762/fibonacci/?number=10"
curl "http://localhost:8762/fibonacci/random"
```

Documentación de cada servicio:

- <http://localhost:8762/add/doc/>
- <http://localhost:8762/subtract/doc/>
- <http://localhost:8762/fibonacci/doc/>

Para bajar el stack:

```bash
docker compose down
```

### El servicio externo

`fibonacci-microservice` consume `/random?bound=10` de **DemoService**, que simula un servicio de
terceros y se levanta aparte, desde
[su repositorio](https://github.com/cedaniel200/DemoService) con su propio Compose:

```bash
docker compose -f compose.yml up -d
```

El microservicio lo busca en `host.docker.internal:9000` (configurable con `RANDOM_NUMBER_URL`).

Si el servicio externo no está levantado, `/fibonacci/random` sigue respondiendo **200** gracias al
circuit breaker: tras los primeros fallos, el circuito se abre y devuelve un valor de respaldo local.

## Tests

```bash
cd add-microservice && ./gradlew test
cd subtract-microservice && ./gradlew test
cd fibonacci-microservice && ./gradlew test
cd gateway && ./gradlew test
```

Las pruebas E2E necesitan el stack levantado:

```bash
cd e2e && ./gradlew test aggregate   # ejecuta y genera el reporte en target/site/serenity
```

Por defecto apuntan al gateway en `http://localhost:8762`; para otro destino:

```bash
cd e2e
./gradlew test -Dbase.url=http://localhost:9000
BASE_URL=http://localhost:8762 ./gradlew test
```

## Estructura

```text
DemoMicroservices
├── eureka-server/            # registro y descubrimiento
├── gateway/                  # API gateway (rutas y balanceo)
├── add-microservice/         # suma
├── subtract-microservice/    # resta
├── fibonacci-microservice/   # Fibonacci + circuit breaker + cliente del servicio externo
├── e2e/                      # Serenity + Cucumber contra el stack
├── calculator-api.yaml       # contrato OpenAPI de la API
└── docker-compose.yml        # entorno de desarrollo completo
```

Cada microservicio incluye su `Dockerfile` multi-etapa (compila con JDK y ejecuta con JRE como usuario
sin privilegios) y su propio wrapper de Gradle.

## Requisitos

- Docker y Docker Compose
- JDK 17 solo si quieres compilar o ejecutar los tests fuera de Docker

## Contacto

Si tienes alguna pregunta, escribe a **cdanielmg200@gmail.com**.
