package Modelo;

import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public class TransaccionCuenta implements Movimiento {
    private IntegerProperty id;
    private IntegerProperty idCuenta;
    private IntegerProperty idCuentaOut;
    private DoubleProperty monto;
    private ObjectProperty<LocalDate> fecha;

    public TransaccionCuenta(int id, int idCuenta, int idCuentaOut, double monto, LocalDate fecha) {
        this.id = new SimpleIntegerProperty(id);
        this.idCuenta = new SimpleIntegerProperty(idCuenta);
        this.idCuentaOut = new SimpleIntegerProperty(idCuentaOut);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleObjectProperty<>(fecha);
    }
    
    public TransaccionCuenta(int idCuenta, int idCuentaOut, double monto) {
        this.idCuenta = new SimpleIntegerProperty(idCuenta);
        this.idCuentaOut = new SimpleIntegerProperty(idCuentaOut);
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

    public int getIdCuentaOut() {
        return idCuentaOut.get();
    }

    public IntegerProperty idCuentaOutProperty() {
        return idCuentaOut;
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
