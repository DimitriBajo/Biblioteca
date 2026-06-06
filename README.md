<<<<<<< HEAD
=======

>>>>>>> 1c47f221113125ddb9cea290bb7ddb7577b13129
# Biblioteca API REST

API REST desarrollada con Java, Spring Boot, Hibernate y MySQL para la gestión de una biblioteca.

## Características

* Gestión de libros
* Gestión de usuarios
* Gestión de préstamos
* Operaciones CRUD
* Persistencia de datos con MySQL
* Uso de Hibernate/JPA
* Arquitectura por capas (Controller, Service, Repository)
* Regla de negocio para impedir prestar un libro ya prestado

## Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Git y GitHub

## Estructura del proyecto

src/main/java/com/project

├── controller

│   ├── LibroController

│   ├── UsuarioController

│   └── PrestamoController

├── service

│   ├── LibroService

│   ├── UsuarioService

│   └── PrestamoService

├── repository

│   ├── LibroRepository

│   ├── UsuarioRepository

│   └── PrestamoRepository

├── entity

│   ├── Libro

│   ├── Usuario

│   └── Prestamo

└── BibliotecaApplication

## Modelo de datos

### Libro

* id
* titulo
* autor
* isbn (único)
* disponible

### Usuario

* id
* nombre
* email (único)

### Prestamo

* id
* fechaPrestamo
* fechaDevolucion
* usuario
* libro

## Relaciones

* Un usuario puede tener varios préstamos.
* Un libro puede tener varios préstamos a lo largo del tiempo.
* Cada préstamo pertenece a un único usuario.
* Cada préstamo pertenece a un único libro.

## Regla de negocio implementada

Un libro no puede ser prestado si ya se encuentra prestado.

Antes de crear un préstamo, el sistema verifica la disponibilidad del libro y lanza una excepción si el libro no está disponible.

## Configuración de la base de datos

Crear la base de datos:

```sql
CREATE DATABASE biblioteca;
```

Configurar las credenciales en:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Ejecución

Compilar el proyecto:

```bash
mvn clean install
```

Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

## Ejemplos de endpoints

### Crear libro

POST /libros

```json
{
  "titulo": "Clean Code",
  "autor": "Robert Martin",
  "isbn": "9780132350884",
  "disponible": true
}
```

### Obtener libros

GET /libros

### Crear usuario

POST /usuarios

```json
{
  "nombre": "Dimitri",
  "email": "dimitri@email.com"
}
```

### Obtener usuarios

GET /usuarios

### Crear préstamo

POST /prestamos

```json
{
  "fechaPrestamo": "2026-05-31",
  "fechaDevolucion": "2026-06-15",
  "usuario": {
    "id": 1
  },
  "libro": {
    "id": 1
  }
}
```

### Obtener préstamos

GET /prestamos

## Autor

Proyecto desarrollado como práctica de desarrollo backend utilizando Spring Boot, Hibernate y MySQL.
