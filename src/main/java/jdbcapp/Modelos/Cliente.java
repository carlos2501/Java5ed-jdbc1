package jdbcapp.Modelos;


public class Cliente {
    // Creamos una propiedad para cada campo de la tabla Clientes
    private Integer codigoCliente;
    private String nombreCliente;
    private String nombreContacto;
    // ... aqui añadiremos el resto de las propiedades -campos-

    // Indicamos los constructores de la clase
    public Cliente(Integer codigoCliente, String nombreCliente, String nombreContacto) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.nombreContacto = nombreContacto;
    }

    public Cliente() {
    }

    // Definimos las funciones para leer y asignar valores a las propiedades
    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}
