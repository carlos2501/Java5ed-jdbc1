package jdbcapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/jardineria";
    private static String usuario = "jardinero";
    private static String clave = "jardinero%";
    private static Connection conex;

    public static Connection abrirConexion() throws SQLException {
        if(conex == null) {
            conex = DriverManager.getConnection(url,usuario, clave);
        }
        return conex;
    }
}
