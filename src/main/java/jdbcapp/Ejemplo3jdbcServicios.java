package jdbcapp;

import jdbcapp.Modelos.Pedido;
import jdbcapp.Servicios.Servicio;
import jdbcapp.Servicios.ServicoImpl;

import java.sql.SQLException;
import java.util.List;


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

        // Si el pedido existe, ponemos los datos

        // Si el pedido NO existe, mostramos el mensaje "El pedido xxx no existe" siendo xxx el nro. de pedido solicitado
    }
}
