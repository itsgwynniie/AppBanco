package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gwyneth
 */

public class DBConnection {
    private Connection connection;
    
    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AppBanco", "postgres", "");
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public CallableStatement ejecutarCall(String sql) throws SQLException {
        if (connection != null) {
            return connection.prepareCall(sql);
        } else {
            throw new SQLException("No hay conexión activa con la base de datos.");
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
