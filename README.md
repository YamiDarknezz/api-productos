# API de Gestión de Productos

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)

### 🚀 **[Ver API en Vivo](https://api-productos-7euo.onrender.com)** | 📚 **[Documentación Interactiva (Swagger)](https://api-productos-7euo.onrender.com/swagger-ui/index.html)**

> ⏱️ **Nota**: Esta API está alojada en el plan gratuito de Render. Si la aplicación está inactiva, el primer acceso puede tardar 30-60 segundos mientras el servidor se activa. Posteriores peticiones serán instantáneas. ¡Gracias por tu paciencia!

</div>

---

API REST desarrollada con Spring Boot para la gestión de productos. Soporta operaciones CRUD completas, paginación y documentación interactiva con Swagger/OpenAPI.

## Características

- CRUD completo de productos
- Paginación y ordenamiento
- Documentación interactiva con Swagger UI
- Selector de servidores en Swagger (producción/desarrollo)
- CORS configurado globalmente para integración con frontends
- Soporte para múltiples entornos (desarrollo/producción)
- Integración continua con GitHub Actions
- Deploy automático a Docker Hub y Render
- Base de datos H2 para desarrollo (sin configuración adicional)
- PostgreSQL para producción
- Health checks para monitoreo

## Tecnologías

- Java 21
- Spring Boot 3.5.3
  - Spring Data JPA
  - Spring Web (con configuración CORS global)
  - Spring Boot Actuator (health checks)
- PostgreSQL (producción)
- H2 Database (desarrollo)
- Springdoc OpenAPI 2.7.0 (documentación interactiva)
- Lombok
- Maven
- Docker (multistage build)

## Requisitos Previos

- Java 21 o superior
- Maven 3.9+
- Docker (opcional, para ejecución en contenedor)
- PostgreSQL (solo para entorno de producción)

## Configuración

### 1. Clonar el repositorio

```bash
git clone <tu-repositorio>
cd api-productos
```

### 2. Configurar variables de entorno

Copia el archivo de ejemplo y configura según tu entorno:

```bash
cp .env.example .env
```

Edita el archivo `.env` con tus valores:

```bash
# Desarrollo (H2 en memoria - sin base de datos externa)
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=5051

# Producción (PostgreSQL)
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=5051
DB_HOST=localhost
DB_PORT=5432
DB_NAME=gestor_proyectos
DB_USER=tu_usuario
DB_PASSWORD=tu_password
```

## Perfiles de Ejecución

### Desarrollo (`dev`)

- Base de datos: **H2 en memoria**
- No requiere PostgreSQL instalado
- Datos se reinician al cerrar la aplicación
- Consola H2 disponible en: `http://localhost:5051/h2-console`
  - JDBC URL: `jdbc:h2:mem:productos_db`
  - Usuario: `sa`
  - Password: (vacío)

### Producción (`prod`)

- Base de datos: **PostgreSQL**
- Requiere configurar variables de entorno para la conexión
- Datos persistentes

## Ejecución Local

### Opción 1: Maven (Desarrollo)

```bash
# Modo desarrollo (H2)
export SPRING_PROFILES_ACTIVE=dev
./mvnw spring-boot:run
```

```bash
# Modo producción (PostgreSQL)
export SPRING_PROFILES_ACTIVE=prod
./mvnw spring-boot:run
```

### Opción 2: JAR Compilado

```bash
# Compilar
./mvnw clean package -DskipTests

# Ejecutar en modo desarrollo
java -jar target/api-productos-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

# Ejecutar en modo producción
java -jar target/api-productos-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Opción 3: Docker

```bash
# Construir imagen
docker build -t api-productos .

# Ejecutar contenedor (desarrollo)
docker run -p 5051:5051 -e SPRING_PROFILES_ACTIVE=dev api-productos

# Ejecutar contenedor (producción con PostgreSQL)
docker run -p 5051:5051 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=gestor_proyectos \
  -e DB_USER=postgres \
  -e DB_PASSWORD=password \
  api-productos
