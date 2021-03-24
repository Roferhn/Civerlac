
package civerlac;

public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String Correo;
    private String Direccion;
    private int idTelefonoCli;
    private int idEstado;
    private String estado;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String Correo, String Direccion, int idTelefonoCli,int idEstado, String estado) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.Correo = Correo;
        this.Direccion = Direccion;
        this.idTelefonoCli = idTelefonoCli;
        this.idEstado = idEstado;
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

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
