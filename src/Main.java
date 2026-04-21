import service.VideojuegoService;
import service.ClienteService;
import service.PedidoService;
import model.Videojuego;
import model.Cliente;
import model.Pedido;

import java.util.Scanner;
import java.util.Locale;

public class Main {

    static Scanner sc                    = new Scanner           (System.in).useLocale (Locale.US);
    static VideojuegoService vjService   = new VideojuegoService ();
    static ClienteService clienteService = new ClienteService    ();
    static PedidoService pedidoService   = new PedidoService     ();

    public static void main (String [] args) {
        int opcion;
        do {
            menuPrincipal ();
            opcion = leerInt ();
            switch (opcion) {
                case 1  -> menuVideojuegos ();
                case 2  -> menuClientes ();
                case 3  -> menuPedidos ();
                case 0  -> System.out.println ("Hasta luego.");
                default -> System.out.println ("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void menuPrincipal () {
        System.out.println ("\n GAMESTORE "      );
        System.out.println ("1. Videojuegos"     );
        System.out.println ("2. Clientes"        );
        System.out.println ("3. Pedidos"         );
        System.out.println ("0. Salir"           );
        System.out.print   ("Elige una opcion : ");
    }

    //VIDEOJUEGOS
    static void menuVideojuegos () {
        int op;
        do {
            System.out.println ("\n Videojuegos "           );
            System.out.println ("1. Ver todos"              );
            System.out.println ("2. Buscar por plataforma"  );
            System.out.println ("3. Añadir videojuego"      );
            System.out.println ("4. Modificar precio"       );
            System.out.println ("5. Eliminar videojuego"    );
            System.out.println ("6. Ver stock bajo"         );
            System.out.println ("0. Volver"                 );
            System.out.print   ("Elige : "                  );
            op = leerInt ();
            switch (op) {
                case 1 -> vjService.listarTodos ();
                case 2 -> {
                    System.out.print ("Plataforma (PS4/PS5/PC/Switch/Xbox) : ");
                    String plat = sc.nextLine ();
                    vjService.buscarPorPlataforma (plat);
                }
                case 3 -> {
                    System.out.print ("Titulo : "   );
                    String titulo = sc.nextLine      ();
                    System.out.print ("Plataforma : ");
                    String plat   = sc.nextLine      ();
                    System.out.print ("Precio : "   );
                    double precio = leerDouble       ();
                    System.out.print ("Stock : "    );
                    int stock     = leerInt          ();
                    System.out.print (" ID categoria (1=Accion 2=RPG 3=Deportes 4=Aventura 5=Estrategia 6=Terror) : ");
                    int idCat     = leerInt          ();
                    System.out.print (" ID proveedor (1=Koch 2=Bandai 3=Sony 4=Nintendo) : ");
                    int idProv    = leerInt          ();
                    vjService.añadir ( new Videojuego (titulo, plat, precio, stock, idCat, idProv) );
                }
                case 4 -> {
                    System.out.print (" ID del videojuego : ");
                    int id        = leerInt    ();
                    System.out.print (" Nuevo precio : ");
                    double precio = leerDouble ();
                    vjService.modificarPrecio (id, precio);
                }
                case 5 -> {
                    System.out.print   ("ID del videojuego a eliminar : ");
                    int id = leerInt   ();
                    vjService.eliminar (id);
                }
                case 6 -> {
                    System.out.print    ("Stock minimo : ");
                    int min = leerInt   ();
                    vjService.stockBajo (min);
                }
                case 0 -> {}
                default -> System.out.println ("Opcion no valida.");
            }
        } while (op != 0);
    }

    //CLIENTES
    static void menuClientes () {
        int op;
        do {
            System.out.println ("\n Clientes "           );
            System.out.println ("1. Ver todos"           );
            System.out.println ("2. Buscar por email"    );
            System.out.println ("3. Añadir cliente"      );
            System.out.println ("4. Modificar telefono"  );
            System.out.println ("5. Eliminar cliente"    );
            System.out.println ("0. Volver"              );
            System.out.print   ("Elige : "               );
            op = leerInt ();
            switch (op) {
                case 1 -> clienteService.listarTodos ();
                case 2 -> {
                    System.out.print ("Email : ");
                    String email = sc.nextLine ();
                    clienteService.buscarPorEmail (email);
                }
                case 3 -> {
                    System.out.print ("Nombre : "   );
                    String nombre    = sc.nextLine ();
                    System.out.print ("Apellidos : ");
                    String apellidos = sc.nextLine ();
                    System.out.print ("Email : "    );
                    String email     = sc.nextLine ();
                    System.out.print ("Telefono : " );
                    String tel       = sc.nextLine ();
                    clienteService.añadir ( new Cliente (nombre, apellidos, email, tel) );
                }
                case 4 -> {
                    System.out.print (" ID del cliente : "  );
                    int id     = leerInt         ();
                    System.out.print (" Nuevo telefono : "  );
                    String tel = sc.nextLine     ();
                    clienteService.modificarTelefono (id, tel);
                }
                case 5 -> {
                    System.out.print ("ID del cliente a eliminar : ");
                    int id = leerInt ();
                    clienteService.eliminar (id);
                }
                case 0 -> {}
                default -> System.out.println ("Opcion no valida.");
            }
        } while (op != 0);
    }

    //PEDIDOS
    static void menuPedidos () {
        int op;
        do {
            System.out.println ("\n Pedidos "                   );
            System.out.println ("1. Ver todos"                  );
            System.out.println ("2. Ver pedidos de un cliente"  );
            System.out.println ("3. Ver detalle de un pedido"   );
            System.out.println ("4. Crear pedido"               );
            System.out.println ("5. Cambiar estado"             );
            System.out.println ("0. Volver"                     );
            System.out.print   ("Elige : "                      );
            op = leerInt ();
            switch (op) {
                case 1 -> pedidoService.listarTodos ();
                case 2 -> {
                    System.out.print ("ID del cliente : ");
                    int id = leerInt ();
                    pedidoService.listarPorCliente (id);
                }
                case 3 -> {
                    System.out.print ("ID del pedido : ");
                    int id = leerInt ();
                    pedidoService.verDetalle (id);
                }
                case 4 -> {
                    System.out.print ("ID del cliente : "      );
                    int idCliente  = leerInt    ();
                    System.out.print ("Fecha (yyyy-mm-dd) : "  );
                    String fecha   = sc.nextLine ();
                    System.out.print ("Total : "               );
                    double total   = leerDouble  ();
                    pedidoService.crear ( new Pedido (fecha, total, "pendiente", idCliente) );
                }
                case 5 -> {
                    System.out.print ("ID del pedido : ");
                    int id        = leerInt      ();
                    System.out.print ("Nuevo estado (pendiente/pagado/enviado/cancelado) : ");
                    String estado = sc.nextLine  ();
                    pedidoService.cambiarEstado (id, estado);
                }
                case 0 -> {}
                default -> System.out.println ("Opcion no valida.");
            }
        } while (op != 0);
    }

    //Metodos auxiliares para leer datos sin errores
    static int leerInt () {
        while ( !sc.hasNextInt () ) {
            System.out.print ("Introduce un numero : ");
            sc.next ();
        }
        int n = sc.nextInt ();
        sc.nextLine ();
        return n;
    }

    static double leerDouble () {
        while ( !sc.hasNextDouble () ) {
            System.out.print (" Introduce un numero con punto (ej : 49.99) : ");
            sc.next ();
        }
        double d = sc.nextDouble ();
        sc.nextLine ();
        return d;
    }
}
