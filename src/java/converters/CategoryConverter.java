/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entity.Category;
import face.ManualCDILookup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.ProductSession;

/**
 *
 * @author Administrador
 */
@FacesConverter(value="categoryConverter")
public class CategoryConverter extends ManualCDILookup implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            ProductSession bean = getFacadeWithJNDI(ProductSession.class);
            Category category = bean.getCategoryByName(value);
            return category;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Category category = (Category)value;
            return category.getName();
        }
        return null;
    }
    
}
