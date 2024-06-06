package jdbcapp;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoClienteImpl;

import java.util.List;

public class Ejemplo1jdbcEditar {
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
        if(repo.findBy(700).get().getCodigoCliente() != null) {
            System.out.println(repo.findBy(700).get());
        } else {
            System.out.println("El cliente 700 no existe");
        }
        System.out.println("------------------ Editamos un cliente  ------------------------");
        Cliente cli = repo.findBy(300).get();
        if(cli.getCodigoCliente() != null && cli.getCodigoCliente() > 0) {
            cli.setNombreCliente("Pepe");
            repo.save(cli);
            System.out.println("He modificado el cliente -> " + cli);
        } else {
            System.out.println("No he cambiado nada porque el cliente no se encuentra");
        }

    }
}
