package Controlador;

import Modelo.DBConnection;
import Modelo.Retiro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author gwyneth
 */

public class RetiroController {
    private DBConnection connection = new DBConnection();
    
    public int Create (Retiro retiro) {
        int result = 0;
        
        String request = "INSERT INTO RETIROS (IDCUENTA, MONTO) VALUES (?, ?)";
        
        try {
            if (this.connection.conectar()) {
                PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
                
                statement.setInt(1, retiro.getIdCuenta());
                statement.setDouble(2, retiro.getMonto());
                
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
    
    public ArrayList<Retiro> Read() {
        ArrayList<Retiro> retiros = new ArrayList<>();
        String request = "SELECT * FROM RETIROS";
        
        try {
            this.connection.conectar();
            PreparedStatement statement = this.connection.getConnection().prepareStatement(request);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("ID");
                int idCuenta = result.getInt("IDCUENTA");
                Double monto = result.getDouble("MONTO");
                
                Date sqlFecha = result.getDate("FECHA");
                LocalDate fecha = (sqlFecha != null) ? sqlFecha.toLocalDate() : null;
                
                retiros.add(new Retiro(id, idCuenta, monto, fecha));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (this.connection != null)
                this.connection.desconectar();
        }
        
        return retiros;
    }
}
