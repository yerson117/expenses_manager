# Expenses Manager API

## Descripción

Expenses Manager es una API RESTful diseñada para gestionar gastos. Permite a los usuarios registrar, actualizar como pagados, y obtener información detallada sobre sus gastos.

## Métodos de la API

### Obtener Gastos del Mes
`GET /expenses/month`
Retorna una lista de los gastos registrados en el mes actual.

### Crear un Gasto
`POST /expenses`
Permite a un usuario crear un nuevo registro de gasto.

### Marcar Gasto como Pagado
`PUT /expenses/{id}/pay`
Marca un gasto específico como pagado según el ID proporcionado.

### Obtener Gastos por Estado
`GET /expenses/state`
Obtiene los gastos filtrados por su estado (pagado/pendiente).

### Calcular Promedio de Gastos por Período
`GET /expenses/avg/{period}`
Calcula el gasto promedio en un período específico (día, semana, quincena).

## Decisiones Técnicas y Arquitectónicas

Al diseñar la API, se optó por una arquitectura RESTful debido a su simplicidad y facilidad de integración con diferentes clientes y plataformas. Se eligió Spring Boot por su amplia comunidad y soporte, así como por la rapidez con la que permite levantar servicios robustos y bien estructurados.

Para la persistencia de datos, se utilizó H2, una base de datos en memoria, para facilitar el desarrollo y pruebas sin la necesidad de una infraestructura de base de datos compleja. Esto permite una configuración y despliegue rápido, ideal en las fases iniciales del desarrollo.

Las decisiones sobre validaciones de entrada y manejo de excepciones se tomaron buscando proporcionar una experiencia de usuario clara y consistente, retornando respuestas informativas en caso de errores.

## Instalación y Uso

Clone el repositorio en su computadora y ejecute el siguiente comando en la raíz del proyecto:

`./gradlew bootRun`

La aplicación se ejecutará en el puerto 8080.

Para realizar las correspondientes pruebas puede usar postman o cualquier otra herramienta de su preferencia, realizando las peticiones a la URL `http://localhost:8080` seguidas de la ruta del endpoint deseado.
