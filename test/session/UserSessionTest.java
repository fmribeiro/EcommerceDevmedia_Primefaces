/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Address;
import entity.User;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import types.CountryType;

/**
 *
 * @author Administrador
 */
public class UserSessionTest {
    
    public static Integer idOfUser;
    public static EJBContainer container;
    
        
    public UserSessionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         container = javax.ejb.embeddable.EJBContainer.createEJBContainer();

    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of saveUser method, of class UserSession.
     */
    @Test
    public void testSaveUser() throws Exception {
       System.out.println("saveUser");
        
        //Testando a criação de um usuario
        User user = new User();
        user.setEmail("fmribeiro21@gmail.com");
        user.setName("Fabrício Mendes");
        user.setPassword("384738");
        user.setUsername("FABRICIO");
        
        //Criando o endereço do usuario
        Address address = new Address();
        address.setAddress("Rua Cotinga");
        address.setCountry(CountryType.BRASIL);
        address.setNumberOf(222);
        address.setNickname("Principal");
        address.setPostalCode("08032-559");
        address.setStateOfAddress("São Paulo");
        
        //setando o endereço do usuario
        user.addAddress(address);              
        
        UserSession instance = getUserSession();
        //User expResult = null;
        User result = instance.saveUser(user);
        idOfUser = result.getId();
        
        assertTrue(result.getId().intValue() > 0);
        assertTrue(result.getAddresses().get(0).getId().intValue() > 0);
    }

    /**
     * Test of getUserById method, of class UserSession.
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("userById("+idOfUser+")");                
        UserSession instance = getUserSession();
        User result = instance.getUserById(idOfUser);
        assertNotNull(result);
        assertEquals("Juliana Braitt", result.getName());
        assertTrue(result.getAddresses().size() > 0);
    }

    /**
     * Test of setUser method, of class UserSession.
     */
    @Test
    public void testSetUser() throws Exception {
        final String name = "Juliana Braitt";
        UserSession instance = getUserSession();
        User user = instance.getUserById(idOfUser);
        assertNotNull(user);
        user.setName(name);
        instance.setUser(user);
        user = instance.getUserById(idOfUser);
        assertEquals(user.getName(),name);
    }

    /**
     * Test of getallUsers method, of class UserSession.
     */
    @Test
    public void testGetallUsers() throws Exception {
        System.out.println("getallUsers");        
        UserSession instance = getUserSession();
        List expResult = instance.getallUsers();
        assertNotNull(expResult);
        assertTrue(expResult.size() >= 1);                
    }

    /**
     * Test of getUserByName method, of class UserSession.
     */
    @Test
    public void testGetUserByName() throws Exception {
         System.out.println("getUserByName");
        final String name = "Juliana Braitt";
       
        UserSession instance = getUserSession();        
        List<User> result = instance.getUserByName(name);
        assertNotNull(result);
        assertTrue(result.size() >= 1);
        assertEquals(result.get(0).getName(), name);
    }

    /**
     * Test of isUserOk method, of class UserSession.
     */
    @Test
    public void testIsUserOk() throws Exception {
        System.out.println("isUserOk");
        String username = "FABRICIO";
        String password = "384738";        
        UserSession instance = getUserSession();
        assertNotNull(instance.isUserOk(username, password));
    }

    /**
     * Test of getAddressssOfUser method, of class UserSession.
     */
    @Test
    public void testGetAddressssOfUser() throws Exception {
        System.out.println("getAddressssOfUser");        
        UserSession instance = getUserSession();
        User user = instance.getUserById(idOfUser);
        assertNotNull(user);
        List<Address> addr = instance.getAddressssOfUser(user);
        assertNotNull(user);
        assertTrue(addr.size() == 1);
        assertEquals(addr.get(0).getAddress(), "Rua Cotinga");
    }

    /**
     * Test of getAddress method, of class UserSession.
     */
    @Test
    public void testGetAddress() throws Exception {
         System.out.println("getAddress");        
        UserSession instance = getUserSession();
        User user = instance.getUserById(idOfUser);
        assertNotNull(user);
        List<Address> addrs = instance.getAddressssOfUser(user);
        int idAddress = addrs.get(0).getId();
        Address result = instance.getAddress(idAddress);
        assertEquals(addrs.get(0), result);
    }

    /**
     * Test of removeUser method, of class UserSession.
     */
    @Test
    public void testRemoveUser_User() throws Exception {
        System.out.println("removeUser");                
        UserSession instance = getUserSession();
        
        //Testando a criação de um usuario
        User user = new User();
        user.setEmail("fmribeiro21@gmail.com");
        user.setName("Fabrício Mendes2");
        user.setPassword("3847382");
        user.setUsername("FABRICIO2");        
        //Criando o endereço do usuario
        Address address = new Address();
        address.setAddress("Rua Cotinga 2");
        address.setCountry(CountryType.BRASIL);
        address.setNumberOf(222);
        address.setNickname("Principal");
        address.setPostalCode("08032-559");
        address.setStateOfAddress("São Paulo");        
        //setando o endereço do usuario
        user.addAddress(address);              
        
        User savedUser = instance.getUserById(instance.saveUser(user).getId());
        instance.removeUser(savedUser);
        
        User removedUser = instance.getUserById(savedUser.getId());
        assertNull(removedUser);
        
    }

    /**
     * Test of removeUser method, of class UserSession.
     */
    @Test
    public void testRemoveUser_int() throws Exception {
       System.out.println("removeUser");       
        UserSession instance = getUserSession();        
        boolean result = instance.removeUser(idOfUser);
        assertTrue(result);
        User user = instance.getUserById(idOfUser);
        assertNull(user);
    }
    
    private UserSession getUserSession() throws NamingException{
        return (UserSession)container.getContext().lookup("java:global/classes/UserSession");
    }
}
