package model;

public class Pedido {

    private int id;
    private String fecha;
    private double total;
    private String estado;
    private int idCliente;

    public Pedido (int id, String fecha, double total, String estado, int idCliente) {
        this.id        = id;
        this.fecha     = fecha;
        this.total     = total;
        this.estado    = estado;
        this.idCliente = idCliente;
    }

    public Pedido (String fecha, double total, String estado, int idCliente) {
        this.fecha     = fecha;
        this.total     = total;
        this.estado    = estado;
        this.idCliente = idCliente;
    }

    public int    getId        () {return id;}
    public String getFecha     () {return fecha;}
    public double getTotal     () {return total;}
    public String getEstado    () {return estado;}
    public int    getIdCliente () {return idCliente;}

    public void setId        (int id)        {this.id = id;}
    public void setFecha     (String fecha)  {this.fecha = fecha;}
    public void setTotal     (double total)  {this.total = total;}
    public void setEstado    (String estado) {this.estado = estado;}
    public void setIdCliente (int idCliente) {this.idCliente = idCliente;}

    @Override
    public String toString () {
        return "[ " + id + " ] Fecha : " + fecha + " | Total : " + total + "€ | Estado : " + estado;
    }
}