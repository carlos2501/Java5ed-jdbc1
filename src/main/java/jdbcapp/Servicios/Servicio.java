package jdbcapp.Servicios;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Modelos.Pedido;

import java.awt.color.ProfileDataException;
import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<Pedido> listaPed();
    Pedido porCodigo(Integer ped);
    Pedido guardaPed (Pedido ped);
    void borraPed(Integer codPed);
    List<Cliente> listaCli();
    Cliente porCodCli(Integer codCli);
    Cliente guardaCli(Cliente cli);
    void borraCli(Integer codCli);
}
