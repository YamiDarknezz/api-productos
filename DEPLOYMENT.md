# Guía Rápida de Deployment en Render

Esta guía te ayudará a deployar tu API de productos en Render en minutos.

## Pre-requisitos

- Cuenta en [Render](https://render.com/) (gratis)
- Repositorio en GitHub con el código

## Método Rápido: Blueprint (5 minutos)

### Paso 1: Preparar el repositorio
Asegúrate de que tu repositorio tiene el archivo `render.yaml` (ya incluido).

### Paso 2: Deploy con Blueprint

1. Inicia sesión en [Render Dashboard](https://dashboard.render.com/)

2. Click en **"New +"** → **"Blueprint"**

3. Conecta tu cuenta de GitHub si aún no lo has hecho

4. Selecciona el repositorio `api-productos`

5. Render detectará automáticamente el archivo `render.yaml`

6. Revisa la configuración:
   ```
   ✓ PostgreSQL Database (api-productos-db)
   ✓ Web Service (api-productos)
   ✓ Variables de entorno configuradas
   ✓ Health checks activados
   ```

7. Click en **"Apply"**

8. Espera 5-10 minutos mientras Render:
   - Crea la base de datos PostgreSQL
   - Construye la imagen Docker
   - Despliega la aplicación
   - Conecta todo automáticamente

### Paso 3: Verificar el Deployment

Una vez completado, verás:
- ✅ Database: `api-productos-db` (Running)
- ✅ Web Service: `api-productos` (Live)

Tu API estará disponible en:
```
https://api-productos-XXXX.onrender.com
```

Documentación Swagger:
```
https://api-productos-XXXX.onrender.com/swagger-ui.html
```

Health Check:
```
https://api-productos-XXXX.onrender.com/actuator/health
```

## Actualizar el README

Una vez deployado, actualiza la línea 10 del `README.md` con tu URL real:

```markdown
### 🚀 **[Ver API en Vivo](https://api-productos-XXXX.onrender.com)** | 📚 **[Documentación Interactiva (Swagger)](https://api-productos-XXXX.onrender.com/swagger-ui.html)**
```

Haz commit y push del cambio:
```bash
git add README.md
git commit -m "docs: actualizar URL de deployment"
git push
```

## Probar la API

### Desde Swagger UI
1. Abre `https://tu-url.onrender.com/swagger-ui.html`
2. Expande cualquier endpoint
3. Click en "Try it out"
4. Completa los parámetros
5. Click en "Execute"

### Desde cURL

```bash
# Listar productos
curl https://tu-url.onrender.com/api/productos

# Crear un producto
curl -X POST https://tu-url.onrender.com/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Producto de Prueba",
    "descripcion": "Creado desde cURL",
    "precio": 99.99,
    "stock": 5
  }'

# Obtener producto por ID
curl https://tu-url.onrender.com/api/productos/1
```

## Monitoreo

### Ver Logs
1. Render Dashboard → `api-productos` → **"Logs"**
2. Aquí verás todos los logs de Spring Boot en tiempo real

### Ver Métricas
1. Render Dashboard → `api-productos` → **"Metrics"**
2. CPU, memoria, requests, etc.

### Verificar Salud
```bash
curl https://tu-url.onrender.com/actuator/health
```

Debería responder:
```json
{
  "status": "UP"
}
```

## Auto-Deploy

Render está configurado para auto-deploy cuando haces push a `main`:

```bash
git add .
git commit -m "feat: nueva funcionalidad"
git push origin main
```

Render detectará el cambio y automáticamente:
1. Construirá una nueva imagen
2. Desplegará la nueva versión
3. Hará health checks
4. Redirigirá el tráfico

Puedes ver el progreso en **"Events"** de tu servicio.

## Troubleshooting

### La aplicación está "sleeping"
- **Causa**: Plan Free duerme la app después de 15 minutos de inactividad
- **Solución**: El primer request la despertará (puede tardar 30-60 segundos)
- **Prevención**: Usa [UptimeRobot](https://uptimerobot.com/) para hacer ping cada 10 minutos

### Error de conexión a base de datos
1. Verifica las variables de entorno en Render Dashboard
2. Asegúrate de que `DB_HOST` sea el host interno de Render
3. Revisa los logs para ver el error específico

### Build falla
1. Revisa los logs del build en Render
2. Verifica que el `Dockerfile` esté correcto
3. Asegúrate de que `pom.xml` no tenga errores

### La app se reinicia constantemente
1. Revisa el health check path: `/actuator/health`
2. Verifica que el puerto sea `5051`
3. Mira los logs para detectar errores de inicio

## Variables de Entorno Configuradas

Las siguientes variables están configuradas automáticamente por el Blueprint:

```bash
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=5051
DB_HOST=<auto-configurado>
DB_PORT=5432
DB_NAME=gestor_proyectos
DB_USER=<auto-configurado>
DB_PASSWORD=<auto-configurado>
```

## Límites del Plan Free

- **Databases**: 90 días de inactividad antes de eliminarse
- **Web Services**: Se duermen tras 15 min de inactividad
- **Build Time**: Limitado
- **Bandwidth**: 100 GB/mes
- **Uptime**: Sin garantía SLA

Para producción seria, considera el plan pago ($7/mes por servicio).

## Siguientes Pasos

1. ✅ Deploy exitoso
2. 📝 Actualizar README con URL real
3. 🧪 Probar todos los endpoints desde Swagger
4. 📊 Configurar monitoreo externo (opcional)
5. 🔒 Configurar dominio personalizado (opcional)
6. 💳 Considerar plan pago si es para producción

## Recursos

- [Documentación de Render](https://render.com/docs)
- [Render Community](https://community.render.com/)
- [Render Status](https://status.render.com/)

¡Listo! Tu API está en producción y accesible desde cualquier lugar del mundo. 🚀
