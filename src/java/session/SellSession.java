package session;

import ejb.BasicSessionBean;
import entity.Product;
import entity.Sell;
import entity.SellItem;
import entity.User;
import exceptions.CreditcardInvalidException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import types.StatusSellType;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SellSession extends BasicSessionBean{
    
    
    public List<Sell> getSellByDate(Date start, Date end){
        return getList(Sell.class, "select sel from Sell sel where sel.dateOfSell >= ?1 and sel.dateOfSell <= ?2", start,end);
    }
    
    public List<Sell> getSellsByUser(User user){
        return getList(Sell.class, "select sel from Sell sel where sel.userOf = ?1", user);
    }
    
    public List<Sell> getSellByStatus(StatusSellType status){
        return getList(Sell.class, "select sel from Sell sel where sel.status = ?1", status);
    }
    
    public List<Product> getLastSelledProducts(){
        return getLimitedList(Product.class, "select DISTINCT(si.product) from SellItem si order by si.sell.dateOfSell desc",10);
    }
    
    public List<SellItem> getSellItens(int idOfSell){
        return getList(SellItem.class, "select si from SellItem si where si.sell.id = ?1",idOfSell);
    }
    
    public List<Sell> getLastSells(){
        return getLimitedList(Sell.class, "select si.sell from SellItem si order by si.sell.dateOfSell desc",10);
    }
    
    public Sell getSell(int idSell){
        return getPojo(Sell.class, idSell);
    }
    
    private void confirmSell(Sell sell){
        System.out.println("The products has been selled... "+sell);
    }
    
    public boolean isCreditCardValidForSell(Sell sell){
        setStatusOfSell(sell, StatusSellType.APROVADO);
        System.out.println("This credit card is been validated !"+sell.getCreditcard());
        return true;
    }
    
    public void setStatusOfSell(Sell sell,StatusSellType statusSellType){
        System.out.println("Send email to user");
        sell.setStatus(statusSellType);
        //setSell(sell);
        if(sell.getId() != null){
            getEm().find(Sell.class, sell.getId()).setStatus(statusSellType);
        }
    }
    
    public Sell closeSell(Sell sell){
        sell = getEm().find(Sell.class, sell.getId());
        sell.setClosed(true);
        setSell(sell);
        setStatusOfSell(sell, StatusSellType.FINALIZADO);        
        return sell;
    }
    
    public Sell saveSell(Sell sell) throws CreditcardInvalidException{
        sell.setDateOfSell(new  Date());
        BigDecimal total = BigDecimal.ZERO;
        for (SellItem item : sell.getSellItens()) {
            total = total.add(item.getProduct().getCost());
            
        }
        sell.setTotal(total);
        if(!isCreditCardValidForSell(sell)){
            throw new CreditcardInvalidException("The creditcard is invalid for sell.");
        }
        getEm().persist(sell);
        confirmSell(sell);
        for (SellItem si: sell.getSellItens()) {
            Product product = si.getProduct();
            product.setStock(product.getStock() - si.getQnt());
            getEm().merge(product);
            //si.getProduct().setStock(si.getProduct().getStock()-si.getQnt()); 
        }
        setStatusOfSell(sell, StatusSellType.EMPROGRESSO);                                                
        return sell;
    }
    
    public Sell setSell(Sell sell){
        getEm().merge(sell);
        return sell;
    }
    
    public void removeSell(Sell sell){
        Sell toRemove = getEm().getReference(Sell.class, sell.getId());
        getEm().remove(toRemove);
    }
    
    
    
    
}
