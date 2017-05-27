/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.BasicSessionBean;
import entity.Letter;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LetterSession extends BasicSessionBean{
    
    public Letter addLetter(String name, String email){
        Letter letter = new Letter();
        letter.setName(name);
        letter.setEmail(email);
        getEm().persist(letter);
        return letter;
    }
    
    public void removeLetter(int idLetter){
        getEm().remove(getEm().find(Letter.class, idLetter));
    }
    
    public List<Letter> getAllLetters(){
        return getList(Letter.class, "select l from Letter l");
    }
    
}
