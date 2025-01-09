package Modelo;

import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public class TransaccionTarjeta {
    private IntegerProperty id;
    private IntegerProperty idTarjeta;
    private DoubleProperty monto;
    private ObjectProperty<LocalDate> fecha;
    
    public TransaccionTarjeta(int id, int idTarjeta, double monto, LocalDate fecha) {
        this.id = new SimpleIntegerProperty(id);
        this.idTarjeta = new SimpleIntegerProperty(idTarjeta);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleObjectProperty<>(fecha);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public IntegerProperty getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(IntegerProperty idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public DoubleProperty getMonto() {
        return monto;
    }

    public void setMonto(DoubleProperty monto) {
        this.monto = monto;
    }

    public ObjectProperty<LocalDate> getFecha() {
        return fecha;
    }

    public void setFecha(ObjectProperty<LocalDate> fecha) {
        this.fecha = fecha;
    }
    
    
}
