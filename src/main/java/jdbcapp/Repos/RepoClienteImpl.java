package jdbcapp.Repos;

import jdbcapp.Modelos.Cliente;
//import jdbcapp.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoClienteImpl implements Repo<Cliente> {
    /*
    private Connection cogeConexion() throws SQLException {
        return ConexionBD.abrirConexion();
    }
    */

    // Utilizamos la conexión creada en la aplicación principal (main). Esta conexión se mantiene abierta mientras
    // este activa la aplicación principal
    private Connection con;

    public RepoClienteImpl(Connection conn) {
        con = conn;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();

        try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT codigo_cliente, nombre_cliente, nombre_contacto FROM Cliente")){
            while (rs.next()) {
                clientes.add(crearCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> findBy(Integer id) {
        // Creamos un objeto cliente -instancia de la clase "Cliente" para devolver el valor que tenga o un objeto vacío
        Cliente cli = new Cliente();

        // Creamos una sentencia con parámetros para ejecutar la consulta
        try(PreparedStatement stmt = con
                .prepareStatement("SELECT * FROM Cliente WHERE codigo_cliente = ?")){

            // Asignamos el valor de los parámetros
            stmt.setInt(1, id);

            // Ejecutamos la consulta para obtener el resultado
            try (ResultSet rs = stmt.executeQuery()){
                // Si la BD nos devuelve algún registro, será el que buscamos...
                if (rs.next()) {
                    //... entonces, asignamos los campos al objeto que creamos al principio
                    cli = crearCliente(rs);
                }
            }

            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Devolemos el cliente encontrado o un objeto nulo
        return Optional.of(cli);
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

    private static Cliente crearCliente(ResultSet rs) throws SQLException {
        Cliente cli = new Cliente();
        // Asigno los valores desde el resultset
        cli.setCodigoCliente(rs.getInt(1));
        cli.setNombreCliente(rs.getString(2));
        cli.setNombreContacto(rs.getString("nombre_contacto"));
        return cli;
    }
}
