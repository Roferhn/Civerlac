
package civerlac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionSQL cn = new ConexionSQL();
    
    public Login log(String user, String pass){
        
        Login l = new Login();
        String sql = "SELECT idEmpleado, contrasena FROM `usuarios` WHERE idEmpleado = ? AND contrasena = ? AND Estado = 'Habilitado'";
        
        try{
            con = cn.getConnection();
            
            ps = con.prepareStatement(sql); //EN ESTA LINEA MUERE. ERROR = "Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException"

            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            if(rs.next()){
                l.setUser(rs.getString("idEmpleado"));
                l.setPass(rs.getString("contrasena"));
            } 
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return l;
    }
}
