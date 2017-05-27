package entity;

import entity.CallOnAvaliable;
import entity.Category;
import entity.SellItem;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, String> spec;
    public static volatile ListAttribute<Product, SellItem> sellItens;
    public static volatile SingularAttribute<Product, Category> category;
    public static volatile SingularAttribute<Product, Integer> stock;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile ListAttribute<Product, CallOnAvaliable> callers;
    public static volatile SingularAttribute<Product, String> photURL;
    public static volatile SingularAttribute<Product, BigDecimal> cost;

}