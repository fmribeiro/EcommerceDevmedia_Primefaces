/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Product;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.ProductSession;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class SearchFace extends BaseCDIBean<Product>{
    
    private static final long serialVersionUID = 1l;
    
    @Inject
    private ProductSession bean;
    @Inject
    private UserFace user;
    private List<Product> list;
    private Product selectedProductSearch;
    private String searchTerm;
    
    public String doSearch(){
        list = bean.getAllProductsByNameOrSpec(searchTerm);
        return "/searchResult.faces?faces-redirect=true";
    }
    
    public List<Product> autoComplete(String busca) {
        list = bean.getAllProductsByNameOrSpec(busca);
        searchTerm = busca;
        /*
        List<Product> toReturn = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.getName().toUpperCase().contains(busca.toUpperCase())) {
                toReturn.add(product);
            }
        }*/
        return list;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchFace() {
        setSelectedBean(new Product());
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public Product getSelectedProductSearch() {
        return selectedProductSearch;
    }

    public void setSelectedProductSearch(Product selectedProductSearch) {
        this.selectedProductSearch = selectedProductSearch;
    }
    
}
