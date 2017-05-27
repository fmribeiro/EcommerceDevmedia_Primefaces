/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Category;
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
public class CategoryFace extends BaseCDIBean<Category> {
       
    private static final long serialVersionUID = 1l;
    @Inject
    private ProductSession bean;
    private List<Category> list;
       
     public CategoryFace() {
        setSelectedBean(new Category());
    }

    
    public String doEditCategory(){        
        return "/admin/category/edit.faces";
    }
    
    public String doFinishEditCategory(){
        bean.setCategory(getSelectedBean());
        return doListCategories();
    }
    
    public String doRemoveCategory(){
        bean.removeCategory(getSelectedBean());
        return doListCategories();
    }
    
    public String doCreateCategory(){
        setSelectedBean(new Category());
        return "/admin/category/add.faces";
    }
    
    public String doFinishCreateCategory(){
        bean.saveCategory(getSelectedBean());
        return doListCategories();
    }

    public String doListCategories() {
        list = bean.getAllCategories();
        return "/admin/category/list.faces";
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
}
