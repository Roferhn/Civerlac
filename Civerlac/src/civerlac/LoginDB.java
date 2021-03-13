
package civerlac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Roger Sifontes
 */
public class LoginDB {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionSQL cn = new ConexionSQL();
    
    public Login log(String user, String pass){
        
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE idEmpleado = ? AND contrasena = ?";
        
        try{
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(0,user);
            ps.setString(1,pass);
            rs = ps.executeQuery();
            
            if(rs.next()){
                l.setUser(rs.getString("idEmpleado"));
                l.setPass(rs.getString("contrasena"));
                l.setStatus(rs.getString("Estado"));
            } 
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return l;
    }
}
