package jdbcapp.util;

import jdbcapp.Modelos.Pedido;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoPedidoImpl;

import java.util.List;

public class Ejemplo2jdbc {
    public static void main(String[] args) {
        Repo<Pedido> repo = new RepoPedidoImpl();
        System.out.println("------------------ Listado de clientes ------------------------");
        List<Pedido> lista = repo.findAll();
        for(Pedido p : lista){
            System.out.println(p);
        }
        System.out.println("------------------ Datos pedido 8 ------------------------");
        if(repo.findBy(8).get().getCliente() != null) {
            System.out.println(repo.findBy(8).get());
        } else {
            System.out.println("El pedido 800 no existe");
        }

    }
}
