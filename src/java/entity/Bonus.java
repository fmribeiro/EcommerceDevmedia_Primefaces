/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrador
 */
@Entity
@Table
public class Bonus implements Serializable{
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    private Integer id;
    
    @Column(name="cupom")
    @NotNull
    private String cupom;
    
    @Column(name="percent")
    @Basic(optional=true)
    private Integer percent;
    
    @Column(name="valueOf")
    @Basic(optional=true)
    private BigDecimal valueOf;
       
    @OneToOne(mappedBy = "bonus")
    private SellItem sellItem;    
    
    @OneToOne
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public SellItem getSellItem() {
        return sellItem;
    }

    public void setSellItem(SellItem sellItem) {
        this.sellItem = sellItem;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public BigDecimal getValueOf() {
        return valueOf;
    }

    public void setValueOf(BigDecimal valueOf) {
        this.valueOf = valueOf;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.cupom != null ? this.cupom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bonus other = (Bonus) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.cupom == null) ? (other.cupom != null) : !this.cupom.equals(other.cupom)) {
            return false;
        }
        return true;
    }
    
    
}