```

## Documentación de la API

Una vez iniciada la aplicación, accede a la documentación interactiva:

### Swagger UI (Interfaz Interactiva)

**Local (Desarrollo)**:
```
http://localhost:5051/swagger-ui.html
```

**Producción (Render)**:
```
https://api-productos-7euo.onrender.com/swagger-ui/index.html
```

#### Funcionalidades de Swagger

- Ver todos los endpoints disponibles
- Probar las operaciones directamente desde el navegador
- Ver los modelos de datos
- Consultar códigos de respuesta
- Cambiar entre diferentes servidores (producción/desarrollo)

#### Selector de Servidores

En la parte superior de Swagger UI encontrarás un dropdown **"Servers"** con dos opciones:

1. **Servidor de Producción (Render)** - `https://api-productos-7euo.onrender.com`
   - Selecciona esta opción cuando accedas desde Render
   - Todas las peticiones irán al servidor de producción

2. **Servidor Local (Desarrollo)** - `http://localhost:5051`
   - Selecciona esta opción cuando desarrolles localmente
   - Las peticiones irán a tu servidor local

**Importante**: Asegúrate de seleccionar el servidor correcto antes de probar los endpoints.

### OpenAPI JSON

**Local**:
```
http://localhost:5051/api-docs
```

**Producción**:
```
https://api-productos-7euo.onrender.com/api-docs
```

## Endpoints Disponibles

| Método | Endpoint                  | Descripción                      |
| ------ | ------------------------- | -------------------------------- |
| GET    | `/api/productos`          | Listar todos los productos       |
| GET    | `/api/productos/{id}`     | Obtener un producto por ID       |
| POST   | `/api/productos`          | Crear un nuevo producto          |
| PUT    | `/api/productos/{id}`     | Actualizar un producto existente |
| DELETE | `/api/productos/{id}`     | Eliminar un producto             |
| GET    | `/api/productos/paginado` | Listar productos con paginación  |

### Ejemplo de Paginación

```bash
# Obtener página 0, con 10 elementos, ordenados por nombre ascendente
GET /api/productos/paginado?page=0&size=10&sort=nombre,asc
```

## CORS y Integración con Frontends

Esta API tiene **CORS configurado globalmente**, lo que permite su integración con aplicaciones frontend desde cualquier origen.

### Configuración CORS

La configuración se encuentra en `src/main/java/com/darkhub/api/api_productos/config/CorsConfig.java`:

- ✅ **Permite todos los orígenes** (`allowedOriginPatterns: *`)
- ✅ **Métodos soportados**: GET, POST, PUT, DELETE, OPTIONS, PATCH
- ✅ **Headers permitidos**: Authorization, Content-Type, Accept, etc.
- ✅ **Credenciales**: Habilitadas
- ✅ **Preflight cache**: 1 hora

### Uso desde Frontend

Puedes consumir esta API desde cualquier aplicación frontend (React, Angular, Vue, etc.):

```javascript
// Ejemplo con fetch (JavaScript vanilla)
fetch('https://api-productos-7euo.onrender.com/api/productos')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));

// Ejemplo con axios
import axios from 'axios';

const API_URL = 'https://api-productos-7euo.onrender.com/api/productos';

// Obtener productos
const productos = await axios.get(API_URL);

// Crear producto
const nuevoProducto = await axios.post(API_URL, {
  nombre: 'Producto Nuevo',
  descripcion: 'Descripción del producto',
  precio: 99.99,
  stock: 10
});
```

## Ejemplos de Uso

### Crear un Producto

```bash
curl -X POST http://localhost:5051/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop",
    "descripcion": "Laptop HP 15 pulgadas",
    "precio": 799.99,
    "stock": 10
  }'
```

### Obtener Todos los Productos

```bash
curl http://localhost:5051/api/productos
```

### Actualizar un Producto

