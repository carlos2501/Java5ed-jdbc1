package jdbcapp.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/jardineria";
    private static String usuario = "jardinero";
    private static String clave = "jardinero%";
    // Creamos un objeto pool
    private static BasicDataSource pool;

    // Inicializar el pool
    public static BasicDataSource getpool() {
        if(pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(usuario);
            pool.setPassword(clave);
            pool.setInitialSize(3); //<-- cantidad de conexiones habilitadas al inicio. Por defecto, es 0
            pool.setMinIdle(3);     //<-- min. de conexiones esperando para ser utilizadas. Por defecto, es 0
            pool.setMaxIdle(8);     //<-- idem, pero máximo
            pool.setMaxTotal(8);    //<-- Total de conexiones entre inactivas (sin utilizar) y utilizadas
        }
        return pool;
    }

    // Devolvemos una conexión del pool
    public static Connection abrirConexion() throws SQLException {
        return getpool().getConnection();
    }
}
