package jdbcapp;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoClienteImpl;
import jdbcapp.util.ConexionBD;

import java.sql.*;
import java.util.List;

public class Ejemplo1jdbc {
    public static void main(String[] args) throws SQLException {

        try (Connection conex = ConexionBD.abrirConexion()){
            Repo<Cliente> repo = new RepoClienteImpl();
            System.out.println("------------------ Listado de clientes ------------------------");
            List<Cliente> lista = repo.findAll();
            for(Cliente c : lista){
                System.out.println(c);
            }
            System.out.println("------------------ Datos cliente 7 ------------------------");
            System.out.println(repo.findBy(7).get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
