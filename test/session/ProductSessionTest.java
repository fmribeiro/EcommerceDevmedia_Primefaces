/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CallOnAvaliable;
import entity.Category;
import entity.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrador
 */
public class ProductSessionTest {

    public static Integer idOfProduct1;
    public static Integer idOfProduct2;
    public static Integer idOfProduct3;
    public static Integer idOfCategory1;
    public static Integer idOfCategory2;
    public static EJBContainer container;

    public ProductSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveCategory method, of class ProductSession.
     */
    
    public void testSaveCategory() throws Exception {
        System.out.println("saveCategory");
        Category category1 = new Category();
        category1.setActive(true);
        category1.setName("cat 1");

        Category category2 = new Category();
        category2.setActive(false);
        category2.setName("cat 2");

        ProductSession instance = getProductSession();
        idOfCategory1 = instance.saveCategory(category1).getId();
        idOfCategory2 = instance.saveCategory(category2).getId();

        category1 = null;
        category2 = null;

        category1 = instance.getCategoryById(idOfCategory1);
        category2 = instance.getCategoryById(idOfCategory2);

        assertNotNull(category1);
        assertNotNull(category2);
        assertEquals(category1.getName(), "cat 1");
        assertEquals(category2.getName(), "cat 2");
    }

    /**
     * Test of setCategory method, of class ProductSession.
     */
    
    public void testSetCategory() throws Exception {
        System.out.println("setCategory");
        ProductSession instance = getProductSession();

        Category category1 = instance.getCategoryById(idOfCategory1);
        Category category2 = instance.getCategoryById(idOfCategory2);

        category1.setName("The Changed Category1");
        category2.setName("The Changed Category2");
        instance.setCategory(category1);
        instance.setCategory(category2);

        category1 = null;
        category2 = null;
        category1 = instance.getCategoryById(idOfCategory1);
        category2 = instance.getCategoryById(idOfCategory2);

        assertNotNull(category1);
        assertNotNull(category2);
        assertEquals(category1.getName(), "The Changed Category1");
        assertEquals(category2.getName(), "The Changed Category2");
    }

    /**
     * Test of saveProduct method, of class ProductSession.
     */
    
    public void testSaveProduct() throws Exception {
        System.out.println("saveProduct");
        ProductSession instance = getProductSession();
        Product product1 = new Product();
        product1.setCategory(instance.getCategoryById(idOfCategory1));
        product1.setCost(new BigDecimal("10.2"));
        product1.setName("product1");
        product1.setStock(200);
        product1.setSpec("spec 1");

        Product product2 = new Product();
        product2.setCategory(instance.getCategoryById(idOfCategory2));
        product2.setCost(new BigDecimal("300.34"));
        product2.setName("product2");
        product2.setStock(1);
        product2.setSpec("spec 2");

        Product product3 = new Product();
        product3.setCategory(instance.getCategoryById(idOfCategory1));
        product3.setCost(new BigDecimal("450.34"));
        product3.setName("product3");
        product3.setStock(1);
        product3.setSpec("spec 3");

        idOfProduct1 = instance.saveProduct(product1).getId();
        idOfProduct2 = instance.saveProduct(product2).getId();
        idOfProduct3 = instance.saveProduct(product3).getId();

        assertNotNull(idOfProduct1);
        assertNotNull(idOfProduct2);
        assertNotNull(idOfProduct3);

        assertTrue(idOfProduct1 >= 1);
        assertTrue(idOfProduct2 >= 1);
        assertTrue(idOfProduct3 >= 1);
    }

    /**
     * Test of setProduct method, of class ProductSession.
     */
   
    public void testSetProduct() throws Exception {
        System.out.println("setProduct");
        ProductSession instance = getProductSession();
        Product product1 = instance.getProductById(idOfProduct1);
        Product product2 = instance.getProductById(idOfProduct2);
        Product product3 = instance.getProductById(idOfProduct3);

        product1.setName("product1 changed");
        product2.setName("product2 changed");
        product3.setName("product3 changed");

        instance.setProduct(product1);
        instance.setProduct(product2);
        instance.setProduct(product3);

        product1 = instance.getProductById(idOfProduct1);
        product2 = instance.getProductById(idOfProduct2);
        product3 = instance.getProductById(idOfProduct3);

        assertNotNull(product1);
        assertNotNull(product2);
        assertNotNull(product3);

        assertEquals(product1.getName(), "product1 changed");
        assertEquals(product2.getName(), "product2 changed");
        assertEquals(product3.getName(), "product3 changed");
    }

    /**
     * Test of getAllProducts method, of class ProductSession.
     */
    
    public void testGetAllProducts() throws Exception {
        System.out.println("getAllProducts");
        ProductSession instance = getProductSession();
        List result = instance.getAllProducts();
        assertTrue(result.size() == 3);
    }

    /**
     * Test of getAllProductsByName method, of class ProductSession.
     */
    
    public void testGetAllProductsByName() throws Exception {
        System.out.println("getAllProductsByName");
        ProductSession instance = getProductSession();
        String nameOfProduct = "product";
        List result1 = instance.getAllProductsByNameOrSpec(nameOfProduct);
        assertTrue(result1.size() == 3);

        List result2 = instance.getAllProductsByNameOrSpec(nameOfProduct + " 1");
        assertTrue(result2.size() == 1);

        List result3 = instance.getAllProductsByNameOrSpec("produto não cadastrado");
        assertNotNull(result3);
        assertTrue(result3.isEmpty());
    }
    
