## Documentacion

<a href="https://docs.google.com/document/d/1JkhDNbItAxBbVtyZbTw9DGZ1tNf_hTuK/edit" target="_blank">
  <img src="https://img.shields.io/badge/DOCUMENTACION-Google%20Drive-34A853?style=for-the-badge&logo=google-drive&logoColor=white" alt="Documento Drive">
</a>

<br><br>

Aquí se encuentra la sustentación del proyecto **PIZZERIA** desarrollado con **MySQL**, donde se explica el modelado, la implementación y las funcionalidades clave de la base de datos.


<br>

---


# Gestión de Ventas — API REST con Spring Boot

**Autor:** Luis Ángel Gelvez Delgado
**Docente:** David Domínguez

---

## Descripción

API REST para la gestión de productos y ventas, desarrollada con Spring Boot 3.5, Java 21, JPA/Hibernate con MySQL y autenticación JWT. Incluye documentación interactiva con Swagger UI y un frontend HTML embebido para pruebas.

---

## Estructura del Proyecto

```
SpringBoot_s1_GelvezLuis/
├── src/
│   └── main/
│       ├── java/com/s1/gestion_ventas/
│       │   ├── auth/
│       │   │   ├── AuthController.java         # Endpoint POST /auth/login
│       │   │   ├── LoginRequest.java            # Record: username + password
│       │   │   └── LoginResponse.java           # Record: token
│       │   ├── config/
│       │   │   ├── JwtFilter.java               # Valida el token en cada petición
│       │   │   ├── JwtService.java              # Genera y valida tokens JWT
│       │   │   ├── OpenApiConfig.java           # Configuración de Swagger
│       │   │   └── SecurityConfig.java          # Reglas de acceso y CORS
│       │   ├── controller/
│       │   │   ├── ProductoController.java      # CRUD de productos
│       │   │   └── VentaController.java         # CRUD de ventas
│       │   ├── dto/
│       │   │   ├── request/
│       │   │   │   ├── ProductoRequestDTO.java
│       │   │   │   ├── VentaRequestDTO.java
│       │   │   │   └── DetalleVentaRequestDTO.java
│       │   │   └── response/
│       │   │       ├── ProductoResponseDTO.java
│       │   │       ├── VentaResponseDTO.java
│       │   │       └── DetalleVentaResponseDTO.java
│       │   ├── exception/
│       │   │   ├── BusinessRuleException.java
│       │   │   ├── ErrorResponse.java
│       │   │   └── GlobalExceptionHandler.java
│       │   ├── mapper/
│       │   │   ├── ProductoMapper.java
│       │   │   ├── VentaMapper.java
│       │   │   └── DetalleVentaMapper.java
│       │   ├── model/
│       │   │   ├── Producto.java
│       │   │   ├── Venta.java
│       │   │   └── DetalleVenta.java
│       │   ├── repository/
│       │   │   ├── ProductoRepository.java
│       │   │   ├── VentaRepository.java
│       │   │   └── DetalleVentaRepository.java
│       │   ├── service/
│       │   │   ├── ProductoService.java
│       │   │   ├── VentaService.java
│       │   │   └── impl/
│       │   │       ├── ProductoServiceImpl.java
│       │   │       └── VentaServiceImpl.java
│       │   └── GestionVentasApplication.java
│       └── resources/
│           ├── application.properties
│           └── static/
│               ├── index.html
│               ├── css/styles.css
│               ├── js/app.js
│               └── view/vistas.html
├── pom.xml
└── mvnw / mvnw.cmd
```

---

## Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.5.11 | Framework principal |
| Spring Data JPA | - | Acceso a base de datos |
| Spring Security | - | Autenticación y autorización |
| MySQL | 8+ | Base de datos relacional |
| JJWT | 0.11.5 | Tokens JWT |
| Lombok | - | Reducción de código repetitivo |
| SpringDoc OpenAPI | 2.8.15 | Swagger UI |

---

## Requisitos Previos

- Java 21 o superior
- MySQL 8 o superior
- Maven (o usar el wrapper `mvnw` incluido en el proyecto)
- Postman, Swagger UI o cualquier cliente HTTP para probar el API

---

## Paso 1 — Crear la Base de Datos

Conéctate a MySQL y ejecuta el siguiente script:

```sql
CREATE DATABASE IF NOT EXISTS gestion_ventas;
USE gestion_ventas;

CREATE TABLE producto (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(50)   NOT NULL,
    descripcion  VARCHAR(100)  NOT NULL,
    precio       DECIMAL(10,2) NOT NULL,
    stock        INT           NOT NULL
);

CREATE TABLE venta (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha  DATETIME      NOT NULL,
    total  DECIMAL(10,2) NOT NULL
);

CREATE TABLE detalle_venta (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad         INT           NOT NULL,
    precio_unitario  DECIMAL(10,2) NOT NULL,
    subtotal         DECIMAL(10,2) NOT NULL,
    venta_id         BIGINT        NOT NULL,
    producto_id      BIGINT        NOT NULL,
    CONSTRAINT fk_venta    FOREIGN KEY (venta_id)    REFERENCES venta(id),
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES producto(id)
);
```

