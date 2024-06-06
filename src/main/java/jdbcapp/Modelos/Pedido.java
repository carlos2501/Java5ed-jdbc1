package jdbcapp.Modelos;

import java.sql.Date;

public class Pedido {
    private Integer codPedido;
    private Date fechaPedido;
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Integer codPedido, Date fechaPedido, Cliente cliente) {
        this.codPedido = codPedido;
        this.fechaPedido = fechaPedido;
        this.cliente = cliente;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codPedido=" + codPedido +
                ", fechaPedido=" + fechaPedido +
                ", cliente=" + cliente +
                '}';
    }
}
