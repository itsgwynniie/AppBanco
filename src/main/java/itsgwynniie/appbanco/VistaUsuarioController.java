package itsgwynniie.appbanco;

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

public class VistaUsuarioController {
    @FXML Button btndeposito;
    @FXML Button btnretiro;
    @FXML Button btntransaccion;
    @FXML Button btnmovimientos;
    
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    private void switchToInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btndeposito.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void depositar() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Depositar.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) btndeposito.getScene().getWindow();

                DepositarController controller = loader.getController();

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
    private void retirar() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Retirar.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) btnretiro.getScene().getWindow();

                RetirarController controller = loader.getController();

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
    private void transferir() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Transferir.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) btntransaccion.getScene().getWindow();

                TransferirController controller = loader.getController();

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
    private void verMovimientos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Movimientos.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnmovimientos.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
