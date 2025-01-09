package Modelo;

import java.time.LocalDate;

/**
 *
 * @author gwyneth
 */

public class Usuario {
    private String cedula;
    private String nombre;
    private String apellido;
    private LocalDate fecha;
    private String usuario;
    private String clave;
    
    public Usuario(String cedula, String nombre, String apellido, LocalDate fecha, String usuario, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
