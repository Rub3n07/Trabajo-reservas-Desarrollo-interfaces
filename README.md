Hotel Sol – Gestión de habitaciones

Aplicación de escritorio desarrollada en Java con JavaFX para la asignatura Desarrollo de Interfaces. Lee los datos de un hotel desde un archivo XML y los muestra en una interfaz con una tabla de habitaciones y un buscador por número.

Características
Carga automática de los datos del hotel desde reservas.xml al abrir la aplicación.
Muestra el nombre y la ciudad del hotel en la cabecera.
Tabla con todas las habitaciones: número, tipo, capacidad, precio por noche y estado.
El estado libre se muestra como disponible.
Buscador de habitaciones por número:
Selecciona la habitación en la tabla y se desplaza hasta ella.
Muestra el detalle completo (estado, tipo, capacidad y precio por noche).
Validación de la entrada: campo vacío, texto no numérico, números menores o iguales a cero y habitaciones inexistentes.
Mensajes de error en la propia interfaz (no en consola), incluido el caso de que falte reservas.xml.
Tecnologías
Java
JavaFX (FXML para la interfaz)
XML leído con DOM (DocumentBuilderFactory)
Sin Maven: ejecución directa desde la clase Main
Estructura del proyecto
HotelApp/
├── Main.java              # Punto de entrada: carga el FXML y muestra la ventana
├── HotelController.java   # Controlador: lectura del XML, tabla y búsqueda
├── hotel.fxml             # Diseño de la interfaz
└── reservas.xml           # Datos del hotel y sus habitaciones

Ajusta los nombres si tus archivos se llaman de otra forma.

Formato del XML
xml
<hotel>
    <nombre>Hotel Sol</nombre>
    <ciudad>Madrid</ciudad>
    <habitacion numero="101" estado="libre">
        <tipo>Doble</tipo>
        <capacidad>2</capacidad>
        <precioNoche>85</precioNoche>
    </habitacion>
</hotel>
Requisitos
JDK instalado
JavaFX SDK (descargable desde https://openjfx.io)
Cómo ejecutarlo
Clona el repositorio:
bash
   git clone https://github.com/Rub3n07/Trabajo-reservas-Desarrollo-interfaces.git
Añade las librerías del JavaFX SDK al proyecto (carpeta lib). En Visual Studio Code, con java.project.referencedLibraries en settings.json.
Comprueba que hotel.fxml y reservas.xml están en el classpath (carpeta de recursos).
Ejecuta la clase Main. Si lo haces desde la terminal, añade:
bash
   --module-path <ruta-al-javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml
Uso
Al abrir la aplicación se carga la lista de habitaciones.
Escribe un número de habitación en el campo de texto.
Pulsa Buscar: la habitación se resalta en la tabla y sus datos aparecen debajo.
Capturas
