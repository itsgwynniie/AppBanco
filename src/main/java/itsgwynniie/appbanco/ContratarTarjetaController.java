package itsgwynniie.appbanco;

import Controlador.TarjetaController;
import Modelo.Tarjeta;
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

public class ContratarTarjetaController {
    @FXML TextField txtbalance;
    
    TarjetaController tar = new TarjetaController();
    
    String CedulaTemporal2;

    public String getCedulaTemporal2() {
        return CedulaTemporal2;
    }

    public void setCedulaTemporal2(String CedulaTemporal2) {
        this.CedulaTemporal2 = CedulaTemporal2;
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
    private void abrirTarjeta() throws IOException {
        Double balance = Double.valueOf(txtbalance.getText().trim());
        
        if (balance == null) {
            App.showError("Error para continuar", "El campo no puede estar vacío.");
            return;
        }
        
        Tarjeta card = new Tarjeta(CedulaTemporal2, balance);
        int result = tar.Create(card);
        
        if (result > 0) {
            App.showConfirmation("Apertura de Tarjeta", "Tarjeta creada exitosamente.");
            switchToInicioSesion();
        }
    }
}