    @Test
     public void testGetProductsByCategory() throws Exception {
        System.out.println("getAllProductsByCategory");
        ProductSession instance = getProductSession();
        String nameOfCategory = "audio";
       // List result1 = instance.getProductsByCategory(nameOfCategory);
        /*
        assertFalse(result1.isEmpty());
        assertTrue(result1.size() == 3);              
        assertNotNull(result1.get(0));     */   
    }
    
    @Test
     public void testGetProductsWithHighStockDesc() throws Exception {
        System.out.println("getAllProductsByCategory");
        ProductSession instance = getProductSession();        
        List result1 = instance.getProductsWithHighStockDesc();
        
        assertFalse(result1.isEmpty());
        assertTrue(result1.size() > 0);              
        assertNotNull(result1.get(0));        
    }
    

    /**
     * Test of getProductsWithLowStock method, of class ProductSession.
     */
    
    public void testGetProductsWithLowStock() throws Exception {
        System.out.println("getProductsWithLowStock");
        ProductSession instance = getProductSession();
        List result = instance.getProductsWithLowStock();
        assertTrue(result.size() == 1);
    }

    /**
     * Test of isProductWithHighStock method, of class ProductSession.
     */
    
    public void testIsProductWithHighStock() throws Exception {
        System.out.println("isProductWithHighStock");
        ProductSession instance = getProductSession();
        boolean result1 = instance.isProductWithHighStock(idOfProduct1);
        boolean result2 = instance.isProductWithHighStock(idOfProduct2);
        boolean result3 = instance.isProductWithHighStock(idOfProduct3);
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    /**
     * Test of getProductById method, of class ProductSession.
     */
    
    public void testGeProductById() throws Exception {
        System.out.println("geProductById");
        ProductSession instance = getProductSession();
        Product result = instance.getProductById(idOfProduct3);
        assertEquals(result.getName(), "product3 changed");
    }

    /**
     * Test of getAllCategories method, of class ProductSession.
     */
    
    public void testGetAllCategories() throws Exception {
        System.out.println("getAllCategories");
        ProductSession instance = getProductSession();
        List result = instance.getAllCategories();
        assertTrue(result.size() >= 2);
    }

    /**
     * Test of getActiveCategories method, of class ProductSession.
     */
    
    public void testGetActiveCategories() throws Exception {
        System.out.println("getActiveCategories");
        ProductSession instance = getProductSession();
        List result = instance.getActiveCategories();
        assertTrue(result.size() == 1);
    }

    /**
     * Test of getCategoryById method, of class ProductSession.
     */
    
    public void testGetCategoryById() throws Exception {
        System.out.println("getCategoryById");
        ProductSession instance = getProductSession();
        Category result = instance.getCategoryById(idOfCategory2);
        assertNotNull(result);
        assertEquals(result.getName(), "The Changed Category2");

    }

    /**
     * Test of addNotifiableUserOnAvaliable method, of class ProductSession.
     */
    
    public void testAddNotifiableUserOnAvaliable() throws Exception {
        System.out.println("addNotifiableUserOnAvaliable");
        ProductSession instance = getProductSession();
        String email = "fmribeiro21@gmail.com";

        Category category1 = new Category();
        category1.setActive(true);
        category1.setName("cat 1");
        int idCategory = instance.saveCategory(category1).getId();

        //Product product = instance.getProductById(idOfProduct1);  
        Product product1 = new Product();
        product1.setCategory(instance.getCategoryById(idCategory));
        product1.setCost(new BigDecimal("10.2"));
        product1.setName("product1");
        product1.setStock(200);
        product1.setSpec("spec 1");
        instance.saveProduct(product1);

        CallOnAvaliable coa = new CallOnAvaliable();
        coa.setEmail(email);
        coa.setProduct(product1);
        CallOnAvaliable result = instance.addNotifiableUserOnAvaliable(email, product1);
        assertNotNull(result);
        assertTrue(result.getId() >= 1);
    }

    /**
     * Test of callTheUsersOnProductAvaliable method, of class ProductSession.
     */
    
    public void testCallTheUsersOnProductAvaliable() throws Exception {
        System.out.println("callTheUsersOnProductAvaliable");
        ProductSession instance = getProductSession();
        int called = instance.getNumberOfCalldUsersOnProductAvaliable();
        assertTrue(called == 1);
        called = instance.getNumberOfCalldUsersOnProductAvaliable();
        assertTrue(called == 0);
    }

    /**
     * Test of removeProduct method, of class ProductSession.
     */
    
    public void testRemoveProduct() throws Exception {
        System.out.println("removeProduct");
        ProductSession instance = getProductSession();
        Product product1 = instance.getProductById(idOfProduct1);
        Product product2 = instance.getProductById(idOfProduct2);
        Product product3 = instance.getProductById(idOfProduct3);
        instance.removeProduct(product1);
        instance.removeProduct(product2);
        instance.removeProduct(product3);
        assertNull(instance.getProductById(idOfProduct1));
        assertNull(instance.getProductById(idOfProduct2));
        assertNull(instance.getProductById(idOfProduct3));
    }

    /**
     * Test of removeCategory method, of class ProductSession.
     */
   
    public void testRemoveCategory() throws Exception {
        System.out.println("removeCategory");
        ProductSession instance = getProductSession();
        Category category1 = instance.getCategoryById(idOfCategory1);
        Category category2 = instance.getCategoryById(idOfCategory2);
        instance.removeCategory(category1);
        instance.removeCategory(category2);
        assertNull(instance.getCategoryById(idOfCategory1));
        assertNull(instance.getCategoryById(idOfCategory2));

    }

    private ProductSession getProductSession() throws NamingException {
        return (ProductSession) container.getContext().lookup("java:global/classes/ProductSession");
    }
}
