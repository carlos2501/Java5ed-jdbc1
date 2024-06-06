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
        /*
            Como en el repositorio se abre y cierra la conexión en cada llamada (utilizamos try con recursos), cada vez
            necesitemos una conexión, hay que crearla y abrirla.
         */
        return DriverManager.getConnection(url,usuario, clave);
    }
}
