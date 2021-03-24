
package civerlac;

/**
 *
 * @author Roger Sifontes
 */
public class Login {
    
    private String user;
    private String pass;
    private int estado;
   

    public Login() {
    }

    public Login(String user, String pass, int estado) {
        this.user = user;
        this.pass = pass;
        this.estado = estado;
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

   

   
    
    
}
