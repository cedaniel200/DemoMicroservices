# Fibonacci Microservice
_________________________

Para ejecutar el proyecto usar el comando:

    gradlew bootRun

Nota:
* Si quiere que todos los endpoints de **Fibonacci** funcionen correctamente debe tener corriendo el servicio que puede descargar
  en el repositorio https://github.com/cedaniel200/DemoService. Sin este **/fibonacci/random** devolvera siempre los valores de **-1**
* Si **Eureka** esta corriendo de forma local asegurese de cambiar en el archivo application.yml **eureka** por **localhost**,
  no olvide volver a dejarlo como estaba, ya que, de lo contrario no servira el **comandon docker compose up**.
* Si **Eureka** no esta corriendo en la consola aparecera un error, pero el servicio seguira funcionando.
______________________

URL http://localhost:8400

### Paths
* [GET] **/fibonacci**

  Parameters:
  number

  Ejemplo:

        http://localhost:8400/fibonacci/?number=10

  Response

        {
            "number": 10,
            "result": 55
        }


* [GET] **/fibonacci/random**

  Ejemplo:

        http://localhost:8400/fibonacci/random

  Response

        {
            "number": 10,
            "result": 55
        }
     