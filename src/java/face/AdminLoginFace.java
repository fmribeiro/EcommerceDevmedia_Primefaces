/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class AdminLoginFace extends BaseCDIBean implements Serializable{
    
    private static final long serialVersionUID = 1l;
    
    @NotNull(message="O campo Login não pode ser nulo.")
    private String login;
    @NotNull(message="O campo Senha não pode ser nulo.")
    @Min(value=5,message="A senha precisa ter mais que cinco caracteres.")
    private String password;
    private boolean adminUserLogged = false;
    
    public String doLogin(){
        if(getLogin().equalsIgnoreCase("fabricio") && getPassword().equals("12345")){
            adminUserLogged = true;           
        }else{
            adminUserLogged = false;
        }
        
        if(isAdminUserLogged()){
             return "main.faces";
        }else{
             return "loginerr.faces";
        }
        
    }
    
    public boolean isAdminUserLogged(){
        return adminUserLogged;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