```bash
curl -X PUT http://localhost:5051/api/productos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop Actualizada",
    "descripcion": "Laptop HP 15 pulgadas - Actualizada",
    "precio": 699.99,
    "stock": 15
  }'
```

### Eliminar un Producto

```bash
curl -X DELETE http://localhost:5051/api/productos/1
```

## CI/CD con GitHub Actions

Este proyecto incluye integración continua configurada con GitHub Actions que:

1. Se activa automáticamente con cada `push` a la rama `main`
2. Compila el proyecto con Maven
3. Construye una imagen Docker para arquitectura ARM64
4. Publica la imagen en Docker Hub: `yamidarknezz/api-productos:latest`

### Configurar Secrets en GitHub

Para que funcione el workflow, configura estos secrets en tu repositorio:

```
DOCKERHUB_USER: tu_usuario_dockerhub
DOCKERHUB_TOKEN: tu_token_dockerhub
```

### Descargar Imagen desde Docker Hub

```bash
docker pull yamidarknezz/api-productos:latest
```

## Estructura del Proyecto

```
api-productos/
├── src/
│   ├── main/
│   │   ├── java/com/darkhub/api/api_productos/
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java           # Configuración CORS global
│   │   │   │   ├── DatosIniciales.java       # Datos de prueba iniciales
│   │   │   │   └── OpenApiConfig.java        # Configuración Swagger/OpenAPI
│   │   │   ├── controlador/
│   │   │   │   └── ProductoControlador.java  # Endpoints REST con anotaciones OpenAPI
│   │   │   ├── modelo/
│   │   │   │   └── Producto.java             # Entidad JPA
│   │   │   ├── repositorio/
│   │   │   │   └── ProductoRepositorio.java  # Acceso a datos
│   │   │   ├── servicio/
│   │   │   │   └── ProductoServicio.java     # Lógica de negocio
│   │   │   └── excepcion/
│   │   │       └── RecursoNoEncontradoExcepcion.java
│   │   └── resources/
│   │       ├── application.properties         # Configuración base
│   │       ├── application-dev.properties     # Perfil desarrollo (H2)
│   │       └── application-prod.properties    # Perfil producción (PostgreSQL + Render URL)
│   └── test/
├── .github/workflows/
│   └── deploy.yml                             # GitHub Actions CI/CD
├── Dockerfile                                 # Configuración Docker multistage
├── docker-compose.yml                         # Orquestación local (app + PostgreSQL)
├── .dockerignore                              # Archivos ignorados en build Docker
├── render.yaml                                # Configuración Render Blueprint
├── DEPLOYMENT.md                              # Guía de deployment en Render
├── .env.example                               # Ejemplo variables de entorno
├── .gitignore                                 # Archivos ignorados por Git
└── pom.xml                                    # Configuración Maven
```

## Deployment en Render

Este proyecto está configurado para deployarse fácilmente en **Render** con PostgreSQL incluido.

### Opción 1: Deployment Automático con Blueprint (Recomendado)

Este repositorio incluye un archivo `render.yaml` que configura automáticamente todo:

