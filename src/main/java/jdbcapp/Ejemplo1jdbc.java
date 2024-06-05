package jdbcapp;

import jdbcapp.util.ConexionBD;

import java.sql.*;

public class Ejemplo1jdbc {
    public static void main(String[] args) {

        try (Connection conex = ConexionBD.abrixConexion();
             Statement sent = conex.createStatement();
             ResultSet result = sent.executeQuery("SELECT * FROM producto")){
            // El cursor del resultset apunta ANTES del primer registro
            while (result.next()) {
                String datos = result.getString("nombre") + " " +
                        result.getString("codigo_producto") + " "
                        + result.getDouble(8);
                System.out.println(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
