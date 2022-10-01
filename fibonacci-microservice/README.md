# Add Fibonacci
_________________________

Para ejecutar el proyecto usar el comando:

    gradlew bootRun

Nota:
* Si quiere que todos los endpoints funcionen correctamente debe tener corriendo el servicio 
que puede descargar en el repositorio https://github.com/cedaniel200/DemoService . Aunque puede funcionar sin este
* Si **Eureka** esta corriendo de forma local asegurese de cambiar en el archivo application.yml **eureka** por **localhost**,
  no olvide volver a dejarlo como estaba, ya que, de lo contrario no servira el **comandon docker compose up**.
* Si **Eureka** no esta corriendo en la consola aparecera un error, pero el servicio seguira funcionando.
______________________