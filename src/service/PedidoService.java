package service;

import db.Conexion;
import model.Pedido;

import java.sql.*;

public class PedidoService {

    //Listar pedidos de un cliente
    public void listarPorCliente (int idCliente) {
        String sql = "SELECT p.*, CONCAT (c.nombre, ' ', c.apellidos) AS cliente "  +
                     "FROM pedido p JOIN cliente c ON p.id_cliente = c.id_cliente " +
                     "WHERE p.id_cliente = ? ORDER BY p.fecha DESC";
        try (
                Connection               con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setInt (1, idCliente);
            ResultSet rs = ps.executeQuery ();
            boolean encontrado = false;
            while ( rs.next () ) {
                encontrado = true;
                System.out.println (
                    "[ "
                    + rs.getInt ("id_pedido")
                    + " ] "
                    + rs.getString ("cliente")
                    + " | Fecha : "   + rs.getString ("fecha" ) 
                    + " | Total : "   + rs.getDouble ("total" )
                    + "€ | Estado : " + rs.getString ("estado")
                );
            }
            if (!encontrado) System.out.println ("Este cliente no tiene pedidos.");
        } catch (SQLException e) {
            System.out.println ( "Error al listar pedidos : " + e.getMessage () );
        }
    }

    //Ver detalle de un pedido
    public void verDetalle (int idPedido) {
        String sql = "SELECT v.titulo, v.plataforma, lp.cantidad, lp.precio_unidad, "                +
                     " (lp.cantidad * lp.precio_unidad) AS subtotal "                                +
                     "FROM linea_pedido lp JOIN videojuego v ON lp.id_videojuego = v.id_videojuego " +
                     "WHERE lp.id_pedido = ?";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setInt (1, idPedido);
            ResultSet rs = ps.executeQuery ();
            System.out.println ("Detalle pedido #" + idPedido + " : ");
            double totalCalculado = 0;
            while ( rs.next () ) {
                double subtotal = rs.getDouble ("subtotal");
                totalCalculado += subtotal;
                System.out.println (
                    "  "
                    + rs.getString ("titulo") 
                    + " ("
                    + rs.getString ("plataforma")
                    + ") "
                    + " x"
                    + rs.getInt    ("cantidad") 
                    + " | "
                    + rs.getDouble ("precio_unidad")
                    + "€ | Subtotal : "
                    + subtotal
                    + "€"
                );
            }
            System.out.println ("  Total : " + totalCalculado + "€");
        } catch (SQLException e) {
            System.out.println ( "Error al ver detalle : " + e.getMessage () );
        }
    }

    //Crear un pedido nuevo
    public void crear (Pedido p) {
        String sql = "INSERT INTO pedido (fecha, total, estado, id_cliente) VALUES (?, ?, ?, ?) ";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setString ( 1, p.getFecha     () );
            ps.setDouble ( 2, p.getTotal     () );
            ps.setString ( 3, p.getEstado    () );
            ps.setInt    ( 4, p.getIdCliente () );
            ps.executeUpdate ();
            System.out.println ("Pedido creado correctamente.");
        } catch (SQLException e) {
            System.out.println ( "Error al crear pedido : " + e.getMessage () );
        }
    }

    //Cambiar estado de un pedido
    public void cambiarEstado (int idPedido, String nuevoEstado) {
        String sql = "UPDATE pedido SET estado = ? WHERE id_pedido = ?";
        try (
            Connection        con = Conexion.getConexion ();
            PreparedStatement ps  = con.prepareStatement (sql) 
            ) {
            ps.setString (1, nuevoEstado);
            ps.setInt    (2, idPedido   );
            int filas = ps.executeUpdate ();
            if (filas > 0) System.out.println ("Estado actualizado a : " + nuevoEstado);
            else System.out.println ("No se encontró el pedido con id " + idPedido);
        } catch (SQLException e) {
            System.out.println ( "Error al cambiar estado : " + e.getMessage () );
        }
    }

    //Listar todos los pedidos
    public void listarTodos () {
        String sql = "SELECT p.*, CONCAT (c.nombre, ' ', c.apellidos) AS cliente "  +
                     "FROM pedido p JOIN cliente c ON p.id_cliente = c.id_cliente " +
                     "ORDER BY p.fecha DESC";
        try (
            Connection con = Conexion.getConexion ();
             Statement st  = con.createStatement  ();
             ResultSet rs  = st.executeQuery      (sql) 
            ) {
            while ( rs.next () ) {
                System.out.println (
                    "[ "
                    + rs.getInt ("id_pedido")
                    + " ] "
                    + rs.getString ("cliente")
                    + " | "  + rs.getString ("fecha" ) 
                    + " | "  + rs.getDouble ("total" )
                    + "€ | " + rs.getString ("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println ( "Error al listar pedidos : " + e.getMessage () );
        }
    }
}