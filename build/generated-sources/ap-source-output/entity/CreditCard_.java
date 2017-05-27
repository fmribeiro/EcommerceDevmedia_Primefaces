package entity;

import entity.Sell;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import types.FlagType;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, Integer> id;
    public static volatile SingularAttribute<CreditCard, FlagType> flag;
    public static volatile SingularAttribute<CreditCard, Integer> times;
    public static volatile SingularAttribute<CreditCard, User> userOf;
    public static volatile SingularAttribute<CreditCard, Date> dateOfValidation;
    public static volatile SingularAttribute<CreditCard, String> nameOfCardOwner;
    public static volatile SingularAttribute<CreditCard, String> number;
    public static volatile ListAttribute<CreditCard, Sell> sells;

}