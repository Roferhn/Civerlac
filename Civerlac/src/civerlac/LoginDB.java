
package civerlac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    
    Connection con = null;
    PreparedStatement ps= null;
    ResultSet rs= null;
    ConexionSQL cn = new ConexionSQL();
    
    public Login log(String user, String pass){
        
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE idEmpleado = ? AND contrasena = ?";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            if(rs.next()){
                l.setUser(rs.getString("idEmpleado"));
                l.setPass(rs.getString("contrasena"));
                l.setEstado(rs.getInt("idEstado"));
                System.out.println(l.getUser()+l.getPass()+l.getEstado());
            } 
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return l;
    }
    
    public boolean bloquearUsusario(Login l){
        
        String sql = "UPDATE empleados SET idEstado=0 WHERE idEmpleado=?";
        try {
            ps.setString(2, l.getUser());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
}
    
}
