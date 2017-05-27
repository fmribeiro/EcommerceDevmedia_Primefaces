package entity;

import entity.Address;
import entity.CreditCard;
import entity.Sell;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Sell> sells;
    public static volatile ListAttribute<User, Address> addresses;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, CreditCard> creditCards;

}