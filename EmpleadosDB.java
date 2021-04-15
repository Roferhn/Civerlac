
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


public class EmpleadosDB {
    
    ConexionSQL cn = new ConexionSQL();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean registrarEmpleado(Empleado cl){
        
        String sql = "INSERT INTO `empleado`( `Nombre`, `Ocupacion` , `Correo`, `Direccion`, `idTelefonoEmp`, `Estado`) VALUES (?,?,?,?,?,?)";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           // ps.setInt(1, cl.getIdCliente());
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getOcupacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getTelefono());
            ps.setInt(6, cl.getEstado());
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
    public boolean UpdateEmpleado(Empleado cl){
        
        String sql = "UPDATE  `empleado` set  `Nombre`=?, `Ocupacion`=?, `Correo`=?, `Direccion`=?, `idTelefonoEmp`=?, `Estado`=? WHERE idEmpleado=? ";
        //String sql = "INSERT INTO `cliente`(`idCliente`, `Nombre`, `Correo`, `Direccion`, `idTelefonoCli`, `Estado`) VALUES (?,?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getOcupacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getTelefono());
            ps.setInt(6, cl.getEstado());
            ps.setInt(7, cl.getIdEmpleado());
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
    
    
    public List ListEmpleados(){
    
        List<Empleado> listaCL = new ArrayList();
        String sql = "SELECT * FROM `empleado`";
        //String sql = "SELECT c.idCliente, c.Nombre, c.Correo, c.Direccion, tc.Telefono, c.Estado FROM cliente AS c INNER JOIN telefonocliente AS tc ON c.idTelefonoCli = tc.idTelefonoCli";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Empleado cl = new Empleado();
                
                cl.setIdEmpleado(rs.getInt("idEmpleado"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setOcupacion(rs.getString("Ocupacion"));
                cl.setCorreo(rs.getString("Correo"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setTelefono(rs.getInt("idTelefonoEmp"));
                cl.setEstado(rs.getInt("Estado"));
                listaCL.add(cl);
                 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCL;
    }
    public List listarClientesBusqueda(String filtro,String dato){
        
        String sql=null;
        if( "Nombre".equals(filtro) ){
            sql = "SELECT * FROM `empleado` WHERE "+filtro+" LIKE '%"+dato+"%' ";
        }
        if( "Estado".equals(filtro) ){
            sql = "SELECT * FROM `empleado` WHERE "+filtro+" LIKE '%"+dato+"%' ";
        }
        if("idEmpleado".equals(filtro)){
            sql = "SELECT * FROM `empleado` WHERE "+filtro+"=?";
        }
        System.out.println(sql);
        List<Empleado> listaCL = new ArrayList();
        
        //String sql = "SELECT c.idCliente, c.Nombre, c.Correo, c.Direccion, tc.Telefono, c.Estado FROM cliente AS c INNER JOIN telefonocliente AS tc ON c.idTelefonoCli = tc.idTelefonoCli";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           // ps.setString(1, dato);
           

            if("idEmpleado".equals(filtro)){
               ps.setString(1, dato);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                Empleado cl = new Empleado();
                
                cl.setIdEmpleado(rs.getInt("idEmpleado"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setCorreo(rs.getString("Correo"));
                cl.setOcupacion(rs.getString("Ocupacion"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setTelefono(rs.getInt("idTelefonoEmp"));
                cl.setEstado(rs.getInt("Estado"));
                listaCL.add(cl);
                 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCL;
    }

    
}
