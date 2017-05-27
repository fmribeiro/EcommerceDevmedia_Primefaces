package entity;

import entity.validators.ValidCreditCardExpirationDate;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import types.FlagType;

/**
 *
 * @author Administrador
 */
@Entity
@Table
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    private Integer id;
   
    @NotNull(message="The number of Credit Card cannot be null.")
    @NotEmpty
    @Column(nullable=false)
    private String number;
    
    @NotNull(message="The Date of Validation cannot be null.")    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)   
    @ValidCreditCardExpirationDate
    private Date dateOfValidation;
    
    @NotNull(message="The Name of Card Owner cannot be null.")
    @NotEmpty
    @Column(nullable=false)
    private String nameOfCardOwner;
               
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private FlagType flag;

    @Column(nullable=false)
    @Min(1)
    private int times;
    
    @JoinColumn(referencedColumnName="id")
    @ManyToOne(cascade= CascadeType.ALL,optional=false)
    @Valid
    private User userOf;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="creditcard")
    private List<Sell> sells = new LinkedList<Sell>();

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
            
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOfValidation() {
        return dateOfValidation;
    }

    public void setDateOfValidation(Date dateOfValidation) {
        this.dateOfValidation = dateOfValidation;
    }

    public String getNameOfCardOwner() {
        return nameOfCardOwner;
    }

    public void setNameOfCardOwner(String nameOfCardOwner) {
        this.nameOfCardOwner = nameOfCardOwner;
    }

    public User getUserOf() {
        return userOf;
    }

    public void setUserOf(User userOf) {
        this.userOf = userOf;
    }

    public FlagType getFlag() {
        return flag;
    }

    public void setFlag(FlagType flag) {
        this.flag = flag;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
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
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditCard[ id=" + id + " ]";
    }
    
}
