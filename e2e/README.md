# E2E — Serenity + Cucumber

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=openjdk&logoColor=white)
![Serenity](https://img.shields.io/badge/Serenity-5.4.1-1E88E5?style=flat-square)
![Cucumber](https://img.shields.io/badge/Cucumber-8.0.4-23D96C?style=flat-square&logo=cucumber&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-8.14.5-02303A?style=flat-square&logo=gradle&logoColor=white)

Pruebas de extremo a extremo del stack, escritas con Screenplay: el actor pide un Fibonacci por el
gateway y comprueba la respuesta. Valida el camino completo: gateway, Eureka, servicio y, en el caso
aleatorio, el servicio externo.

## Escenarios

`src/test/resources/features/calculate_fibonacci.feature`

| Escenario | Qué comprueba |
|---|---|
| Fibonacci de 10 | `GET /fibonacci/?number=10` responde 200 y `result` es 55 |
| Fibonacci de un número aleatorio | `GET /fibonacci/random` responde 200 y el resultado corresponde al número recibido |

## Requisitos

El stack tiene que estar levantado antes de ejecutar:

```bash
# en la raíz del repositorio
docker compose up -d
```

Si quieres que `/fibonacci/random` use el servicio externo real, levanta también
[DemoService](https://github.com/cedaniel200/DemoService). Si no está, el circuit breaker devuelve un
valor de respaldo y el escenario sigue pasando.

## Ejecución

```bash
./gradlew test                    # ejecuta los escenarios
./gradlew test aggregate          # ejecuta y genera el reporte
```

Reporte: `target/site/serenity/index.html`.

## URL del gateway

Por defecto `http://localhost:8762`. Se puede cambiar sin tocar el código:

```bash
./gradlew test -Dbase.url=http://otro-host:8762
BASE_URL=http://otro-host:8762 ./gradlew test
```

## Estructura

```text
src/main/java/com/cedaniel200/microservices/e2e
├── model/          # Fibonacci (respuesta)
├── questions/      # aserciones sobre la última respuesta
└── tasks/          # la tarea que consume el gateway

src/test/java/com/cedaniel200/microservices/e2e
├── runners/        # suite de JUnit Platform que ejecuta Cucumber
└── stepdefinitions/
```

El runner usa el motor de Cucumber sobre JUnit Platform con el plugin `SerenityReporter`, que es lo
que conecta los escenarios con el reporte.

---

[Volver al README principal](../README.md)
