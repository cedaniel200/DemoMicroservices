# Demo Microservices - Calculator Api

Un ejemplo basico de una arquitectura de microservicios con las pruebas que a esta se le pueden aplicar, las cuales incluye:

* Pruebas unitarias
* Pruebas de Componentes
* Pruebas de Integración
* E2E

Los microservicios construidos son:

* Add
* Subtract
* Fibonacci

## Tecnologías de desarrollo

Los microservicios y servers fueron construídos con las siguientes herramientas y lenguajes:

* Gradle 7.1
* Java 8
* Spring Boot 
* Retrofit 2 [Fibonacci]
* Okhttp 3 [Fibonacci]

## Requisitos

* Tener instalado Docker
* Si quiere que todos los endpoints de **Fibonacci** funcionen correctamente debe tener corriendo el servicio que puede descargar 
en el repositorio https://github.com/cedaniel200/DemoService. Sin este **/fibonacci/random** devolvera siempre los valores de **-1** 

## Para correr todos los microservicios

Ejecutar el comando:

    docker compose up

URL http://localhost:8762

Puede acceder a la documentación de cada microservicio en:

* http://localhost:8762/add/doc/
* http://localhost:8762/subtract/doc/
* http://localhost:8762/fibonacci/doc/


## Si tiene alguna pregunta, puede escribirme a cdanielmg200@gmail.com