/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import types.StatusSellType;

/**
 *
 * @author Administrador
 */
@Entity
@Table
public class Sell implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    private Integer id;
           
    @NotNull
    @Enumerated(EnumType.STRING)            
    @Column(nullable=false)
    private StatusSellType status = StatusSellType.INICIO;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dateOfSell;
    
    @NotNull
    @Min(0)
    @Column(nullable=false)
    private BigDecimal total;
    
    @NotNull
    @JoinColumn(referencedColumnName="id")
    @ManyToOne()
    @Valid
    private User userOf;
    
    @NotNull
    @JoinColumn(referencedColumnName="id")
    @ManyToOne(optional=false,cascade= CascadeType.MERGE)
    @Valid
    private Address addressToSend;
        
    @JoinColumn(referencedColumnName="id")
    @ManyToOne(optional=false,cascade= CascadeType.ALL)
    @Valid
    private CreditCard creditcard;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="sell")
    private List<SellItem> sellItens = new LinkedList<SellItem>();
    
    @Column
    private boolean closed = false;
        
    public void addItem(SellItem sellItem){
        sellItem.setSell(this);
        getSellItens().add(sellItem);
        if(total == null){
            total = sellItem.getProduct().getCost();
        }else{
            total.add(sellItem.getProduct().getCost());
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusSellType getStatus() {
        return status;
    }

    public void setStatus(StatusSellType status) {
        this.status = status;
    }

    public Date getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(Date dateOfSell) {
        this.dateOfSell = dateOfSell;
    }
    
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public User getUserOf() {
        return userOf;
    }

    public void setUserOf(User userOf) {
        this.userOf = userOf;
    }

    public Address getAddressToSend() {
        return addressToSend;
    }

    public void setAddressToSend(Address addressToSend) {
        this.addressToSend = addressToSend;
    }

    public CreditCard getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(CreditCard creditcard) {
        this.creditcard = creditcard;
    }
    
    public List<SellItem> getSellItens() {
        return sellItens;
    }

    public void setSellItens(List<SellItem> sellItens) {
        this.sellItens = sellItens;
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
        if (!(object instanceof Sell)) {
            return false;
        }
        Sell other = (Sell) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sell[ id=" + id + " ]";
    }
    
}
