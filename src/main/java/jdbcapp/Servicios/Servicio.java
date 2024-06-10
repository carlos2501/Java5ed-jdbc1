package jdbcapp.Servicios;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Modelos.Pedido;

import java.util.List;
import java.util.Optional;

public interface Servicio {
    List<Pedido> listaPed();
    Optional<Pedido> porCodigo(Integer ped);
    Pedido guardaPed (Pedido ped);
    void borraPed(Integer codPed);
    List<Cliente> listaCli();
    Cliente porCodCli(Integer codCli);
    Cliente guardaCli(Cliente cli);
    void borraCli(Integer codCli);
}
