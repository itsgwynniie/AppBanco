package Modelo;

import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public class Cuenta {
    private IntegerProperty id;
    private StringProperty usuario;
    private DoubleProperty balance;
    private StringProperty tipo;
    
    public Cuenta(String usuario, String tipo) {
        this.usuario = new SimpleStringProperty(usuario);
        this.tipo = new SimpleStringProperty(tipo);
        this.balance = new SimpleDoubleProperty(0.0);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getUsuario() {
        return usuario;
    }

    public void setUsuario(StringProperty usuario) {
        this.usuario = usuario;
    }

    public DoubleProperty getBalance() {
        return balance;
    }

    public void setBalance(DoubleProperty balance) {
        this.balance = balance;
    }

    public StringProperty getTipo() {
        return tipo;
    }

    public void setTipo(StringProperty tipo) {
        this.tipo = tipo;
    }
    
    
}
