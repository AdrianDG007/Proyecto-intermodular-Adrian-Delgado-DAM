package service;

import db.Conexion;
import model.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoService {

    //Listar todos los videojuegos con su categoria
    public List <Videojuego> listarTodos () {
        List <Videojuego> lista = new ArrayList <> ();
        String sql = "SELECT v.*, c.nombre AS categoria FROM videojuego v JOIN categoria c ON v.id_categoria = c.id_categoria";
        try (
                Connection con = Conexion.getConexion ();
                Statement  st  = con.createStatement  ();
                ResultSet  rs  = st.executeQuery      (sql) 
            ) {
            while ( rs.next () ) {
                Videojuego vj = new Videojuego
                (
                    rs.getInt    ("id_videojuego"),
                    rs.getString ("titulo"       ),
                    rs.getString ("plataforma"   ),
                    rs.getDouble ("precio"       ),
                    rs.getInt    ("stock"        ),
                    rs.getInt    ("id_categoria" ),
                    rs.getInt    ("id_proveedor" ) 
                );
                lista.add (vj);
                System.out.println ( vj + " | Cat : " + rs.getString ("categoria") );
            }
        } catch (SQLException e) {
            System.out.println ( "Error al listar videojuegos : " + e.getMessage () );
        }
        return lista;
    }

    //Buscar por plataforma
    public void buscarPorPlataforma (String plataforma) {
        String sql = "SELECT * FROM videojuego WHERE plataforma = ?";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setString (1, plataforma);
            ResultSet rs = ps.executeQuery ();
            boolean encontrado = false;
            while ( rs.next () ) {
                encontrado = true;
                System.out.println
                (
                    "[ "
                    + rs.getInt    ("id_videojuego")
                    + "] "
                    + rs.getString ("titulo"       ) 
                    + " | "
                    + rs.getDouble ("precio"       )
                    + "€ | Stock : "
                    + rs.getInt    ("stock"        ) 
                );
            }
            if (!encontrado) System.out.println ("No hay juegos para esa plataforma.");
        } catch (SQLException e) {
            System.out.println ( "Error al buscar : " + e.getMessage () );
        }
    }

    //Añadir un videojuego nuevo
    public void añadir (Videojuego vj) {
        String sql = "INSERT INTO videojuego (titulo, plataforma, precio, stock, id_categoria, id_proveedor) VALUES (?, ?, ?, ?, ?, ?) ";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setString ( 1, vj.getTitulo      () );
            ps.setString ( 2, vj.getPlataforma  () );
            ps.setDouble ( 3, vj.getPrecio      () );
            ps.setInt    ( 4, vj.getStock       () );
            ps.setInt    ( 5, vj.getIdCategoria () );
            ps.setInt    ( 6, vj.getIdProveedor () );
            ps.executeUpdate ();
            System.out.println ("Videojuego añadido correctamente.");
        } catch (SQLException e) {
            System.out.println ( "Error al añadir videojuego : " + e.getMessage () );
        }
    }

    //Modificar el precio de un videojuego
    public void modificarPrecio (int id, double nuevoPrecio) {
        String sql = "UPDATE videojuego SET precio = ? WHERE id_videojuego = ?";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setDouble (1, nuevoPrecio);
            ps.setInt    (2, id);
            int filas = ps.executeUpdate ();
            if (filas > 0) System.out.println ("Precio actualizado.");
            else System.out.println ("No se encontró el videojuego con id " + id);
        } catch (SQLException e) {
            System.out.println ( "Error al modificar : " + e.getMessage () );
        }
    }

    //Eliminar un videojuego
    public void eliminar (int id) {
        String sql = "DELETE FROM videojuego WHERE id_videojuego = ?";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setInt (1, id);
            int filas = ps.executeUpdate ();
            if (filas > 0) System.out.println ("Videojuego eliminado.");
            else System.out.println ("No se encontró el videojuego con id " + id);
        } catch (SQLException e) {
            System.out.println ( "Error al eliminar : " + e.getMessage () );
        }
    }

    //Juegos con stock bajo
    public void stockBajo (int minimo) {
        String sql = "SELECT titulo, plataforma, stock FROM videojuego WHERE stock  < ? ORDER BY stock ASC";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setInt (1, minimo);
            ResultSet rs = ps.executeQuery ();
            System.out.println ("Juegos con stock menor a " + minimo + " : ");
            while ( rs.next () ) {
                System.out.println 
                (
                    "  "
                    + rs.getString ("titulo")
                    + " | "
                    + rs.getString ("plataforma")
                    + " | Stock : "
                    + rs.getInt    ("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println ( "Error : " + e.getMessage () );
        }
    }
}