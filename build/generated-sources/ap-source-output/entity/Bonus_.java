package entity;

import entity.Product;
import entity.SellItem;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(Bonus.class)
public class Bonus_ { 

    public static volatile SingularAttribute<Bonus, Integer> id;
    public static volatile SingularAttribute<Bonus, Product> product;
    public static volatile SingularAttribute<Bonus, Integer> percent;
    public static volatile SingularAttribute<Bonus, SellItem> sellItem;
    public static volatile SingularAttribute<Bonus, String> cupom;
    public static volatile SingularAttribute<Bonus, BigDecimal> valueOf;

}