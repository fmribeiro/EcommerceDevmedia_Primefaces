/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entity.User;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

   @PersistenceContext
   EntityManager em;
   
   public User ping(){
       return em.find(User.class, 1);
   }
    
    
}
