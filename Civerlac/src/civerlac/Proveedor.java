/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civerlac;

/**
 *
 * @author Omaly Breve
 */
public class Proveedor {
    
    private int idProveedor;
    private String Nombre;
    private String Correo;
    private int idTelefonoProv;
    private String Estado;
    
    public Proveedor() {
        
      
    }

    public Proveedor(int idProveedor, String Nombre, String Correo, int idTelefonoProv, String Estado) {
        this.idProveedor = idProveedor;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.idTelefonoProv = idTelefonoProv;
        this.Estado = Estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getIdTelefonoProv() {
        return idTelefonoProv;
    }

    public void setIdTelefonoProv(int idTelefonoProv) {
        this.idTelefonoProv = idTelefonoProv;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String proveedor) {
        this.Estado = Estado;
    }

    
    }
    

