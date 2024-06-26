package jdbcapp.Repos;

import jdbcapp.Modelos.Cliente;
import jdbcapp.util.ConexionBD;

import java.sql.*;
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

        try(Connection conn = cogeConexion();
            Statement stmt = conn.createStatement();
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
        try(Connection conn = cogeConexion();
            PreparedStatement stmt = conn
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
        // Devolvemos el cliente encontrado o un objeto nulo
        return Optional.of(cli);
    }

    @Override
    public Optional<Cliente> findByCadena(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> save(Cliente cli) {
        // Preparamos la sentencia SQL que tenemos que ejecutar en cada caso
        String qry;
        if(cli.getCodigoCliente() != null && cli.getCodigoCliente() > 0) {
            // El cliente ya existe (tiene un codigo_cliente asignado)
            qry ="UPDATE Cliente SET nombre_cliente=?, nombre_contacto=? WHERE codigo_cliente=?";
        } else {
            // Es un cliente nuevo
            qry = "INSERT INTO Cliente(nombre_cliente, nombre_contacto) VALUES(?,?)";
        }

        try (Connection conn = cogeConexion();
            PreparedStatement stmt = conn.prepareStatement(qry)){
            // Asigno los valores de los parámetros de la consulta
            stmt.setString(1, cli.getNombreCliente());
            stmt.setString(2, cli.getNombreContacto());
            if(cli.getCodigoCliente() != null && cli.getCodigoCliente() > 0){
                stmt.setInt(3, cli.getCodigoCliente());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(cli);
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