1. Ve a [Render Dashboard](https://dashboard.render.com/)
2. Click en **"New +"** → **"Blueprint"**
3. Conecta tu repositorio de GitHub
4. Render detectará automáticamente el archivo `render.yaml` y creará:
   - Base de datos PostgreSQL
   - Web Service con todas las variables de entorno configuradas
   - Variable `RENDER_EXTERNAL_URL` para Swagger (URL pública automática)
   - Health checks en `/actuator/health`
   - Conexión automática entre la aplicación y la base de datos
5. Click en **"Apply"** y espera a que se complete el deployment (5-10 minutos)

**Ventajas del Blueprint**:
- Configuración automática de todas las variables de entorno
- Swagger configurado con la URL correcta de producción
- No necesitas copiar credenciales manualmente

### Opción 2: Deployment Manual

Si prefieres configurar manualmente o necesitas más control:

#### 1. Crear PostgreSQL Database

1. Ve a [Render Dashboard](https://dashboard.render.com/)
2. Click en **"New +"** → **"PostgreSQL"**
3. Configura:
   - **Name**: `api-productos-db`
   - **Database**: `gestor_proyectos`
   - **User**: (deja el generado automáticamente)
   - **Region**: Elige la más cercana a tus usuarios
   - **Plan**: Free (o el que prefieras)
4. Click en **"Create Database"**
5. **Guarda las credenciales** que aparecen (Internal Database URL)

#### 2. Crear Web Service

1. En Render Dashboard, click **"New +"** → **"Web Service"**
2. Conecta tu repositorio de GitHub
3. Configura el servicio:
   - **Name**: `api-productos`
   - **Region**: La misma que la base de datos
   - **Branch**: `main`
   - **Runtime**: `Docker`
   - **Plan**: Free (o el que prefieras)

#### 3. Configurar Variables de Entorno

En la sección **"Environment Variables"**, agrega:

```
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=5051
DB_HOST=<internal-db-host>
DB_PORT=5432
DB_NAME=gestor_proyectos
DB_USER=<db-user>
DB_PASSWORD=<db-password>
```

> **Tip**: Render te proporciona una "Internal Database URL" en el formato:
> `postgresql://user:password@host:5432/database`
>
> Extrae los valores y úsalos en las variables de entorno.

#### 4. Configurar Health Check (Opcional pero Recomendado)

En **"Settings"** → **"Health Check Path"**:

```
/actuator/health
```

#### 5. Deploy

1. Click en **"Create Web Service"**
2. Render automáticamente:
   - Clonará tu repositorio
   - Construirá la imagen Docker
   - Desplegará la aplicación
   - La conectará a PostgreSQL

#### 6. Acceder a tu API

Una vez desplegado, tu API estará disponible en:

```
https://tu-app.onrender.com
```

Y la documentación Swagger en:

```
https://tu-app.onrender.com/swagger-ui.html
```

### Auto-Deploy desde GitHub

Render detecta automáticamente los cambios en tu rama `main` y redespliega la aplicación. No necesitas configuración adicional.

### Monitoreo

- **Logs**: Render Dashboard → Tu servicio → "Logs"
- **Métricas**: Render Dashboard → Tu servicio → "Metrics"
- **Health**: `https://tu-app.onrender.com/actuator/health`

### Consideraciones del Plan Free

- La aplicación se "duerme" después de 15 minutos de inactividad
- El primer request después de "despertar" puede tardar 30-60 segundos
- Para evitar esto, considera un plan pago o usa un servicio de ping (ej: UptimeRobot)

### Actualizar la URL en el README

Una vez desplegado, actualiza la línea 10 del README con tu URL real:

```markdown
### 🚀 **[Ver API en Vivo](https://tu-app-real.onrender.com)** | 📚 **[Documentación Interactiva (Swagger)](https://tu-app-real.onrender.com/swagger-ui.html)**
```

---

## Deploy Alternativo (Docker Hub)

El proyecto también se despliega automáticamente a Docker Hub mediante GitHub Actions. Puedes ejecutarlo en cualquier servidor compatible con Docker:

```bash
docker run -p 5051:5051 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_HOST=tu_host \
  -e DB_NAME=tu_base_datos \
  -e DB_USER=tu_usuario \
  -e DB_PASSWORD=tu_password \
  yamidarknezz/api-productos:latest
```

## Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-caracteristica`)
3. Commit tus cambios (`git commit -m 'Agregar nueva característica'`)
4. Push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia que especifiques.

## Autor

**DarkHub API**

- GitHub: [@yamidarknezz](https://github.com/yamidarknezz)
- Docker Hub: [yamidarknezz/api-productos](https://hub.docker.com/r/yamidarknezz/api-productos)

## Soporte

Si encuentras algún problema o tienes sugerencias, abre un issue en el repositorio de GitHub.
