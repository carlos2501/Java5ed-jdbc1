package jdbcapp;

import jdbcapp.Modelos.Pedido;
import jdbcapp.Servicios.Servicio;
import jdbcapp.Servicios.ServicoImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Ejemplo3jdbcServicios {
    public static void main(String[] args) {
        Servicio srvc = new ServicoImpl();

        System.out.println("------------------ Listado de pedidos ------------------------");
        List<Pedido> listaped = srvc.listaPed();
        for (Pedido p : listaped) {
            System.out.println(p);
        }
        System.out.println("------------------ Buscar pedido ------------------------");
        // Preguntamos al usuario el código del pedido a buscar
        Scanner s = new Scanner(System.in);
        System.out.println("Introduzca el código del pedido a buscar: ");
        Integer codPed = s.nextInt();
        s.close();
        // Busco el pedido
        Optional<Pedido> ped = srvc.porCodigo(codPed);
        if(ped.isPresent() && ped.get().getCodPedido() != null){
            // Si el pedido existe, ponemos los datos
            System.out.println(ped.get());
        } else {
            // Si el pedido NO existe, mostramos el mensaje "El pedido xxx no existe" siendo xxx el nro. de pedido solicitado
            System.out.println("El pedido con código " + codPed + " no existe.");
        }
    }
}
