package jdbcapp;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoClienteImpl;

import java.util.List;

public class Ejemplo1jdbc {
    public static void main(String[] args)  {
        /*
            En este caso, la conexión se abre y se cierra en cada llamada a los procedimientos del repositorio
            por lo que no es necesario crearla en la aplicación principal

         */
        Repo<Cliente> repo = new RepoClienteImpl();
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
    }
}
