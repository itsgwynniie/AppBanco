package Modelo;

import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author gwyneth
 */

public interface Movimiento {
    IntegerProperty idProperty();
    IntegerProperty idCuentaProperty();
    DoubleProperty montoProperty();
    ObjectProperty<LocalDate> fechaProperty();
    
    int getId();
    int getIdCuenta();
    double getMonto();
    LocalDate getFecha();
}
