package entity;

import entity.Bonus;
import entity.Product;
import entity.Sell;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(SellItem.class)
public class SellItem_ { 

    public static volatile SingularAttribute<SellItem, Integer> id;
    public static volatile SingularAttribute<SellItem, Product> product;
    public static volatile SingularAttribute<SellItem, Sell> sell;
    public static volatile SingularAttribute<SellItem, Integer> qnt;
    public static volatile SingularAttribute<SellItem, Bonus> bonus;

}