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
public class VentasDB {
    
    
    
    
    ConexionSQL cn = new ConexionSQL();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean RegistrarVenta(Ventas cl){
        
        String sql = "INSERT INTO `factura`( `Cliente`, `Vendedor`, `total`) VALUES (?,?,?)";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           // ps.setInt(1, cl.getIdCliente());
            ps.setString(1, cl.getCliente());
            ps.setString(2, cl.getEmpleado());
            ps.setDouble(3, cl.getTotal());
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
    public List ListarHistorialVenta(){
    
        List<Ventas> listaProd = new ArrayList();
        String sql = "SELECT * FROM `factura`";
        //String sql = "SELECT c.idCliente, c.Nombre, c.Correo, c.Direccion, tc.Telefono, c.Estado FROM cliente AS c INNER JOIN telefonocliente AS tc ON c.idTelefonoCli = tc.idTelefonoCli";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Ventas cl = new Ventas();
                
                cl.setIdFactura((rs.getInt("idFactura")));
                cl.setCliente((rs.getString("Cliente")));
                cl.setEmpleado((rs.getString("Vendedor")));
                cl.setTotal((rs.getDouble("total")));
                listaProd.add(cl);
                 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaProd;
    }
        
    
}
