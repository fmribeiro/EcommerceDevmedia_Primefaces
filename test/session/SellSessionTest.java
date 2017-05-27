/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Address;
import entity.Category;
import entity.CreditCard;
import entity.Product;
import entity.Sell;
import entity.SellItem;
import entity.User;
import java.math.BigDecimal;
import java.util.Date;
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
import types.FlagType;
import types.StatusSellType;

/**
 *
 * @author Administrador
 */
public class SellSessionTest {
    
    public static Integer idOfCategory1;
    public static Integer idOfProduct1;
    public static Integer idOfUser;
    public static Integer idOfSell;
    public static EJBContainer container;
        
    public SellSessionTest() {
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
    
    private SellSession getSellSession() throws NamingException{
        return(SellSession) container.getContext().lookup("java:global/classes/SellSession");
    }       
    
    
    /**
     * Test of saveSell method, of class SellSession.
     */
    @Test
    public void testSaveSell() throws Exception {
        System.out.println("saveSell");        
        //A categoria
        Category category1 = new Category();       
        category1.setActive(true);
        category1.setName("cat 1");
        //O produto
        Product product1 = new Product();        
        product1.setCategory(category1);
        product1.setCost(new BigDecimal("10.2"));
        product1.setName("product1");
        product1.setStock(200);
        product1.setSpec("spec 1");    
        //O usuário
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
        
        //Criando um cartão de crédito
        CreditCard creditCard = new CreditCard();
        creditCard.setDateOfValidation(new Date());
        creditCard.setFlag(FlagType.VISA);
        creditCard.setNameOfCardOwner("Fabricio M Ribeiro");
        creditCard.setNumber("1111222233334444");
        creditCard.setTimes(1);
        user.addCreditCard(creditCard);
        
        
        //Criando uma venda
        SellSession instance = getSellSession();
        Sell sell = new Sell();
        sell.setAddressToSend(address);
        sell.setUserOf(user);
        sell.setStatus(StatusSellType.INICIO);
                
        SellItem sellItem = new SellItem();
        sellItem.setProduct(product1);
        sellItem.setQnt(1);
        
        //adicionando o item a venda
        sell.addItem(sellItem);
        sell.setCreditcard(creditCard);
        creditCard.getSells().add(sell);
        
        Sell result = instance.saveSell(sell);
        assertNotNull(result);
        assertTrue(result.getId() > 0);
        
        idOfCategory1 = category1.getId();
        idOfProduct1 = product1.getId();
        idOfUser = user.getId();
        idOfSell = sell.getId();
        
    }
    
    /**
     * Test of getSell method, of class SellSession.
     */
    @Test
    public void testGetSell() throws Exception {
        System.out.println("getSell");
        SellSession instance = getSellSession();
        Sell result = instance.getSell(idOfSell);
        assertNotNull(result);
        assertEquals(idOfSell, result.getId());
    }
    
    /**
     * Test of setSell method, of class SellSession.
     */
    
    public void testSetSell() throws Exception {
        System.out.println("setSell");
        SellSession instance = getSellSession();
        Sell sell = instance.getSell(idOfSell);
        sell.setTotal(BigDecimal.valueOf(6565));        
        Sell result = instance.setSell(sell);
        assertEquals(BigDecimal.valueOf(6565), result.getTotal());
    }
    
    
    /**
     * Test of isCreditCardValidForSell method, of class SellSession.
     */
    @Test
    public void testIsCreditCardValidForSell() throws Exception {
        System.out.println("isCreditCardValidForSell");
        SellSession instance = getSellSession();
        Sell sell = instance.getSell(idOfSell);        
        boolean result = instance.isCreditCardValidForSell(sell);
        assertTrue(result);
    }
    
    /**
     * Test of setStatusOfSell method, of class SellSession.
     */
    @Test
    public void testSetStatusOfSell() throws Exception {
        System.out.println("setStatusOfSell");
        SellSession instance = getSellSession();
        Sell sell = instance.getSell(idOfSell);
        instance.setStatusOfSell(sell, StatusSellType.EMPROGRESSO);
        sell = instance.getSell(idOfSell);
        assertNotNull(sell.getStatus());
        assertEquals(StatusSellType.EMPROGRESSO,sell.getStatus());                
        
    }    
    
    /**
     * Test of getSellByDate method, of class SellSession.
     */    
    @Test
    public void testGetSellByDate() throws Exception {
        System.out.println("getSellByDate");
        SellSession instance = getSellSession();
        Date start = new Date();
        Date end = new Date();
        List result = instance.getSellByDate(start, end);
        assertNotNull(result);
        assertTrue(result.size() == 1);
    }
    
    /**
     * Test of getSellsByUser method, of class SellSession.
     */
    @Test
    public void testGetSellsByUser() throws Exception {
        System.out.println("getSellsByUser");
        SellSession instance = getSellSession();
        User user = instance.getSell(idOfSell).getUserOf();
        List result = instance.getSellsByUser(user);
        assertNotNull(result);
        assertTrue(result.size() >= 1  );
    }    
    
    /**
     * Test of getSellByStatus method, of class SellSession.
     */
    @Test
    public void testGetSellByStatus() throws Exception {
        System.out.println("getSellByStatus");
        SellSession instance = getSellSession();
        List result = instance.getSellByStatus(StatusSellType.APROVADO);
        assertTrue(result.isEmpty());
        result = instance.getSellByStatus(StatusSellType.EMPROGRESSO);
        assertTrue(result.size() >= 1);
        
    }
    /**
     * Test of getLastSelledProducts method, of class SellSession.
     */
    @Test
    public void testGetLastSelledProducts() throws Exception {
        System.out.println("getLastSelledProducts");
        SellSession instance = getSellSession();
        List result = instance.getLastSelledProducts();
        Sell sell = instance.getSell(idOfSell);
        assertEquals(result.get(0), sell.getSellItens().get(0).getProduct());
    }
    
    
    /**
     * Test of closeSell method, of class SellSession.
     */
    @Test
    public void testCloseSell() throws Exception {
        System.out.println("closeSell");
        SellSession instance = getSellSession();
        Sell sell = instance.getSell(idOfSell);
        Sell result = instance.closeSell(sell);
        assertTrue(result.isClosed());
        assertEquals(result.getStatus(), StatusSellType.FINALIZADO);
    }
   
    /**
     * Test of removeSell method, of class SellSession.
     */
    @Test
    public void testRemoveSell() throws Exception {
        System.out.println("removeSell");
        SellSession instance = getSellSession();
        Sell sell = instance.getSell(idOfSell);
        instance.removeSell(sell);
        sell = instance.getSell(idOfSell);
        assertNull(sell);
        
    }
}
