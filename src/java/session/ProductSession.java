/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.BasicSessionBean;
import entity.CallOnAvaliable;
import entity.Category;
import entity.Product;
import java.util.List;
import javax.ejb.Schedule;
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
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductSession extends BasicSessionBean{

    public Category saveCategory(Category category) {
        getEm().persist(category);
        return category;
    }

    public Category setCategory(Category cat) {
        getEm().merge(cat);
        return cat;
    }

    public Product saveProduct(Product product) {
        getEm().persist(product);
        return product;
    }

    public Product setProduct(Product product) {
        getEm().merge(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return getList(Product.class, "select prod from Product prod order by prod.name");
    }

    /**
     * Recupera os produtos que estão com o estoque alto ordenando de forma
     * decrescente
     */
    public List<Product> getProductsWithHighStockDesc() {
        return getLimitedList(Product.class, "select prod from Product prod where prod.category.active =?1 order by prod.stock desc", 20, true);
    }
    
    public List<Product> getProductsByCategory(String categoryName) {
        return getLimitedList(Product.class, "select prod from Product prod where prod.category.name like ?1 order by prod.cost desc", 20, "%" + categoryName + "%");
    }

    public List<Product> getAllProductsByNameOrSpec(String nameOfProduct) {
        return getList(Product.class, "Select prod from Product prod where prod.name like ?1 or prod.spec like ?1", "%" + nameOfProduct + "%");
    }

    public List<Product> getProductsWithLowStock() {
        return getList(Product.class, "select prod from Product prod where prod.stock <= ?1 and prod.category.active = ?2", 5, true);
    }

    /**
     * Recupera os produtos que estão com mais de 20 itens no estoque
     */
    public List<Product> getProductsWithHighStock() {
        return getList(Product.class, "select prod from Product prod where prod.stock >= ?1 and prod.category.active = ?2", 20, true);
    }

    public List<Product> getProductsByName(String name) {
        return getList(Product.class, "select prod from Product prod where prod.category.active = ?1 and prod.name like ?2 order by prod.name", true, name + "%");// "S" categoria está ativa
    }

    public boolean isProductWithHighStock(int idProduct) {
        return getProductById(idProduct).getStock() > 20;
    }

    public Product getProductById(int idProduct) {
        return getPojo(Product.class, idProduct);
    }

    public List<Category> getAllCategories() {
        return getList(Category.class, "select cat from Category cat order by cat.name");
    }

    public List<Category> getActiveCategories() {
        return getList(Category.class, "select cat from Category cat where cat.active = ?1 order by cat.name", true);// "S" categoria está ativa
    }

    public List<Category> getCategoriesByName(String name) {
        return getList(Category.class, "select cat from Category cat where cat.active = ?1 and cat.name like ?2 order by cat.name", true, name + "%");// "S" categoria está ativa
    }

    public Category getCategoryByName(String name) {
        return getPojo(Category.class, "select cat from Category cat where cat.active = ?1 and cat.name = ?2 order by cat.name", true, name);// "S" categoria está ativa
    }

    public Category getCategoryById(int idCategory) {
        return getPojo(Category.class, idCategory);
    }

    public CallOnAvaliable addNotifiableUserOnAvaliable(String email, Product product) {
        CallOnAvaliable toPersist = new CallOnAvaliable();
        toPersist.setEmail(email);
        toPersist.setProduct(product);
        getEm().persist(toPersist);
        return toPersist;
    }

    @Schedule(minute = "30", hour = "*")
    public void callTheUsersOnProductAvaliable() {
        List<CallOnAvaliable> toCall = getList(CallOnAvaliable.class, "select coa from CallOnAvaliable coa where"
                + " coa.product.stock > ?1 and coa.called = ?2", 0, false);
        System.out.println("Start Callers check");
        for (CallOnAvaliable callOnAvaliable : toCall) {
            System.out.println("You need to call " + callOnAvaliable.getEmail());
            callOnAvaliable.setCalled(true);
        }
        System.out.println("End Callers check");
    }

    public int getNumberOfCalldUsersOnProductAvaliable() {
        return getList(CallOnAvaliable.class, "select coa from CallOnAvaliable coa where"
                + " coa.product.stock > ?1 and coa.called = ?2", 0, false).size();
    }

    public void removeCategory(Category cat) {
        cat = getEm().merge(cat);
        getEm().remove(cat);
    }

    public void removeProduct(Product product) {
        product = getEm().merge(product);
        getEm().remove(product);
    }

    public void removeNotifiableUserOnAvaliable(int idCoa) {
        CallOnAvaliable coa = getEm().find(CallOnAvaliable.class, idCoa);
        getEm().remove(coa);
    }

}
