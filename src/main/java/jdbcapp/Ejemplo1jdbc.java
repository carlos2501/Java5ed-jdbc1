package jdbcapp;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoClienteImpl;
import jdbcapp.util.ConexionBD;

import java.sql.*;
import java.util.List;

public class Ejemplo1jdbc {
    public static void main(String[] args)  {

        // Creamos una nuieva conexión en el try con recursos que enviaremos al contructor del repositorio
       try (Connection conex = ConexionBD.abrirConexion()){
           // Enviamos la conexión abierta en el try al constructor del repositorio
            Repo<Cliente> repo = new RepoClienteImpl(conex);
            System.out.println("------------------ Listado de clientes ------------------------");
            List<Cliente> lista = repo.findAll();
            for(Cliente c : lista){
                System.out.println(c);
            }
            System.out.println("------------------ Datos cliente 7 ------------------------");
            if(repo.findBy(7).get().getCodigoCliente() != null) {
                System.out.println(repo.findBy(700).get());
            } else {
                System.out.println("El cliente 700 no existe");
            }
       } catch (SQLException e) {
            e.printStackTrace();
       }
    }
}
