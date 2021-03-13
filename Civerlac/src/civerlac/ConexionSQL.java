
package civerlac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Roger Sifontes
 */
public class ConexionSQL {
    Connection con;
    
    public Connection getConnection(){
        try{
            String myDB = "jdbc:mysql//localhost:3306/civerlac?serverTimezone=UTC";
            con = DriverManager.getConnection(myDB,"root","");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
}
