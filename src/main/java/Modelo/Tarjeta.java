package Modelo;

import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public class Tarjeta {
    private IntegerProperty id;
    private StringProperty cedula;
    private DoubleProperty balance;
    
    public Tarjeta(String cedula, double balance) {
        this.cedula = new SimpleStringProperty(cedula);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getCedula() {
        return cedula;
    }

    public void setCedula(StringProperty cedula) {
        this.cedula = cedula;
    }

    public DoubleProperty getBalance() {
        return balance;
    }

    public void setBalance(DoubleProperty balance) {
        this.balance = balance;
    }
    
    
}
