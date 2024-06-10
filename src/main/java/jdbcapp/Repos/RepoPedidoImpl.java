package jdbcapp.Repos;

import jdbcapp.Modelos.Cliente;
import jdbcapp.Modelos.Pedido;
import jdbcapp.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoPedidoImpl implements Repo<Pedido>{

    private Connection cogeConexion() throws SQLException {
        return ConexionBD.abrirConexion();
    }

    @Override
    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();

        try(Connection conn = cogeConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT p.codigo_pedido, p.fecha_pedido, p.codigo_cliente, c.nombre_cliente, c.nombre_contacto
                    FROM Pedido p
                        JOIN Cliente c ON p.codigo_cliente = c.codigo_cliente
                    """)){
            while (rs.next()) {
                pedidos.add(crearPedido(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Optional<Pedido> findBy(Integer id) {
        Pedido ped = new Pedido();

        // Creamos una sentencia con parámetros para ejecutar la consulta
        try(Connection conn = cogeConexion();
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT p.codigo_pedido, p.fecha_pedido, p.codigo_cliente, " +
                                    "c.nombre_cliente, c.nombre_contacto " +
                                    "FROM Pedido p " +
                                        "JOIN Cliente c " +
                                        "ON p.codigo_cliente = c.codigo_cliente " +
                                    "WHERE p.codigo_pedido = ?")){

            // Asignamos el valor de los parámetros
            stmt.setInt(1, id);

            // Ejecutamos la consulta para obtener el resultado
            try (ResultSet rs = stmt.executeQuery()){
                // Si la BD nos devuelve algún registro, será el que buscamos...
                if (rs.next()) {
                    //... entonces, asignamos los campos al objeto que creamos al principio
                    ped = crearPedido(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Devolvemos el cliente encontrado o un objeto nulo
        return Optional.of(ped);
    }

    @Override
    public Optional<Pedido> findByCadena(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Pedido> save(Pedido ped) {
        String qry;
        if (ped.getCodPedido() != null && ped.getCodPedido() > 0) {
            qry = """
                  UPDATE Pedido p
                  SET p.fecha_pedido = ? , p.codigo_cliente = ?
                  WHERE p.codigo_pedido = ?
                  """;
        } else {
            //Es un cliente nuevo
            qry = "INSERT INTO Pedido(fecha_pedido, codigo_cliente) VALUES(?,?)";
        }

        try (Connection conn = cogeConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setDate(1, ped.getFechaPedido());
            stmt.setInt(2, ped.getCliente().getCodigoCliente());
            if (ped.getCodPedido() != null && ped.getCodPedido() > 0) {
                stmt.setInt(3, ped.getCodPedido());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(ped);
    }

    @Override
    public void borrar(Integer id) {
        try (Connection conn = cogeConexion();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM pedido WHERE codigo_pedido = ? ")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Pedido crearPedido(ResultSet rs) throws SQLException {
        Pedido ped = new Pedido();
        Cliente cli = new Cliente();
        // Asigno los valores del cliente que realiza el pedido
        cli.setNombreCliente(rs.getString(4));
        cli.setNombreContacto(rs.getString(5));
        cli.setCodigoCliente(rs.getInt(3));
        // Asigno los valores desde el resultset
        ped.setCodPedido (rs.getInt(1));
        ped.setFechaPedido (rs.getDate(2));
        ped.setCliente (cli);
        return ped;
    }
}

