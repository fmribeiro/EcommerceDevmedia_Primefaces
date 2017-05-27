/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entity.Bonus;
import entity.Category;
import face.ManualCDILookup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.BonusSession;

/**
 *
 * @author Administrador
 */
@FacesConverter(value="bonusConverter")
public class BonusConverter extends ManualCDILookup implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            BonusSession bean = getFacadeWithJNDI(BonusSession.class);
            Bonus bonus = bean.getBonusByCupom(value);
            return bonus;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Bonus bonus = (Bonus) value;
            return bonus.getCupom();
        }
        return null;
    }
    
}
