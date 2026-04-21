# Informe técnico del entorno de ejecución
## Proyecto: Gestión Tienda de Videojuegos
### Módulo: Sistemas Informáticos (0483)

## 1. Tipo de sistema

La aplicación se ejecutara en un **PC de usuario** con Windows. Elegi este sistema ya que la aplicación está pensada para ser usada por el personal de la tienda directamente en su ordenador de trabajo, sin necesidad de un servidor externo ni infraestructura especial.

Sin necesitarse una máquina dedicada ni ningun servidor porque la base de datos MySQL se instalara en el mismo equipo donde corre la aplicación.Siendo suficiente para una tienda pequeña con pocos usuarios.

## 2. Requisitos de hardware

|   Componente   |            Mínimo           |         Recomendado         |
|----------------|-----------------------------|-----------------------------|
|      CPU       | Intel Core i3 / AMD Ryzen 3 | Intel Core i5 / AMD Ryzen 5 |
|      RAM       |            4 GB             |             8 GB            |
| Almacenamiento |         10 GB libres        |         20 GB libres        |
|    Pantalla    |           1280x720          |          1920x1080          |


El almacenamiento no necesita ser muy grande ya que la app es de consola y la base de datos de la tienda no va a ocupar mucho. Con 10 GB libres es suficiente para tener el sistema operativo, Java, MySQL y los datos sin problemas.

## 3. Sistema operativo recomendado

**Windows 10 / Windows 11 (64 bits)**

He elegido Windows porque es lo que suele tener cualquier tienda o pequeña empresa. Ademas tanto Java como MySQL tienen instaladores muy faciles para Windows, no hay que complicarse.

Tambien podria funcionar en Linux (Ubuntu 22.04 LTS), que es mas ligero y gratis, pero necesitaria mas conocimientos para instalarlo y mantenerlo, asi que para este proyecto Windows es la opcion mas practica.

## 4. Software necesario

Hay que instalar esto en orden para que la aplicacion funcione:

### 4.1 Java (JDK 17 o superior)
1. Descargar desde https://adoptium.net
2. Ejecutar el instalador `.msi`
3. Siguiente en todo, no hay que cambiar nada
4. Comprobar que se ha instalado bien: abrir CMD y ejecutar `java -version`

### 4.2 MySQL Server 8.0
1. Descargar desde https://dev.mysql.com/downloads/installer/
2. Elegir "Developer Default" en el tipo de instalacion
3. Poner una contraseña para el usuario `root` y no olvidarla
4. Asegurarse de que MySQL se configura como servicio de Windows para que arranque solo
5. Comprobar: `mysql --version` en CMD

### 4.3 Driver JDBC
El archivo `mysql-connector-j-9.6.0.jar` ya esta dentro de la carpeta `/src` del proyecto, no hace falta instalarlo por separado.

## 5. Instalacion de la aplicacion

1. Clonar o descargar el repositorio de GitHub
2. Abrir una terminal en la carpeta `/src`
3. Crear la base de datos con el script:
   ```
   mysql -u root -p < sql/crear_tablas.sql
   ```
4. Meter los datos de ejemplo:
   ```
   mysql -u root -p < sql/insertar_datos.sql
   ```
5. Abrir `src/db/Conexion.java` y poner la contraseña de MySQL:
   ```java
   private static final String PASSWORD = "tu_contraseña";
   ```
6. Compilar:
   ```
   javac -cp mysql-connector-j-9.6.0.jar -d . db/Conexion.java model/Videojuego.java model/Cliente.java model/Pedido.java service/VideojuegoService.java service/ClienteService.java service/PedidoService.java Main.java
   ```
7. Ejecutar:
   ```
   java -cp ".;mysql-connector-j-9.6.0.jar" Main
   ```

## 6. Usuarios y permisos

|       Usuario      |            Rol           |                Permisos                |
|--------------------|--------------------------|----------------------------------------|
|        root        |    Administrador MySQL   |    Control total de la base de datos   |
| usuario de Windows | Usuario de la aplicacion | Ejecutar la app, leer y escribir datos |

La estructura de carpetas del proyecto es esta:

```
Proyecto-intermodular-Adrian-Delgado-DAM/
|-- src/          -> codigo fuente Java
|-- sql/          -> scripts de base de datos
|-- xml/          -> catalogo XML
|-- diagrams/     -> diagrama E/R
L-- docs/         -> documentacion
```

Los datos se guardan en MySQL en la base de datos `tienda_videojuegos`, que por defecto esta en `C:\ProgramData\MySQL\MySQL Server 8.0\Data\`.

## 7. Mantenimiento

Actualizaciones:
- Java: mirar si hay versiones LTS nuevas una vez al año
- MySQL: instalar actualizaciones de seguridad cuando salgan
- El stock de la tienda se revisa desde la propia aplicacion cuando haga falta

Copias de seguridad:
- Hacer backup de la base de datos una vez a la semana con este comando:
  ```
  mysqldump -u root -p tienda_videojuegos > backup.sql
  ```
- Guardar el backup en una carpeta externa o subirlo a la nube

Si la aplicacion falla:
- Comprobar que MySQL esta arrancado: Servicios de Windows → MySQL80 → Iniciar
- Comprobar que Java esta bien instalado con `java -version`
- Revisar que la contraseña en `Conexion.java` es la correcta

## 8. Evidencias de funcionamiento

Las capturas de pantalla de la aplicacion funcionando estan en la carpeta `/docs/sistemas/capturas/`.