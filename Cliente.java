
package civerlac;

public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String Correo;
    private String Direccion;
    private int idTelefonoCli;
    private int estado;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String Correo, String Direccion, int idTelefonoCli, int estado) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.Correo = Correo;
        this.Direccion = Direccion;
        this.idTelefonoCli = idTelefonoCli;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getIdTelefonoCli() {
        return idTelefonoCli;
    }

    public void setIdTelefonoCli(int idTelefonoCli) {
        this.idTelefonoCli = idTelefonoCli;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
