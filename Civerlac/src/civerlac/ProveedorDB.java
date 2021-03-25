/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civerlac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Omaly Breve
 */
public class ProveedorDB {
    Connection con;
    ConexionSQL cn = new ConexionSQL();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor(idProveedor, Nombre, Correo, Direccion, idTelefonoProv, idEstado) VALUES (?,?,?,?,?,?)";
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, pr.getIdProveedor());
           ps.setString(2, pr.getNombre());
           ps.setString(3, pr.getCorreo());
           ps.setString(4, pr.getDireccion());
           ps.setInt(5, pr.getIdTelefonoProv());
           if(pr.getEstado()=="Habilitado"){
                pr.setIdEstado(1);  
            }else{
                pr.setIdEstado(0);
            }
           ps.setInt(6, pr.getIdEstado());
           ps.execute();
           return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarProveedor(){
        List<Proveedor> ListaPR = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setIdProveedor(rs.getInt("idProveedor"));
                pr.setNombre(rs.getString("Nombre"));
                pr.setCorreo(rs.getString("Correo"));
                pr.setDireccion(rs.getString("Direccion"));
                
                pr.setIdTelefonoProv(rs.getInt("idTelefonoProv"));
                pr.setIdEstado(rs.getInt("idEstado"));
                if (pr.getIdEstado()==1){
                    pr.setEstado("Habilitado");
                }else{
                    pr.setEstado("Deshabilitado");
                }
                ListaPR.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPR;
    }
    
   
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET idProveedor=?, Nombre=?,Correo=?, Direccion=?, idTelefonoProv=?,  idEstado=? WHERE idProveedor=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getIdProveedor());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getDireccion());
            ps.setInt(4, pr.getIdTelefonoProv());
            ps.setString(5, pr.getCorreo());
            if(pr.getEstado()=="Habilitado"){
                pr.setIdEstado(1);
            }else{
                pr.setIdEstado(0);
            }          
            ps.setInt(6, pr.getIdEstado());
            ps.setInt(7, pr.getIdProveedor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
 
    
