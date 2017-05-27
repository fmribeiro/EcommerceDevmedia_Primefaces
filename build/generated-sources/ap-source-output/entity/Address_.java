package entity;

import entity.Sell;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import types.CountryType;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T20:17:20")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> postalCode;
    public static volatile SingularAttribute<Address, User> userOf;
    public static volatile SingularAttribute<Address, String> nickname;
    public static volatile SingularAttribute<Address, String> address;
    public static volatile SingularAttribute<Address, Integer> numberOf;
    public static volatile SingularAttribute<Address, String> stateOfAddress;
    public static volatile ListAttribute<Address, Sell> sells;
    public static volatile SingularAttribute<Address, CountryType> country;

}