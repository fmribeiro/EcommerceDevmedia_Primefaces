/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.validators.ValidPostalCode;
import java.io.Serializable;
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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import types.CountryType;

/**
 *
 * @author Administrador
 */
@Entity
@Table
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    private Integer id;
    
    @JoinColumn(referencedColumnName="id",name="userOf")
    @ManyToOne()
    @NotNull        
    @Valid
    private User userOf;
    
    @NotNull
    @NotEmpty
    @Column(length=255,nullable=false)
    private String nickname;
    
    @NotNull
    @NotEmpty
    @Column(length=255,nullable=false)
    @XmlElement
    private String address;
    
    @NotNull
    @NotEmpty
    @Column(length=100,nullable=false)
    @XmlElement
    private String stateOfAddress;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @XmlElement
    private CountryType country = CountryType.BRASIL;
    
    @NotNull
    @Min(0)
    @Column(nullable=false)
    @XmlElement
    private int numberOf;
    
    @NotNull
    @NotEmpty
    @Column(nullable=false)
    @ValidPostalCode
    private String postalCode;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="addressToSend")
    private List<Sell> sells = new LinkedList<Sell>();

    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }

    public User getUserOf() {
        return userOf;
    }

    public void setUserOf(User userOf) {
        this.userOf = userOf;
    }
     
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateOfAddress() {
        return stateOfAddress;
    }

    public void setStateOfAddress(String stateOfAddress) {
        this.stateOfAddress = stateOfAddress;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }
   

    

    public CountryType getCountry() {
        return country;
    }

    public void setCountry(CountryType country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Address[ id=" + id + " ]";
    }
    
}
