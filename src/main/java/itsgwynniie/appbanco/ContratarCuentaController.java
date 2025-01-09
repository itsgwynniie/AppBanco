package itsgwynniie.appbanco;

import Controlador.CuentaController;
import Modelo.Cuenta;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gwyneth
 */

public class ContratarCuentaController {
    @FXML
    TextField txtbalance;
            
    CuentaController cue = new CuentaController();
    
    private Cuenta account;
    
    public void setCuenta(Cuenta account) {
        this.account = account;
    }
    
    @FXML
    private void switchToContrataProducto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContrataProducto.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtbalance.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtbalance.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void abrirCuenta() throws IOException {
        String input = txtbalance.getText().trim();
        if (input.isEmpty()) {
            App.showError("Error para continuar", "El campo no puede estar vacío.");
            return;
        }

        try {
            Double balance = Double.valueOf(input);
            account.getBalance().set(balance);

            int result = cue.Create(account);
            if (result > 0) {
                App.showConfirmation("Apertura de Cuenta", "Cuenta creada exitosamente.");
                switchToInicioSesion();
            }
        } catch (NumberFormatException e) {
            App.showError("Error de formato", "El balance debe ser un número válido.");
        }
    }
    
}
