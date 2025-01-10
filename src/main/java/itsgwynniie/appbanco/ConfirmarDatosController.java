package itsgwynniie.appbanco;

import Controlador.UsuarioController;
import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gwyneth
 */

public class ConfirmarDatosController {
    @FXML TextField txtcedula;
    @FXML TextField txtclave;
    
    private UsuarioController usu = new UsuarioController();
    
    @FXML
    private void initialize() {
        txtclave.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                txtclave.setText(oldValue);
            }
        });
    }
    
    @FXML
    private void switchToInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtcedula.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void confirmarDatos() throws IOException {
        String cedula = txtcedula.getText().trim();
        String clave = txtclave.getText().trim();
        
        if (cedula.isEmpty() || clave.isEmpty()) {
            App.showError("Error para continuar", "El campo no puede estar vacío.");
            return;
        }
        
        if (!cedula.matches("\\d+")) {
            App.showError("Error", "La cedula ingresada no es válida.");
            return;
        } else if (cedula.length() != 10) {
            App.showError("Error", "La cédula debe tener 10 caracteres.");
            return;
        }
        
        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                return usu.SearchIdAndPassword(cedula, clave);
            }
        };
        
        task.setOnSucceeded(e -> {
            boolean result = task.getValue();
            
            if (result) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ContrataProducto.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) txtcedula.getScene().getWindow();

                    ContrataProductoController controller = loader.getController();

                    if (controller != null) {
                        controller.setCedulaTemporal(cedula);

                        App.showConfirmation("Confirmación de Datos", "Datos Confirmados.");

                        stage.setScene(scene);
                        stage.show();
                    } else {
                        App.showError("Error al cargar pantalla", "No se pudo cargar el usuario correctamente.");
                    }
                } catch (IOException ex) {
                    App.showError("Error al cargar pantalla", "No se pudo cargar la siguiente pantalla correctamente.");
                }
            } else {
                App.showError("Confirmación de Datos", "Datos incorrectos.");
            }
        });
        
        task.setOnFailed(e -> {
            App.showError("Error", "Ocurrió un error durante la validación.");
        });
        
        new Thread(task).start();
    }
}
