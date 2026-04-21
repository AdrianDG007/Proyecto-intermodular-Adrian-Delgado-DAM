package model;

public class Videojuego {

    private int id;
    private String titulo;
    private String plataforma;
    private double precio;
    private int stock;
    private int idCategoria;
    private int idProveedor;

    public Videojuego (int id, String titulo, String plataforma, double precio, int stock, int idCategoria, int idProveedor) {
        this.id          = id;
        this.titulo      = titulo;
        this.plataforma  = plataforma;
        this.precio      = precio;
        this.stock       = stock;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public Videojuego (String titulo, String plataforma, double precio, int stock, int idCategoria, int idProveedor) {
        this.titulo      = titulo;
        this.plataforma  = plataforma;
        this.precio      = precio;
        this.stock       = stock;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public int    getId            () {return id;}
    public String getTitulo        () {return titulo;}
    public String getPlataforma    () {return plataforma;}
    public double getPrecio        () {return precio;}
    public int    getStock         () {return stock;}
    public int    getIdCategoria   () {return idCategoria;}
    public int    getIdProveedor   () {return idProveedor;}

    public void setId          (int id)            {this.id = id;}
    public void setTitulo      (String titulo)     {this.titulo = titulo;}
    public void setPlataforma  (String plataforma) {this.plataforma = plataforma;}
    public void setPrecio      (double precio)     {this.precio = precio;}
    public void setStock       (int stock)         {this.stock = stock;}
    public void setIdCategoria (int idCategoria)   {this.idCategoria = idCategoria;}
    public void setIdProveedor (int idProveedor)   {this.idProveedor = idProveedor;}

    @Override
    public String toString () {
return "[ " + id + " ] " + titulo + " | " + plataforma + " | " + precio + " EUR | Stock: " + stock;    }
}