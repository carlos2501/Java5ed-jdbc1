package jdbcapp;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Modelos.Pedido;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoPedidoImpl;

import java.sql.Date;
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

        System.out.println("------------------ Lista pedidos ------------------------");
        List<Pedido> listaped = repo.findAll();
        for( Pedido p : listaped){
            System.out.println(p);
        }

        System.out.println(" ------------------- Añadir pedido nuevo ----------------");
        Pedido ped = new Pedido();
        Pedido nped;
        Cliente cli = new Cliente();
        cli.setCodigoCliente(9);
        ped.setFechaPedido(Date.valueOf("2024-06-05"));
        ped.setCliente(cli);
        nped = repo.save(ped).get();
        if(nped.getCodPedido() == null){
            System.out.println("He creado un pedido nuevo");
        }else {
            System.out.println("He actualizado el pedido");
        }

        System.out.println(" ---------------- Eliminar pedido -----");
        repo.borrar(34);
    }
}
