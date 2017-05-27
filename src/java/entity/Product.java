/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.sun.xml.bind.CycleRecoverable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Administrador
 */
@Entity
@Table
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable,CycleRecoverable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    @XmlElement   
    private Integer id;
    
    @NotNull
    @NotEmpty
    @Column(unique=true,nullable=false)
    @XmlElement   
    private String name;
    
    @NotNull
    @Column(nullable=false)  
    @XmlElement
    private BigDecimal cost;
    
    @NotNull
    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(referencedColumnName="id",name="category")
    @Valid
    @XmlElement
    private Category category;
    
    @Column(nullable=false)
    @NotNull
    @NotEmpty
    @XmlElement
    private String spec;
    
    @NotNull
    @Min(0)
    @Basic
    @Column(nullable=false,name="stock")
    @XmlElement
    private int stock;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="product")
    @XmlTransient
    private List<SellItem> sellItens;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="product",orphanRemoval=true)
    @XmlTransient
    private List<CallOnAvaliable> callers = new LinkedList<CallOnAvaliable>();
    
    @Column(nullable=true)
    @XmlElement
    private String photURL;        
        
    //usado apenas quando o objeto estiver na memória
    @Transient
    private BigDecimal discountValue;
    
    @Transient
    private Bonus usedBonus;

    public Bonus getUsedBonus() {
        return usedBonus;
    }

    public void setUsedBonus(Bonus usedBonus) {
        this.usedBonus = usedBonus;
    }
    
    public BigDecimal getCostWithDiscount(){
        if(getDiscountValue() != null && getCost() != null){
            return getCost().subtract(getDiscountValue());
        }
        return getCost();
    }
    
    public boolean isProductWithDiscount(){
        return (getDiscountValue() != null && !getDiscountValue().equals(BigDecimal.ZERO));
    }
    
    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
           
    public String getPhotURL() {
        if(photURL == null){
            return "./resources/images/photo_not_available.jpg";
        }
        return photURL;
    }
    
    public void setPhotURL(String photURL) {
        this.photURL = photURL;
    }
    
    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }        
    
    public void addCaller(CallOnAvaliable caller){
        caller.setProduct(this);
        getCallers().add(caller);
    }
    
    public boolean isOutOfStock(){
        return getStock() <= 0;
    }
    
    public List<CallOnAvaliable> getCallers() {
        return callers;
    }

    public void setCallers(List<CallOnAvaliable> callers) {
        this.callers = callers;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ id=" + id + " ]";
    }

    @Override
    public Object onCycleDetected(Context cntxt) {
        Product product = new Product(this.id);
        return product;
    }

    
}
