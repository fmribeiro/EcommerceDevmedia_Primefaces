/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.BasicSessionBean;
import entity.Address;
import entity.CallOnAvaliable;
import entity.User;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserSession extends BasicSessionBean {

    public void removeAddress(int idOfAddress) {
        Address address = getEm().find(Address.class, idOfAddress);
        getEm().remove(address);
    }

    public Address setAddress(Address address) {
        return getEm().merge(address);
    }

    public User saveUser(User user) {
        getEm().persist(user);
        return user;
    }

    public User getUserById(int idUser) {
        return getPojo(User.class, idUser);
    }

    public List<User> getMostActiveUsers() {
        Query qr = getEm().createNativeQuery("SELECT USEROF_id, count(*) as qtde from Sell group by USEROF_id order by qtde desc limit 10");
        List<Object> returned = qr.getResultList();
        //Set<Integer> users = new LinkedHashSet<Integer>();
        StringBuilder sb = new StringBuilder();
        for (Object object : returned) {
            Integer idUser = (Integer) ((Object[]) object)[0];
            //users.add(idUser);            
            sb.append(idUser + ",");
        }
        //if(users.isEmpty()){
        if (sb.toString().isEmpty()) {
            return new LinkedList<User>();
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return getList(User.class, "select us from User us where us.id in (" + sb.toString() + ")");
    }

    public List<User> getLastUsers() {
        return getLimitedList(User.class, "select us from User us order by us.id desc", 10);
    }

    public User setUser(User user) {
        getEm().merge(user);
        return user;
    }

    public List<User> getallUsers() {
        return getList(User.class, "select us from User us order by us.name");
    }

    public List<User> getUserByName(String name) {
        return getList(User.class, "select us from User us where us.name like ?1", "%" + name + "%");
    }

    public User isUserOk(String username, String password) {
        try {
            return getPojo(User.class, "select us from User us where us.username = ?1 and us.password = ?2", username, password);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Address> getAddressssOfUser(User user) {
        return getList(Address.class, "select addr from Address addr where addr.userOf = ?1 order by addr.address", user);
    }

    public Address getAddress(int idAddress) {
        return getPojo(Address.class, idAddress);
    }

    public void removeUser(User user) {
        user = getEm().merge(user);
        getEm().remove(user);

    }

    public boolean removeUser(int idUser) {
        execute("DELETE FROM Address addr where addr.userOf.id = ?1", idUser);
        boolean toReturn = execute("DELETE FROM User us where us.id = ?1", idUser) > 0;
        return toReturn;
    }

    public int getNumberOfProductsOnCallOnAvailable(User loggedUser) {
        return getList(CallOnAvaliable.class, "Select cal.id from CallOnAvaliable cal where cal.email = ?1", loggedUser.getEmail()).size();
    }

    public List<CallOnAvaliable> getCallAvailableToUser(User loggedUser) {
        return getList(CallOnAvaliable.class, "Select cal from CallOnAvaliable cal where cal.email = ?1", loggedUser.getEmail());
    }
}
