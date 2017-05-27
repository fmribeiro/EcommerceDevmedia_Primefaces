package entity;

import entity.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(CallOnAvaliable.class)
public class CallOnAvaliable_ { 

    public static volatile SingularAttribute<CallOnAvaliable, Integer> id;
    public static volatile SingularAttribute<CallOnAvaliable, Product> product;
    public static volatile SingularAttribute<CallOnAvaliable, String> email;
    public static volatile SingularAttribute<CallOnAvaliable, Boolean> called;

}