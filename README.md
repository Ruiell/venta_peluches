# venta_peluches

Backend de una tienda online de peluches, desarrollado con **Spring Boot** como parte de la asignatura **DSY1104, Desarrollo Fullstack II** (Evaluación Parcial 3). Expone una API REST con operaciones CRUD sobre peluches. Está conectada a una base de datos MySQL y documentada con Swagger.

## Tecnologías

- Java 21
- Spring Boot 4.1.2 (Web MVC, Data JPA, Validation)
- MySQL 8.x
springdoc-openapi 3.1.0 (Swagger UI)
- Maven

## Estructura del proyecto

src/main/java/veta/peluches/com/example/demo/
├── DemoApplication.java
├── controller/PelucheController.java   # REST endpoints
├── service/PelucheService.java         # Lógica del negocio
├── repository/PelucheRepository.java   # Acceso a datos (JPA)
└── model/Peluche.java                  # Entidad
src/main/resources/application.properties


## Requisitos previos

- JDK 21
- MySQL en ejecución (puerto 3306)
- Maven

## Configuración

1. Clona el repositorio:

bash
git clone https://github.com/Ruiell/venta_peluches.git
cd venta_peluches


2. Abre `src/main/resources/application.properties` y cambia el usuario y la contraseña de tu MySQL.

properties
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_peluches?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Santiago
spring.datasource.username=root
spring.datasource.password=


La base de datos `tienda_peluches` y la tabla `peluche` se crean de forma automática cuando se inicia la aplicación.

## Ejecución

Abre `DemoApplication.java` en VS Code y haz clic en **Run**, o hazlo desde la terminal.

bash
mvn spring-boot:run


La aplicación está disponible en `http://localhost:8080`.

## Documentación Swagger

Con la aplicación en ejecución, abre:


http://localhost:8080/swagger-ui.html


## Endpoints

| Método | Ruta                                  | Descripción                         |
|--------|---------------------------------------|-------------------------------------|
| GET    | `/api/peluches`                       | Lista todos los peluches            |
| GET    | `/api/peluches/{id}`                  | Obtiene un peluche por id           |
| POST   | `/api/peluches`                       | Crea un peluche                     |
| PUT    | `/api/peluches/{id}`                  | Actualiza un peluche                |
| DELETE | `/api/peluches/{id}`                  | Elimina un peluche                  |
| GET    | `/api/peluches/categoria?nombre=Osos` | Muestra los peluches de una categoría |

### Ejemplo de cuerpo (POST / PUT)

```json
{
  "nombre": "Oso Panda",
  "descripcion": "Peluche de 80 cm",
  "precio": 24990,
  "stock": 10,
  "categoria": "Osos"
}
```

Validaciones:

nombre: obligatorio
precio: más de 0
stock: no puede ser menor que cero

Si no se cumplen, la API responde con 400 Bad Request. Si el id no existe en GET, PUT o DELETE, responde con 404 Not Found

## Modelo de datos

Campo       | Tipo  |  Descripción                    

 id          (Long)    Identificador autogenerado     
 nombre       (String)  Nombre del peluche             
 descripcion  (String)Descripción                    
 precio       (int)     Precio en pesos chilenos (CLP)
 stock        (int)     Unidades disponibles           
 categoria    (String)  Categoría del peluche          

## Estado del proyecto

- [x] Conexión a MySQL y modelo de datos
- [x] API REST con CRUD documentada en Swagger
- [ ] Autenticación con JWT y roles
- [ ] Integración con el frontend

## Autor

Javier – [@Ruiell](https://github.com/Ruiell)
