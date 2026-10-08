# Ferretería La Golondrina — Catálogo de Estados

Aplicación de escritorio en Java (Swing + Spring Boot) que implementa el **Catálogo de Estados** del caso de estudio *Ferretería La Golondrina*. Los registros se guardan en un archivo de texto.

- **Asignatura:** Programación Orientada a Objetos III
- **Actividad:** Unidad 1, Actividad 2 (DPO3_U1_A2_ERVV)
- **Alumno:** Ernesto Velazquez Velazquez
- **Módulo asignado:** Catálogo de Estados

---

## Requisitos

| Herramienta | Versión |
|---|---|
| JDK | 17 o superior |
| Maven | 3.9 o superior (o el wrapper `mvnw` incluido) |
| Spring Boot | 4.1.1 |
| IDE (opcional) | IntelliJ IDEA, Eclipse, NetBeans o VS Code |

No necesita base de datos ni conexión a internet una vez descargadas las dependencias.

---

## Ejecución

Desde el IDE, abre el proyecto como proyecto Maven y ejecuta la clase:

```
mx.golondrina.ferreteria.FerreteriaApplication
```

Desde la terminal, en la raíz del proyecto:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

También puedes generar el `.jar` y ejecutarlo:

```bash
./mvnw clean package
java -jar target/ferreteria-0.0.1-SNAPSHOT.jar
```

---

## Uso

1. **Pantalla de inicio.** Muestra el nombre de la empresa, el módulo y una imagen alusiva a las 32 entidades federativas. La barra de título incluye el nombre del módulo y el nombre del alumno.
2. **Menú principal.**
    - `Catálogos > Estados` (Ctrl+E) abre el formulario del catálogo.
    - `Archivo > Inicio` (Ctrl+H) regresa a la pantalla de inicio.
    - `Archivo > Salir` (Ctrl+Q) cierra la aplicación tras pedir confirmación. La **X** de la ventana hace lo mismo.
3. **Formulario de estados.** Captura **Clave**, **Nombre del estado** y **Capital**.
    - **Guardar** valida los campos. Si alguno está vacío, muestra un mensaje de error indicando cuál. Si son correctos, guarda el registro en `estados.txt`, lo agrega a la tabla y muestra una confirmación con los datos ingresados. Presionar Enter en cualquier campo equivale a presionar Guardar.
    - **Limpiar** vacía los campos.
    - **Regresar al menú principal** limpia el formulario y vuelve a la pantalla de inicio.
    - La tabla **Estados registrados** muestra lo que ya existe en el archivo, incluso entre ejecuciones.

### Validaciones

- Ningún campo puede estar vacío ni contener solo espacios.
- La clave admite como máximo 4 caracteres y se guarda en mayúsculas.
- El nombre y la capital admiten como máximo 60 caracteres.
- No se permite el carácter `|`, porque es el separador del archivo.
- No se permiten claves ni nombres de estado repetidos.
- Se eliminan los espacios al inicio y al final, así como los espacios dobles.

---

## Archivo de datos

Los estados se guardan en **`estados.txt`**, en la carpeta desde donde se ejecuta la aplicación. Al ejecutar desde el IDE o con Maven, esa carpeta es la raíz del proyecto. El archivo se crea automáticamente al guardar el primer registro.

El formato es una línea por estado, en UTF-8, con los campos separados por `|`:

```
CLAVE|NOMBRE|CAPITAL
```

Ejemplo:

```
JAL|Jalisco|Guadalajara
NL|Nuevo León|Monterrey
OAX|Oaxaca|Oaxaca de Juárez
```

Para usar otra ubicación, agrega esta línea a `src/main/resources/application.properties`:

```properties
catalogo.estados.archivo=data/estados.txt
```

---

## Estructura del proyecto

```
src/main/java/mx/golondrina/ferreteria/
├── FerreteriaApplication.java      Punto de entrada: arranca Spring sin modo headless y abre la ventana
├── model/
│   └── Estado.java                 Entidad (record) y conversión a/desde una línea de texto
├── repository/
│   └── EstadoRepository.java       Lectura y escritura del archivo estados.txt
├── service/
│   ├── EstadoService.java          Reglas de negocio y validaciones
│   └── ValidacionException.java    Excepción para datos inválidos
└── ui/
    ├── AppInfo.java                Textos fijos: empresa, módulo, alumno, título
    ├── Tema.java                   Paleta de colores
    ├── VentanaPrincipal.java       JFrame con menú y navegación por CardLayout
    ├── PanelInicio.java            Pantalla de inicio con imagen
    └── PanelEstados.java           Formulario del catálogo y botón Guardar

src/main/resources/
├── application.properties
└── imagenes/estados.png            Imagen de la pantalla de inicio
```

### Diseño

El proyecto está organizado en capas, y cada clase tiene una sola responsabilidad:

- **Modelo:** `Estado` representa un registro del catálogo.
- **Repositorio:** `EstadoRepository` encapsula el acceso al archivo de texto.
- **Servicio:** `EstadoService` valida los datos y coordina el guardado; la interfaz nunca escribe el archivo directamente.
- **Interfaz:** las clases de `ui` solo muestran datos y mensajes.

Spring se encarga de crear el repositorio y el servicio e inyectarlos por constructor en `VentanaPrincipal`. La ventana es `@Lazy` para construirse dentro del hilo de eventos de Swing (EDT).

---

## Pruebas realizadas

| Caso | Resultado esperado |
|---|---|
| Guardar con todos los campos vacíos | Mensaje de error que lista los campos vacíos |
| Guardar con solo un campo vacío | Mensaje de error que indica ese campo |
| Guardar un estado válido | Confirmación con los datos; nueva línea en `estados.txt` |
| Guardar una clave ya registrada | Mensaje de error por clave duplicada |
| Capturar acentos (`Nuevo León`) | Se guarda y se muestra correctamente (UTF-8) |
| Cerrar y volver a abrir la app | La tabla muestra los registros guardados |
| Regresar al menú principal | Vuelve a la pantalla de inicio con el formulario limpio |
| Salir desde el menú o la X | Pide confirmación y cierra la aplicación |