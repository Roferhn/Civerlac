
package civerlac;

/**
 *
 * @author Roger Sifontes
 */
public class Login {
    
    private String user;
    private String pass;
    private String status;

    public Login() {
    }

    public Login(String user, String pass, String status) {
        this.user = user;
        this.pass = pass;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
