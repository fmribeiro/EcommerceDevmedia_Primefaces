/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    private Integer id;
    
    @NotNull(message="The username cannot be null")
    @NotEmpty(message="The username cannot be empty")
    @Column(length=255,nullable=false)    
    private String username;
    
    @NotNull(message="The password cannot be null")
    @NotEmpty(message="The password cannot be empty")
    @Min(value=5,message="The password min of characters is 5")
    @Column(length=32,nullable=false)
    private String password;
    
    @NotNull(message="The name cannot be null")
    @NotEmpty(message="The name cannot be empty")
    @Column(length=255,nullable=false)
    private String name;
    
    @NotNull(message="The email cannot be null")
    @NotEmpty(message="The email cannot be empty")
    @Email
    @Column(length=255,nullable=false)
    private String email;     
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="userOf",orphanRemoval=true)
    private List<Address> addresses = new LinkedList<Address>();
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="userOf",orphanRemoval=true)
    private List<CreditCard> creditCards = new LinkedList<CreditCard>();
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="userOf",orphanRemoval=true)
    private List<Sell> sells = new LinkedList<Sell>();

    public void addAddress(Address address ){
        address.setUserOf(this);
        getAddresses().add(address);
    }
    
    public void addCreditCard(CreditCard creditCard){
        creditCard.setUserOf(this);
        getCreditCards().add(creditCard);
    }
    
    
    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }
                
    public List<Address> getAddresses() {
        return addresses;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ id=" + id + " ]";
    }
    
}
