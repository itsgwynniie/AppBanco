package Controlador;

import Modelo.Tarjeta;
import Modelo.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gwyneth
 */

public class TarjetaController {
    private DBConnection connection = new DBConnection();
    
    public int Create (Tarjeta tarjeta) {
        int result = 0;
        
        String request = "INSERT INTO CUENTAS (CEDULA, BALANCE) VALUES (?, ?)";
        
        try {
            if (this.connection.conectar()) {
                PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
                
                statement.setString(1, tarjeta.getCedula().get());
                statement.setDouble(2, tarjeta.getBalance().get());
                
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
}
