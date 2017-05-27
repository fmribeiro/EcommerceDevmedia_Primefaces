/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entity.Product;
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
@FacesConverter(value="productConverter")
public class ProductConverter extends ManualCDILookup implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.equals("*All Products*")){
            ProductSession bean = getFacadeWithJNDI(ProductSession.class);
            Product product = bean.getProductsByName(value).get(0);
            return product;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && !(value instanceof String)){
            Product product = (Product)value;
            return product.getName();
        }
        return null;
    }
    
}
