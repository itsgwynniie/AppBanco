package itsgwynniie.appbanco;

import Controlador.CuentaController;
import Controlador.RetiroController;
import Modelo.Retiro;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gwyneth
 */

public class RetirarController {
    @FXML ComboBox<Integer> cbocuenta;
    @FXML TextField txtmonto;
    
    RetiroController ret = new RetiroController();
    CuentaController account = new CuentaController();
    
    private String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        cargarCuentas();
    }
    
    private void cargarCuentas() {
        if (usuario != null && !usuario.isEmpty()) {
            ArrayList<Integer> cuentas = account.getCuentas(usuario);
            ObservableList<Integer> cuentasList = FXCollections.observableArrayList(cuentas);
            cbocuenta.setItems(cuentasList);
        }
    }
    
    @FXML
    private void switchToInicioSesion() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaUsuario.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) cbocuenta.getScene().getWindow();

                VistaUsuarioController controller = loader.getController();

                if (controller != null) {
                    controller.setUsuario(usuario);
                    
                    stage.setScene(scene);
                    stage.show();
                } else {
                    App.showError("Error al cargar pantalla", "No se pudo cargar el usuario correctamente.");
                }
            } catch (IOException e) {
                App.showError("Error al cargar pantalla", "No se pudo cargar la siguiente pantalla correctamente.");
            }
    }
    
    @FXML
    private void retirar() {
        if (cbocuenta.getValue() == null) {
            App.showError("Error", "Por favor, seleccione una cuenta.");
            return;
        }
        
        if (txtmonto.getText().trim().isEmpty()) {
            App.showError("Error", "Por favor, ingrese un monto válido.");
            return;
        }
        
        try {
            int cuenta = cbocuenta.getValue();
            double monto = Double.parseDouble(txtmonto.getText().trim());

            Retiro retiro = new Retiro(cuenta, monto);
            ret.Create(retiro);

            App.showConfirmation("Retiro", "Retiro hecho con éxito.");
        } catch (NumberFormatException e) {
            App.showError("Error", "El monto debe ser un número válido.");
        }
    }
}
