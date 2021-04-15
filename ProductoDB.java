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
import javax.swing.JOptionPane;

/**
 *
 * @author khaleb
 */
public class ProductoDB {

    
    //
    ConexionSQL cn = new ConexionSQL();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    //producto prod= new producto();
     public boolean registrarProducto( producto prod  ){
        
        String sql = "INSERT INTO `producto`( `Nombre`, `precio`,`stock`) VALUES (?,?,?)";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           // ps.setInt(1, cl.getIdCliente());
            ps.setString(1, prod.getNombre());
            ps.setDouble(2, prod.getPreciol());
            ps.setInt(3, prod.getStock());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }/*finally{
            try{
               con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }*/
        
    }
     
     public boolean UpdateProducto(producto prod ){
        
        String sql = "UPDATE  `producto` set  `Nombre`=?, `precio`=?, `stock`=? WHERE idProducto=?";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, prod.getNombre());
            ps.setDouble(2, prod.getPreciol());
            ps.setInt(3, prod.getStock());;
            ps.setInt(4, prod.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }/*finally{
            try{
               con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }*/
        
    }
         public boolean DeleteProducto(producto prod ){
        
        String sql = "Delete FROM `producto` WHERE idProducto=?";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, prod.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }/*finally{
            try{
               con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }*/
        
    }
     
     public List listarProductos(){
    
        List<producto> listaProd = new ArrayList();
        String sql = "SELECT * FROM `producto`";
        //String sql = "SELECT c.idCliente, c.Nombre, c.Correo, c.Direccion, tc.Telefono, c.Estado FROM cliente AS c INNER JOIN telefonocliente AS tc ON c.idTelefonoCli = tc.idTelefonoCli";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                producto cl = new producto();
                
                cl.setId((rs.getInt("idProducto")));
                cl.setNombre((rs.getString("Nombre")));
                cl.setPreciol((rs.getDouble("precio")));
                cl.setStock((rs.getInt("stock")));
         
         
                listaProd.add(cl);
                 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaProd;
    }
    
}
