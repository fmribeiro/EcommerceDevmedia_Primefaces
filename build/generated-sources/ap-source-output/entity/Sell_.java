package entity;

import entity.Address;
import entity.CreditCard;
import entity.SellItem;
import entity.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import types.StatusSellType;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(Sell.class)
public class Sell_ { 

    public static volatile SingularAttribute<Sell, Integer> id;
    public static volatile SingularAttribute<Sell, BigDecimal> total;
    public static volatile ListAttribute<Sell, SellItem> sellItens;
    public static volatile SingularAttribute<Sell, Address> addressToSend;
    public static volatile SingularAttribute<Sell, StatusSellType> status;
    public static volatile SingularAttribute<Sell, User> userOf;
    public static volatile SingularAttribute<Sell, CreditCard> creditcard;
    public static volatile SingularAttribute<Sell, Boolean> closed;
    public static volatile SingularAttribute<Sell, Date> dateOfSell;

}