package itsgwynniie.appbanco;

import Controlador.UsuarioController;
import Modelo.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearUsuarioController {
    @FXML
    private TextField txtcedula;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtapellido;
    @FXML
    private DatePicker txtfecha;
    @FXML
    private TextField txtusuario;
    @FXML
    private TextField txtclave;
    @FXML
    private TextField txtconfclave;
    
    @FXML
    private CheckBox check;
    
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
    private void crearUsuario() throws IOException {
        String cedula = txtcedula.getText().trim();
        String nombre = txtnombre.getText().trim();
        String apellido = txtapellido.getText().trim();
        LocalDate fecha = txtfecha.getValue();
        String usuario = txtusuario.getText().trim();
        String clave = txtclave.getText().trim();
        String confclave = txtconfclave.getText().trim();
        
        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty()) {
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
        
        if (fecha == null) {
            App.showError("Error para continuar", "Debe seleccionar una fecha válida.");
            return;
        }
        
        LocalDate hoy = LocalDate.now();
        int edad = java.time.Period.between(fecha, hoy).getYears();
        if (edad < 18) {
            App.showError("Error para continuar", "Debe tener 18 años para poder crear una cuenta.");
            return;
        }
        
        if (!App.confClave(clave, confclave)) {
            App.showError("Error para continuar", "Las contraseñas no coinciden.");
            return;
        }
        
        if (!check.isSelected()) {
            App.showError("Error para continuar", "Debe aceptar las condiciones.");
            return;
        }
        
        Usuario user = new Usuario(cedula, nombre, apellido, fecha, usuario, clave);
        
        Task<Integer> task = new Task<>() {
            @Override
            protected Integer call() {
                return usu.Create(user);
            }
        };
        
        task.setOnSucceeded(e -> {
            int result = task.getValue();
            if (result > 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ContrataProducto.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) txtcedula.getScene().getWindow();

                    ContrataProductoController controller = loader.getController();

                    if (controller != null) {
                        controller.setCedulaTemporal(cedula);

                        App.showConfirmation("Crear Usuario", "Usuario creado exitosamente.");

                        stage.setScene(scene);
                        stage.show();
                    } else {
                        App.showError("Error al cargar pantalla", "No se pudo cargar el usuario correctamente.");
                    }
                } catch (IOException ex) {
                    App.showError("Error al cargar pantalla", "No se pudo cargar la siguiente pantalla correctamente.");
                }

            } else {
                App.showError("Crear Usuario", "Error al crear el usuario.");
            }
        });
        
        task.setOnFailed(e -> App.showError("Error", "Ocurrió un error durante la creación del usuario."));
        
        new Thread(task).start();
    }

}