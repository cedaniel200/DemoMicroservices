# Subtract Microservice
_________________________

Para ejecutar el proyecto usar el comando:

    gradlew bootRun

Nota: 
* Si **Eureka** esta corriendo de forma local asegurese de cambiar en el archivo application.yml **eureka** por **localhost**, 
no olvide volver a dejarlo como estaba, ya que, de lo contrario no servira el **comandon docker compose up**.
* Si **Eureka** no esta corriendo en la consola aparecera un error, pero el servicio seguira funcionando.
______________________

URL http://localhost:8300

### Paths
* [GET] **/subtract**

    Parameters:
    numbers

    Ejemplo:
        
        http://localhost:8300/subtract/?numbers=5,2
    
    Response
    
        {
            "numbers": [
              6,
              3,
              ],
              "result": 9
        }
        