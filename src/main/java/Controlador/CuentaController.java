package Controlador;

import Modelo.DBConnection;
import Modelo.Cuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gwyneth
 */

public class CuentaController {
    private DBConnection connection = new DBConnection();
    
    public int Create (Cuenta cuenta) {
        int result = 0;
        
        String request = "INSERT INTO CUENTAS (CEDULA, BALANCE, TIPO) VALUES (?, ?, ?)";
        
        try {
            if (this.connection.conectar()) {
                PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
                
                statement.setString(1, cuenta.getUsuario().get());
                statement.setDouble(2, cuenta.getBalance().get());
                statement.setString(3, cuenta.getTipo().get());
                
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
    
    public ArrayList<Integer> getCuentas(String usuario) {
        ArrayList<Integer> cuentas = new ArrayList<>();
        String request = "SELECT C.ID " +
                         "FROM CUENTAS C " +
                         "LEFT JOIN USUARIOS U ON C.CEDULA = U.CEDULA " +
                         "WHERE U.USUARIO = ?";
        
        try {
            this.connection.conectar();
            PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
            statement.setString(1, usuario);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                int id = result.getInt("ID");
                
                cuentas.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.connection != null) {
                this.connection.desconectar();
            }
        }
        
        return cuentas;
    }
}
