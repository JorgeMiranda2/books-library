
Opciones Disponibles:
markdown
Copiar código
***********************************
Elija la acción a través del número
Opciones:
1) Buscar libro por título (desde la API)
2) Listar libros registrados (desde la base de datos)
3) Listar autores registrados (Desde la base de datos)
4) Listar autores vivos en un determinado año (Desde la base de datos)
5) Listar libros por idioma (Desde la base de datos)
0) Salir
***********************************
Descripción de Funcionalidades:
Buscar libro por título (API):
Consulta una API externa para buscar información detallada sobre un libro utilizando su título como parámetro.

Listar libros registrados (DB):
Muestra una lista de los libros almacenados en la base de datos con detalles como título, idioma y autores.

Listar autores registrados (DB):
Enumera los autores disponibles en la base de datos, junto con su información personal y las obras asociadas.

Listar autores vivos en un determinado año:
Filtra y muestra a los autores que estaban vivos en un año específico ingresado por el usuario.

Listar libros por idioma:
Permite filtrar libros según el idioma especificado, utilizando acrónimos como en para inglés o es para español entre otros.

Salir:
Finaliza la ejecución del programa.

📋 Requisitos Previos
Java 17 o superior
Maven para la gestión de dependencias
PostgreSQL como base de datos
⚙️ Instalación y Configuración
Clona este repositorio:

<<bash>>
Copiar código
git clone https://github.com/JorgeMiranda2/books-library.git
Configura las credenciales de la base de datos en el archivo application.properties:

<<properties>>
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/bookDB  
spring.datasource.username=tu_usuario  
spring.datasource.password=tu_password  
Compila y ejecuta el proyecto con Maven:

<<bash>>
Copiar código
mvn spring-boot:run  
Accede al programa desde la terminal y sigue las opciones indicadas.

🛠️ Tecnologías Utilizadas
Java
Spring Boot
PostgreSQL
API externa de libros
📸 Ejemplos y Capturas de Pantalla
🌟 Menú Principal
![menu principal](https://github.com/user-attachments/assets/277e45a3-46b5-4136-8e09-065d25bceae8)

📖 Listado de Libros Registrados
![libros_registrados](https://github.com/user-attachments/assets/52e98877-1708-4210-9ff6-ff73168ad7f1)

🔍 Resultado de Búsqueda de Libro
![ejemplo](https://github.com/user-attachments/assets/dcbb4639-f93d-4745-89ad-e0c97978b1c9)

📂 Estructura del Proyecto
css
Copiar código
src/  
├── main/  
│   ├── java/  
│   │   ├── com.book.book/  
│   │   │   ├── Models/  
│   │   │   ├── DTO/
│   │   │   ├── Repositories/  
│   │   │   ├── Services/  
│   │   │   └── Controllers/  
│   ├── resources/  
│   │   ├── application.properties    
└── test/  
