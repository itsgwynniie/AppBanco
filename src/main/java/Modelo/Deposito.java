package Modelo;

import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public class Deposito implements Movimiento {
    private IntegerProperty id;
    private IntegerProperty idCuenta;
    private DoubleProperty monto;
    private ObjectProperty<LocalDate> fecha;
    
    public Deposito(int id, int idCuenta, double monto, LocalDate fecha) {
        this.id = new SimpleIntegerProperty(id);
        this.idCuenta = new SimpleIntegerProperty(idCuenta);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleObjectProperty<>(fecha);
    }

    public Deposito(int idCuenta, double monto) {
        this.idCuenta = new SimpleIntegerProperty(idCuenta);
        this.monto = new SimpleDoubleProperty(monto);
    }

    @Override
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @Override
    public int getIdCuenta() {
        return idCuenta.get();
    }

    public IntegerProperty idCuentaProperty() {
        return idCuenta;
    }

    @Override
    public double getMonto() {
        return monto.get();
    }

    public DoubleProperty montoProperty() {
        return monto;
    }

    @Override
    public LocalDate getFecha() {
        return fecha.get();
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return fecha;
    }
}
