# Gestion Tienda de Videojuegos

Aplicacion de consola desarrollada en Java para gestionar una tienda de videojuegos. Permite manejar el catalogo de juegos, los clientes y los pedidos, con conexion a base de datos MySQL mediante JDBC.
¡

## ¿Que hace la aplicacion?

- Ver y buscar videojuegos por plataforma o categoria
- Añadir, modificar y eliminar videojuegos
- Gestionar clientes (añadir, buscar, modificar, eliminar)
- Ver pedidos, crear nuevos y cambiar su estado
- Controlar el stock de la tienda
¡

## Tecnologias utilizadas

- Java 17+
- MySQL 8.0
- JDBC (mysql-connector-j-9.6.0)
- Git / GitHub
¡

## Estructura del repositorio


Proyecto-intermodular-Adrian-Delgado-DAM/
 src/
    db/
         Conexion.java
    model/
       Videojuego.java
       Cliente.java
         Pedido.java
    service/
       VideojuegoService.java
       ClienteService.java
         PedidoService.java
    Main.java
      mysql-connector-j-9.6.0.jar
 sql/
    crear_tablas.sql
    insertar_datos.sql
      consultas.sql
 xml/
    catalogo.xml
      esquema.xsd
 diagrams/
      diagrama_er.png
   docs/
     sistemas/
          informe_sistemas.md
       empleabilidad/
           empleabilidad.md


## Instalacion y ejecucion

### Requisitos previos
- Java 17 o superior instalado
- MySQL 8.0 instalado y arrancado

### Pasos

1. Clonar el repositorio:
   
   git clone https://github.com/AdrianDG007/Proyecto-intermodular-Adrian-Delgado-DAM
   

2. Crear la base de datos:
   
   mysql -u root -p < sql/crear_tablas.sql
   

3. Insertar los datos de ejemplo:
   
   mysql -u root -p < sql/insertar_datos.sql
   

4. Abrir `src/db/Conexion.java` y cambiar la contraseña de MySQL:
   java
   private static final String PASSWORD = "tu_contraseña";
   

5. Compilar (desde la carpeta `/src`):
   
   javac -cp mysql-connector-j-9.6.0.jar -d . db/Conexion.java model/Videojuego.java model/Cliente.java model/Pedido.java service/VideojuegoService.java service/ClienteService.java service/PedidoService.java Main.java
   

6. Ejecutar:
   
   java -cp ".;mysql-connector-j-9.6.0.jar" Main
   
¡

## Modulos del proyecto

|             Modulo            |                         Contenido                     |
|-------------------------------|-------------------------------------------------------|
|     Bases de Datos (0484)     | Diseño E/R, modelo relacional y scripts SQL en `/sql` |
|      Programacion (0485)      |           Aplicacion Java con JDBC en `/src`          |
|   Lenguajes de Marcas (0373)  |            XML y XSD del catalogo en `/xml`           |
| Entornos de Desarrollo (0487) |           Control de versiones con Git/GitHub         |
|  Sistemas Informaticos (0483) |          Informe tecnico en `/docs/sistemas`          |
|      Empleabilidad (1709)     |       Perfil profesional en `/docs/empleabilidad`     |

## Autor

Adrian Delgado Gómez — DAM 1º — Proyecto Intermodular 2025-2026

