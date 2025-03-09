# Servicio de Eventos

Este proyecto es un servicio de gestión de eventos desarrollado con Spring Boot y otras tecnologías modernas, siguiendo una arquitectura hexagonal y un enfoque API-First.

## Descripción

El servicio de eventos proporciona funcionalidades para crear, gestionar y consultar eventos. Está diseñado para ser escalable, fácil de mantener y seguir los principios de la arquitectura hexagonal, separando la lógica de negocio del código de infraestructura. Además, se adopta un enfoque API-First, donde la API se define primero utilizando OpenAPI, y luego se genera el código de los controladores automáticamente.

## Tecnologías Utilizadas

* **Spring Boot:** Framework de Java para el desarrollo rápido de aplicaciones web y microservicios.
* **Java 21:** Versión del lenguaje de programación Java utilizada en el proyecto.
* **Maven:** Herramienta de gestión de dependencias y construcción de proyectos.
* **Arquitectura Hexagonal:** Patrón de diseño que separa la lógica de negocio del código de infraestructura, facilitando la mantenibilidad y testabilidad.
* **Lombok:** Librería Java que reduce la cantidad de código boilerplate, generando automáticamente getters, setters, constructores y otros métodos.
* **MapStruct:** Librería para la generación de mapeadores de tipos seguros y de alto rendimiento, facilitando la conversión entre DTOs y entidades de dominio.
* **Spring Security:** Framework de seguridad de Spring para la autenticación y autorización de usuarios.
* **JWT (JSON Web Tokens):** Estándar abierto para la creación de tokens de acceso seguros.
* **OpenAPI:** Especificación para la definición de APIs RESTful. Se utiliza para definir la API del servicio de eventos y generar automáticamente el código de los controladores.
* **MySQL:** Base de datos relacional utilizada para la persistencia de datos.

## Estructura del Proyecto

El proyecto está organizado en los siguientes módulos Maven, siguiendo una arquitectura hexagonal:

* **`servicio-eventos-domain`**: Contiene las entidades de dominio, la lógica de negocio principal y los puertos (interfaces) para la comunicación con la infraestructura.
* **`servicio-eventos-infrastructure`**: Implementa los adaptadores (clases) para los puertos definidos en el dominio, como la persistencia de datos (entidades JPA) y la comunicación con servicios externos.
* **`servicio-eventos-application`**: Actúa como la capa de aplicación, orquestando la lógica de negocio del dominio y exponiendo los endpoints de la API a través de controladores REST. Utiliza DTOs para la comunicación con el exterior, realizando la conversión entre DTOs y entidades de dominio mediante MapStruct.

## Spring Security y JWT

* El servicio de eventos utiliza Spring Security para proteger los endpoints de la API.
* La autenticación se basa en JWT, lo que permite la creación de tokens de acceso seguros que se envían en las cabeceras de las peticiones HTTP.
* Se implementan filtros de seguridad para validar los tokens JWT y autorizar el acceso a los recursos protegidos.

## Enfoque API-First y Generación de Código

* Se adopta un enfoque API-First, donde la API se define primero utilizando OpenAPI.
* A partir de la especificación OpenAPI, se generan automáticamente los modelos de datos (DTOs) y el código de los controladores REST.
* Esto garantiza la coherencia entre la documentación de la API y el código implementado, y facilita la generación de clientes para consumir la API.

## Uso de Lombok y MapStruct

* **Lombok:** Se utiliza para reducir el código boilerplate en las entidades de dominio, DTOs y entidades JPA. Las anotaciones de Lombok generan automáticamente métodos como `getters`, `setters`, `equals`, `hashCode` y constructores.
* **MapStruct:** Se utiliza para generar mapeadores que facilitan la conversión entre DTOs y entidades de dominio, y entre entidades de dominio y entidades JPA. MapStruct genera código de mapeo eficiente y fácil de mantener.

## Configuración de la Base de Datos MySQL

* El servicio de eventos requiere una base de datos MySQL en funcionamiento.
* Debes crear una base de datos llamada `eventos` (o el nombre que hayas configurado en `application.properties`).
* Asegúrate de configurar correctamente la conexión a la base de datos en el archivo `application.properties` del módulo `servicio-eventos-application`.

## Autenticación con JWT

* Para obtener un token JWT, debes tener un usuario registrado en la tabla `users` con una contraseña encriptada.
* Puedes utilizar el endpoint `/auth/login` para obtener un token JWT, proporcionando el nombre de usuario y la contraseña encriptada.
* El token JWT debe ser incluido en la cabecera `Authorization` de las peticiones HTTP a los endpoints protegidos.

## Scripts SQL

### Tabla de Usuarios

```sql
CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL
-- Otros campos si son necesarios
);
```
### Tabla de Eventos

```sql
CREATE TABLE events (
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
description TEXT,
date DATE
-- Otros campos si son necesarios
);
```
## Cómo Ejecutar el Proyecto

1.  Asegúrate de tener instalado Java 21, Maven y MySQL.
2.  Clona el repositorio del proyecto.
3.  Crea la base de datos `eventos` en MySQL y ejecuta los scripts SQL para crear las tablas necesarias.
4.  Navega al directorio raíz del proyecto.
5.  Ejecuta el comando `mvn clean install` para construir el proyecto.
6.  Navega al directorio `servicio-eventos-application`.
7.  Configura la conexión a la base de datos en el archivo `application.properties`.
8.  Ejecuta el comando `mvn spring-boot:run` para iniciar la aplicación.

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1.  Haz un fork del repositorio.
2.  Crea una rama con tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3.  Haz commit de tus cambios (`git commit -am 'Añade nueva funcionalidad'`).
4.  Sube a la rama (`git push origin feature/nueva-funcionalidad`).
5.  Abre un Pull Request.