/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.sun.xml.bind.CycleRecoverable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Administrador
 */
@Entity
@Table
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category implements Serializable,CycleRecoverable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional=false)
    @XmlAttribute    
    private Integer id;
      
    @NotNull(message="The name of category cannot be null")
    @NotEmpty(message="The name of category cannot be empty")
    @Length(min=3,message="The min letter to the category is 3")
    @Column(nullable=false,length=255,unique=true,name="name")
    @XmlElement
    private String name;
        
    @XmlElementWrapper
    @OneToMany(cascade= CascadeType.ALL,mappedBy="category",orphanRemoval=true)        
    private List<Product> products = new LinkedList<Product>();    
    
    @Basic
    @Column(name="active")
    @XmlElement
    private Boolean active = true;//devido a problemas do oracle em aceitar boolenos, a verificação se a categoria será feita com uma String que retorna "S" ou "N"            
    
    public void addProduct(Product prod){
        prod.setCategory(this);
        getProducts().add(prod);
    }
    
    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }                    

    public void setId(Integer id) {
        this.id = id;
    }
    
      public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
       
    /*Trecho de codigo para banco de dados oracle
    public Boolean getActive() {
        if(active == null){
            return null;
        }else{
            return active == 'Y' ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public void setActive(Boolean active) {
        if(active == null){
            this.active = null;
        }else{
            this.active = active == true ? 'Y' : 'N';
        }
    }
    */
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Category[ id=" + id + " ]";
    }

    @Override
    public Object onCycleDetected(Context cntxt) {
        Category category = new Category();
        category.setId(id);
        return category;
    }
    
}
