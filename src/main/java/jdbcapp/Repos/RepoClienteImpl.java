package jdbcapp.Repos;

import jdbcapp.Modelos.Cliente;
import jdbcapp.util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoClienteImpl implements Repo<Cliente> {

    private Connection cogeConexion() throws SQLException {
        return ConexionBD.abrirConexion();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();

        try(Statement stmt = cogeConexion().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT codigo_cliente, nombre_cliente, nombre_contacto FROM Cliente")){
            while (rs.next()) {
                // Creo un nuevo cliente vacío
                Cliente cli = new Cliente();
                // Asigno los valores desde el resultset
                cli.setCodigoCliente(rs.getInt(1));
                cli.setNombreCliente(rs.getString(2));
                cli.setNombreContacto(rs.getString("nombre_contacto"));
                clientes.add(cli);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> findBy(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findByCadena(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> save(Cliente entity) {
        return Optional.empty();
    }

    @Override
    public void borrar(Integer id) {

    }
}
