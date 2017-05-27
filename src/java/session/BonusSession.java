/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import ejb.BasicSessionBean;
import entity.Bonus;
import entity.Product;
import entity.SellItem;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
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
public class BonusSession extends BasicSessionBean {

    public Bonus createBonus(int percent, BigDecimal value, Product product) {
        Bonus bonus = new Bonus();
        bonus.setPercent(percent);
        bonus.setValueOf(value);
        bonus.setProduct(product);
        bonus.setCupom(generateCupomStr());
        try {
            getEm().persist(bonus);
        } catch (Exception e) {
            System.out.println("The Bonus is equal other Bonus,trying again.");
            return createBonus(percent, value, product);
        }
        return bonus;
    }

    public boolean isBonusUsed(Bonus bonus) {
        List<SellItem> list = getList(SellItem.class, "select si from SellItem si where si.bonus = ?1", bonus);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isBonusUsed(String bonusCupom) {
        List<SellItem> list = getList(SellItem.class, "select si from SellItem si where si.bonus.cupom = ?1", bonusCupom);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }    

    public void removeBonus(int idBonus) {
        Bonus bonus = getEm().find(Bonus.class, idBonus);
        getEm().remove(bonus);
    }

    public Bonus setBonus(Bonus bonus) {
        return getEm().merge(bonus);
    }

    public List<Bonus> getLastBonus(int lastLimit) {
        return getLimitedList(Bonus.class, "select bn from Bonus bn order by bn.id asc ", lastLimit);
    }

    private String generateMd5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    private String generateCupomStr() {
        Random random = new Random();
        return generateMd5(String.valueOf(random.nextDouble() * random.nextInt() - random.nextFloat()));
    }

    public Bonus getBonusByCupom(String cupom) {
        try{            
            Bonus toReturn = getPojo(Bonus.class,"select bn from Bonus bn where bn.cupom = ?1",cupom);        
            System.out.println("O valor do cupom é:"+toReturn.getCupom()+" O valor do id é:"+toReturn.getId());
            return toReturn;
        }catch(Exception ex){
            return null;
        }        
    }
    
}
