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
import java.util.List;

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
        String sql = "INSERT INTO proveedor(idProveedor, Nombre, Correo, idTelefonoProv, Estado) VALUES (?,?,?,?,?)";
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, pr.getIdProveedor());
           ps.setString(2, pr.getNombre());
           ps.setString(3, pr.getCorreo());
           ps.setInt(4, pr.getIdTelefonoProv());
           ps.setString(5, pr.getEstado());
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
        List<Proveedor> Listapr = new ArrayList();
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
                pr.setIdTelefonoProv(rs.getInt("idTelefonoProveedor"));
                pr.setEstado(rs.getString("Estado"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
   
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET idProveedor=?, Nombre=?, idTelefonoProv=?, Correo=?, Estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getIdProveedor());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getIdTelefonoProv());
            ps.setString(5, pr.getEstado());
            ps.setString(6, pr.getCorreo());
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
 
    
