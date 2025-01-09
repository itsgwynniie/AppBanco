package Controlador;

import Modelo.DBConnection;
import Modelo.Usuario;
import itsgwynniie.appbanco.App;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gwyneth
 */

public class UsuarioController {
    private DBConnection connection = new DBConnection();
    
    public Usuario InicioSesion (String usuario, String clave) {
        Usuario user = null;
        
        String request = "SELECT CEDULA, NOMBRE, APELLIDO, FECHANAC, USUARIO, CLAVE FROM USUARIOS WHERE USUARIO = ?";
        
        try {
            if (this.connection.conectar()) {
                PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
                statement.setString(1, usuario);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    String claveDB = result.getString("CLAVE");

                    if (App.confClave(clave, claveDB)) {
                        user = new Usuario(
                            result.getString("CEDULA"),
                            result.getString("NOMBRE"),
                            result.getString("APELLIDO"),
                            result.getDate("FECHANAC").toLocalDate(),
                            result.getString("USUARIO"),
                            null);
                    } else {
                        System.err.println("Contraseña incorrecta.");
                    }
                } else {
                    System.err.println("Usuario no encontrado.");
                }
            } else {
                System.err.println("No se puede conectar la base de datos.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al realizar el inicio de sesión", e);
        } finally {
            if (this.connection!= null) {
                this.connection.desconectar();
            }
        }
        
        return user;
    }
    
    public int Create (Usuario usuario) {
        int result = 0;
        
        String request = "INSERT INTO USUARIOS (CEDULA, NOMBRE, APELLIDO, FECHANAC, USUARIO, CLAVE) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            if (this.connection.conectar()) {
                PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
                
                statement.setString(1, usuario.getCedula());
                statement.setString(2, usuario.getNombre());
                statement.setString(3, usuario.getApellido());
                statement.setDate(4, Date.valueOf(usuario.getFecha()));
                statement.setString(5, usuario.getUsuario());
                statement.setString(6, usuario.getClave());
                
                result = statement.executeUpdate();
            } else {
                System.err.println("No se puede conectar la base de datos.");
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.err.println("Error: La cédula o el usuario ya están registrados.");
            } else {
                throw new RuntimeException("Error al registrar el usuario: ", e);
            }
        } finally {
            if (this.connection != null)
                connection.desconectar();
        }
        
        return result;
    }
    
    public boolean SearchIdAndPassword (String cedula, String clave) {
        String request = "{CALL VERIFICAR_CEDULA_CLAVE(?, ?)}";
        try {
            if (this.connection.conectar()) {
                CallableStatement statement = connection.ejecutarCall(request);
                
                statement.setString(1, cedula);
                statement.setString(2, clave);
                
                ResultSet result = statement.executeQuery();
                
                return result.next();
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            connection.desconectar();
        }
    }
    
    public void SearchCard (String usuario) {
        
    }
}
