package itsgwynniie.appbanco;

import Controlador.DepositoController;
import Controlador.RetiroController;
import Controlador.TransaccionCController;
import Modelo.Deposito;
import Modelo.Movimiento;
import Modelo.Retiro;
import Modelo.TransaccionCuenta;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author gwyneth
 */

public class MovimientosController {
    @FXML
    private TableView<Movimiento> tabla;

    @FXML
    private TableColumn<Movimiento, Integer> cId;

    @FXML
    private TableColumn<Movimiento, Integer> cIdCuenta;

    @FXML
    private TableColumn<Movimiento, Double> cMonto;

    @FXML
    private TableColumn<Movimiento, LocalDate> cFecha;

    @FXML
    private TableColumn<TransaccionCuenta, Integer> cIdCuentaOut;

    private ObservableList<Movimiento> listaMovimientos;
    
    @FXML
    private void switchToInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaUsuario.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) tabla.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        listaMovimientos = FXCollections.observableArrayList();
        
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cIdCuenta.setCellValueFactory(cellData -> cellData.getValue().idCuentaProperty().asObject());
        cMonto.setCellValueFactory(cellData -> cellData.getValue().montoProperty().asObject());
        cFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());

        cIdCuentaOut.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof TransaccionCuenta) {
                return ((TransaccionCuenta) cellData.getValue()).idCuentaOutProperty().asObject();
            }
            return null;
        });
        
        RetiroController retRead = new RetiroController();
        ArrayList<Retiro> retiros = retRead.Read();
        
        if (retiros != null) {
            for (Retiro r:retiros) {
                listaMovimientos.add(new Retiro(r.getId(), r.getIdCuenta(), r.getMonto(), r.getFecha()));
            }
        }

        DepositoController depRead = new DepositoController();
        ArrayList<Deposito> depositos = depRead.Read();
        
        if (depositos != null) {
            for (Deposito d:depositos) {
                listaMovimientos.add(new Deposito(d.getId(), d.getIdCuenta(), d.getMonto(), d.getFecha()));
            }
        }
            
        TransaccionCController traRead = new TransaccionCController();
        ArrayList<TransaccionCuenta> transacciones = traRead.Read();
        
        if (transacciones != null) {
            for (TransaccionCuenta t:transacciones) {
                listaMovimientos.add(new TransaccionCuenta(t.getId(), t.getIdCuenta(), t.getIdCuentaOut(), t.getMonto(), t.getFecha()));
            }
        }
            
        tabla.setItems(listaMovimientos);
    }
        
}