---

## Paso 2 — Configurar application.properties

Abre `src/main/resources/application.properties` y ajusta los valores:

```properties
# Cambia localhost si tu MySQL está en otro servidor
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_ventas

# Usuario de MySQL
spring.datasource.username=root

# Contraseña de MySQL — cambia esto por la tuya
spring.datasource.password=TU_CONTRASEÑA

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# none = JPA no toca las tablas (las creamos manualmente en el paso anterior)
spring.jpa.hibernate.ddl-auto=none

# Muestra las consultas SQL en consola, útil para depuración
spring.jpa.show-sql=true

spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=true

# Puerto de la aplicación
server.port=8080
```

### Cambiar el puerto

Si el puerto 8080 está ocupado, modifica la última línea:

```properties
server.port=9090
```

Después del cambio, todas las URLs usan el nuevo puerto, por ejemplo: `http://localhost:9090/swagger-ui/index.html`

---

## Paso 3 — Ejecutar el Proyecto

**Con el Maven Wrapper incluido (no necesitas Maven instalado):**

```bash
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

**Con Maven instalado:**

```bash
mvn spring-boot:run
```

**Compilar y ejecutar el WAR:**

```bash
./mvnw clean package -DskipTests
java -jar target/gestion_ventas-0.0.1-SNAPSHOT.war
```

Cuando arranque correctamente verás en consola:

```
Tomcat started on port(s): 8080 (http)
Started GestionVentasApplication in X.XXX seconds
```

---

## Paso 4 — Autenticarse y Obtener el Token JWT

Todos los endpoints del API requieren un token JWT, excepto `/auth/login`.

**Petición:**

```
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "1234"
}
```

**Respuesta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi..."
}
```

A partir de ese momento, incluye el token en el header de cada petición:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

El token es válido por 24 horas. Pasado ese tiempo debes volver a hacer login.

---

## Endpoints

### Autenticación

| Método | Ruta | Descripción | Requiere token |
|---|---|---|---|
| POST | `/auth/login` | Obtener token JWT | No |

### Productos

| Método | Ruta | Descripción | Requiere token |
|---|---|---|---|
| POST | `/api/producto` | Crear producto | Sí |
| GET | `/api/producto` | Listar todos | Sí |
| GET | `/api/producto/{id}` | Buscar por ID | Sí |
| PUT | `/api/producto/{id}` | Actualizar | Sí |
| DELETE | `/api/producto/{id}` | Eliminar | Sí |

Body para crear o actualizar:

```json
{
  "nombre": "Laptop Gamer",
  "descripcion": "Laptop con 16GB RAM y SSD 512GB",
  "precio": 1500.00,
  "stock": 10
}
```

### Ventas

| Método | Ruta | Descripción | Requiere token |
|---|---|---|---|
| POST | `/api/venta` | Registrar venta | Sí |
| GET | `/api/venta` | Listar todas | Sí |
| GET | `/api/venta/{id}` | Buscar por ID | Sí |
| DELETE | `/api/venta/{id}` | Eliminar | Sí |

Body para crear una venta:

```json
{
  "detalles": [
    { "productoId": 1, "cantidad": 2 },
    { "productoId": 3, "cantidad": 1 }
  ]
}
```

Respuesta de ejemplo:

```json
{
  "id": 1,
  "fecha": "2026-03-19T10:30:00",
  "total": 3000.00,
  "detalles": [
    {
      "id": 1,
      "cantidad": 2,
      "precioUnitario": 1500.00,
      "subtotal": 3000.00,
      "producto": {
        "id": 1,
        "nombre": "Laptop Gamer",
        "descripcion": "Laptop con 16GB RAM y SSD 512GB",
        "precio": 1500.00,
        "stock": 10
      }
    }
  ]
}
```

---

## Swagger UI

Accede a la documentación interactiva desde el navegador:

```
http://localhost:8080/swagger-ui/index.html
```

Para autenticarte en Swagger: haz clic en el botón **Authorize**, en el campo escribe `Bearer TU_TOKEN` y confirma. Todos los endpoints protegidos quedarán disponibles para probar directamente desde ahí.

---

## Frontend de Pruebas

El proyecto incluye una vista HTML para hacer pruebas básicas sin necesidad de Postman:

```
http://localhost:8080/view/vistas.html
```

---

## Manejo de Errores

| Código | Descripción | errorCode |
|---|---|---|
| 400 | Datos inválidos | `VALIDATION_FAILED` |
| 400 | Regla de negocio (ej: credenciales incorrectas) | `BUSINESS_RULE_VIOLATION` |
| 404 | Recurso no encontrado | `RESOURCE_NOT_FOUND` |
| 500 | Error interno | `INTERNAL_SERVER_ERROR` |

Ejemplo de error de validación:

```json
{
  "timestamp": "2026-03-19T10:00:00",
  "status": 400,
  "errorCode": "VALIDATION_FAILED",
  "errors": {
    "nombre": "el nombre no puede estar vacío",
    "precio": "el precio no puede ser nulo"
  }
}
```

---
