# ☕ CafeManager Backend

Backend para la gestión integral de una cafetería.

CafeManager permite administrar productos, ingredientes, stock, compras, ventas, pérdidas y usuarios con autenticación segura mediante JWT.

---

# 🚀 Tecnologías

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate ORM
- Spring Security 6
- JWT
- BCrypt
- MySQL 8
- Swagger OpenAPI 3
- Lombok

---

# 📌 Funcionalidades

## 📦 Inventario

- Gestión de ingredientes.
- Gestión de productos.
- Categorías.
- Recetas.
- Control automático de stock.
- Alertas de stock bajo.

---

## 🛒 Compras

- Registro de compras.
- Gestión de proveedores.
- Actualización automática de inventario.

---

## 💰 Ventas

- Registro de ventas.
- Detalle de ventas.
- Descuento automático de ingredientes.
- Productos más vendidos.

---

## ⚠️ Pérdidas

- Registro de pérdidas.
- Control de costo de pérdidas.
- Actualización de stock.

---

## 📊 Dashboard

Incluye:

- Ventas del día.
- Ingresos diarios.
- Pérdidas.
- Productos vendidos.
- Producto más vendido.
- Ingredientes con stock bajo.

---

# 🔐 Seguridad

Implementado con:

- Spring Security.
- JWT.
- BCrypt.
- Autorización por roles.

Roles disponibles:

| Rol | Descripción |
|---|---|
| ADMIN | Administración completa |
| CAJERO | Gestión de ventas |
| BARISTA | Operaciones de cafetería |
| COMPRAS | Gestión de compras |

---

# 🏗️ Arquitectura

El proyecto utiliza una arquitectura en capas, separando responsabilidades para mantener un código organizado, escalable y fácil de mantener.

## Capas principales

### API
Responsable de la comunicación con el cliente.

Incluye:
- Controllers REST.
- DTOs de entrada (`Request`).
- DTOs de salida (`Response`).

### Application
Contiene la lógica de aplicación y casos de uso.

Incluye:
- Services.
- Mappers.
- Orquestación de operaciones.

### Domain
Contiene el núcleo del negocio.

Incluye:
- Entidades JPA.
- Repositorios.
- Enumeraciones.
- Reglas del dominio.

### Security
Maneja la autenticación y autorización.

Incluye:
- JWT.
- Filtros de seguridad.
- UserDetailsService.
- Configuración de permisos.

### Config
Configuraciones generales de la aplicación.

Incluye:
- Seguridad.
- Swagger/OpenAPI.
- Beans de Spring.

### Exception
Manejo centralizado de errores.

Incluye:
- Excepciones personalizadas.
- Respuestas de error.
- Control global de excepciones.


```text
com.cafemanager.cafemanager

├── api
│   ├── controller
│   ├── request
│   └── response
│
├── application
│   ├── service
│   └── mapper
│
├── domain
│   ├── entity
│   ├── repository
│   └── enums
│
├── security
│
├── config
│
└── exception
```

---

# 📖 Swagger

Documentación disponible en:

http://localhost:8080/swagger-ui/index.html


---

# 🔑 Autenticación

Login:

POST /auth/login

Enviar:

```json
{
  "email": "usuario@email.com",
  "password": "password"
}
```

Utilizar el token recibido:

Authorization: Bearer TOKEN

---

# 📌 Estado del proyecto

Backend v1.0.0

Estado:

✅ API funcional  
✅ Seguridad JWT implementada  
✅ Gestión de usuarios con roles  
✅ Dashboard operativo  
✅ Documentación Swagger completa