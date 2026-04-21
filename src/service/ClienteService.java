package service;

import db.Conexion;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    //Listar todos los clientes
    public List <Cliente> listarTodos () {
        List <Cliente> lista = new ArrayList <> ();
        String sql = "SELECT * FROM cliente";
        try ( 
                Connection con = Conexion.getConexion ();
                Statement  st  = con.createStatement  ();
                ResultSet  rs  = st.executeQuery      (sql)
            ) {
            while (  rs.next () ) {
                Cliente c = new Cliente 
                (
                    rs.getInt    ("id_cliente"),
                    rs.getString ("nombre"    ),
                    rs.getString ("apellidos" ),
                    rs.getString ("email"     ),
                    rs.getString ("telefono"  ) 
                );
                lista.add (c);
                System.out.println (c);
            }
        } catch (SQLException e) {
            System.out.println ( "Error al listar clientes: " + e.getMessage () );
        }
        return lista;
    }

    //Luscar cliente por email
    public void buscarPorEmail (String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";
        try (
                Connection        con = Conexion.getConexion ();
                PreparedStatement ps  = con.prepareStatement (sql)
            ) {
            ps.setString (1, email);
            ResultSet rs = ps.executeQuery ();
            if ( rs.next () ) {
                System.out.println 
                (
                    "[ "
                    + rs.getInt    ("id_cliente") + " ] "
                    + rs.getString ("nombre"    )
                    + " "
                    + rs.getString ("apellidos" )
                    + " | "
                    + rs.getString ("email"     )
                    + " | "
                    + rs.getString ("telefono"  )
                );
            } else {
                System.out.println ("No se encontró ningún cliente con ese email.");
            }
        } catch (SQLException e) {
            System.out.println ("Error al buscar cliente: " + e.getMessage () );
        }
    }

    //Añadir cliente nuevo
    public void añadir (Cliente c) {
        String sql = "INSERT INTO cliente (nombre, apellidos, email, telefono) VALUES (?, ?, ?, ?) ";
        try (
                Connection       con = Conexion.getConexion ();
                PreparedStatement ps = con.prepareStatement (sql) 
            ) {
                    ps.setString ( 1, c.getNombre    () );
                    ps.setString ( 2, c.getApellidos () );
                    ps.setString ( 3, c.getEmail     () );
                    ps.setString ( 4, c.getTelefono  () );
                    ps.executeUpdate ();
                    System.out.println ("Cliente añadido correctamente.");
                } catch (SQLException e) {
            System.out.println ( "Error al añadir cliente: " + e.getMessage () );
        }
    }

    //Modificar telefono de un cliente
    public void modificarTelefono (int id, String nuevoTelefono) {
        String sql = "UPDATE cliente SET telefono = ? WHERE id_cliente = ?";
        try (
                Connection con = Conexion.getConexion ();
                PreparedStatement ps = con.prepareStatement (sql)
            ) {
                ps.setString (1, nuevoTelefono);
                ps.setInt    (2, id           );
                int filas = ps.executeUpdate ();
                if (filas > 0) System.out.println ("Teléfono actualizado.");
                else System.out.println ("No se encontró el cliente con id " + id);
            } catch (SQLException e) {
            System.out.println ( "Error al modificar: " + e.getMessage () );
        }
    }

    //Eliminar cliente
    public void eliminar (int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (
                Connection        con = Conexion.getConexion ();
                PreparedStatement ps  = con.prepareStatement (sql)
            ) {
                ps.setInt (1, id);
                int filas = ps.executeUpdate ();
                if (filas > 0) System.out.println ("Cliente eliminado.");
                else System.out.println ("No se encontró el cliente con id " + id);
            } catch (SQLException e) {
                System.out.println ( "Error al eliminar: " + e.getMessage () );
        }
    }
}