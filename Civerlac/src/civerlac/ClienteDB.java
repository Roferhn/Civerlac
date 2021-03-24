
package civerlac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;


public class ClienteDB {
    
    ConexionSQL cn = new ConexionSQL();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean registrarClientes(Cliente cl){
        
        String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `idEstado`) VALUES (?,?,?,?,?,?)";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getIdCliente());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getIdTelefonoCli());
            ps.setInt(6, cl.getEstado());
            ps.execute();
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
    
    
    public List listarClientes(){
    
        List<Cliente> listaCL = new ArrayList();
        String sql = "SELECT * FROM `cliente`";
        //String sql = "SELECT c.idCliente, c.Nombre, c.Correo, c.Direccion, tc.Telefono, c.Estado FROM cliente AS c INNER JOIN telefonocliente AS tc ON c.idTelefonoCli = tc.idTelefonoCli";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl = new Cliente();
                
                cl.setIdCliente(rs.getInt("idCliente"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setCorreo(rs.getString("Correo"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setIdTelefonoCli(rs.getInt("idTelefonoCli"));
                cl.setEstado(rs.getInt("Estado"));
                listaCL.add(cl);
                 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCL;
    }

    
    public boolean modificarCliente(Cliente cl){
        
        String sql = "UPDATE cliente SET idCliente=? ,Nombre=?,Correo=?,Direccion=?,idTelefonoCli=?,Estado=? WHERE idCliente=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getIdCliente());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getIdTelefonoCli());
            ps.setInt(6, cl.getEstado());
            ps.setInt(7, cl.getIdCliente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }/*finally{
            try{
               con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }*/
    
    }
    
}
