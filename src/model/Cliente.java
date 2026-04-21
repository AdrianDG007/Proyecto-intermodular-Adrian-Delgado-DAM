package model;

public class Cliente {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;

    public Cliente (int id, String nombre, String apellidos, String email, String telefono) {
        this.id        = id;
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.email     = email;
        this.telefono  = telefono;
    }

    public Cliente (String nombre, String apellidos, String email, String telefono) {
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.email     = email;
        this.telefono  = telefono;
    }

    public int    getId           () {return id;}
    public String getNombre       () {return nombre;}
    public String getApellidos    () {return apellidos;}
    public String getEmail        () {return email;}
    public String getTelefono     () {return telefono;}

    public void setId        (int id)           {this.id = id;}
    public void setNombre    (String nombre)    {this.nombre = nombre;}
    public void setApellidos (String apellidos) {this.apellidos = apellidos;}
    public void setEmail     (String email)     {this.email = email;}
    public void setTelefono  (String telefono)  {this.telefono = telefono;}

    @Override
    public String toString () {
        return "[ " + id + " ]" + nombre + " " + apellidos + " | " + email + " | " + telefono;
    }
}