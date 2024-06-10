package jdbcapp.Servicios;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Modelos.Pedido;
import jdbcapp.Repos.Repo;
import jdbcapp.Repos.RepoClienteImpl;
import jdbcapp.Repos.RepoPedidoImpl;

import java.util.List;
import java.util.Optional;

public class ServicoImpl implements Servicio {
    private Repo<Cliente> repoCli;
    private Repo<Pedido> repoPed;

    public ServicoImpl() {
        this.repoCli = new RepoClienteImpl();
        this.repoPed = new RepoPedidoImpl();
    }

    @Override
    public List<Pedido> listaPed() {
        return repoPed.findAll();
    }

    @Override
    public Optional<Pedido> porCodigo(Integer ped) {
        return repoPed.findBy(ped);
    }

    @Override
    public Pedido guardaPed(Pedido ped) {
        return null;
    }

    @Override
    public void borraPed(Integer codPed) {

    }

    @Override
    public List<Cliente> listaCli() {
        return null;
    }

    @Override
    public Cliente porCodCli(Integer codCli) {
        return null;
    }

    @Override
    public Cliente guardaCli(Cliente cli) {
        return null;
    }

    @Override
    public void borraCli(Integer codCli) {

    }
}
