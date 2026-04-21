package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/tienda_videojuegos?useUnicode=true&characterEncoding=UTF-8";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "ADGadg7/";

    public static Connection getConexion () {
        Connection con = null;
        try {
            con = DriverManager.getConnection (URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println ( "Error al conectar con la base de datos: " + e.getMessage () );
        }
        return con;
    }
}