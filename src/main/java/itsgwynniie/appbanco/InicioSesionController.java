package itsgwynniie.appbanco;

import Controlador.UsuarioController;
import Modelo.Usuario;
import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InicioSesionController {
    @FXML TextField txtuser;
    @FXML PasswordField txtclave;
    
    UsuarioController usu = new UsuarioController();
    
    @FXML
    private void initialize() {
        txtclave.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                txtclave.setText(oldValue);
            }
        });
    }

    @FXML
    private void switchToCrearUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CrearUsuario.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtuser.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToConfirmarDatos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmarDatos.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtuser.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToVistaUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaUsuario.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtuser.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void iniciarSesion() throws IOException {
        String usuario = txtuser.getText().trim();
        String clave = txtclave.getText().trim();
        
        if (usuario.isEmpty() || clave.isEmpty()) {
            App.showError("Datos faltantes", "Por favor, completa todos los campos.");
            return;
        }
        
        Task<Usuario> task = new Task<>() {
            @Override
            protected Usuario call() {
                return usu.InicioSesion(usuario, clave);
            }
        };
        
        task.setOnSucceeded(e -> {
            Usuario user = task.getValue();
            if (user != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaUsuario.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) txtuser.getScene().getWindow();

                    VistaUsuarioController controller = loader.getController();

                    if (controller != null) {
                        controller.setUsuario(usuario);

                        App.showConfirmation("Inicio de Sesión", "Sesión iniciada exitosamente");

                        stage.setScene(scene);
                        stage.show();
                    } else {
                        App.showError("Error al cargar pantalla", "No se pudo cargar el usuario correctamente.");
                    }
                } catch (IOException ex) {
                    App.showError("Error al cargar pantalla", "No se pudo cargar la siguiente pantalla correctamente.");
                }
            } else {
                App.showError("Error de autenticación", "Usuario o contraseña incorrectos.");
            }
        });
        
        task.setOnFailed(e -> {
            App.showError("Error", "Ocurrió un error durante la autenticación. Inténtalo de nuevo.");
        });
        
        new Thread(task).start();
    }
    
    @FXML
    private void recuperarUser() {
        
    }
    
    @FXML
    private void olvideContrasenha() {
        
    }
    
}
