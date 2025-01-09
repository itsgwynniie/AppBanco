package itsgwynniie.appbanco;

import Modelo.Cuenta;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author gwyneth
 */

public class ContrataProductoController {
    @FXML
    private Button btnahorro;
    @FXML
    private Button btncorriente;
    @FXML 
    private Button btntarjeta;
    
    String CedulaTemporal;

    public String getCedulaTemporal() {
        return CedulaTemporal;
    }

    public void setCedulaTemporal(String CedulaTemporal) {
        this.CedulaTemporal = CedulaTemporal;
    }
    
    @FXML
    private void switchToInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnahorro.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToContratarTarjeta() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContratarTarjeta.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnahorro.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void contratarCuentaAhorros() {
        Cuenta account = new Cuenta(CedulaTemporal, "ahorros");
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContratarAhorros.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnahorro.getScene().getWindow();
            
            ContratarCuentaController controller = loader.getController();
            
            if (controller != null) {
                controller.setCuenta(account);
                stage.setScene(scene);
                stage.show();
            } else {
                App.showError("Error al cargar la pantalla", "No se pudo cargar la cuenta correctamente.");
            }
        } catch (IOException e) {
            App.showError("Error al cargar la pantalla", "No se pudo cargar la pantalla siguiente.");
        }
    }
    
    @FXML
    private void contratarCuentaCorriente() {
        Cuenta account = new Cuenta(CedulaTemporal, "corriente");
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContratarCorriente.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btncorriente.getScene().getWindow();
            
            ContratarCuentaController controller = loader.getController();
            
            if (controller != null) {
                controller.setCuenta(account);
                stage.setScene(scene);
                stage.show();
            } else {
                App.showError("Error al cargar la pantalla", "No se pudo cargar la cuenta correctamente.");
            }
        } catch (IOException e) {
            App.showError("Error al cargar la pantalla", "No se pudo cargar la pantalla siguiente.");
        }
    }
    
    @FXML 
    private void contratarTarjeta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContratarTarjeta.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btntarjeta.getScene().getWindow();

            ContratarTarjetaController controller = loader.getController();

            if (controller != null) {
                controller.setCedulaTemporal2(CedulaTemporal);
                    
                App.showConfirmation("Cargar pantalla", "Pantalla cargada exitosamente.");
                    
                stage.setScene(scene);
                stage.show();
            } else {
                App.showError("Error al cargar pantalla", "No se pudo cargar el usuario correctamente.");
            }
        } catch (IOException e) {
            App.showError("Error al cargar pantalla", "No se pudo cargar la siguiente pantalla correctamente.");
        }
    }
}
