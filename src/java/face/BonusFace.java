/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Bonus;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.BonusSession;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class BonusFace extends BaseCDIBean<Bonus> {
    private static final long serialVersionUID = 1L;
    
    private Bonus selectedBonus;   
    @Inject
    private BonusSession bonusSessionBean;
    
    public String doFinishCreateBonus(){
        selectedBonus = bonusSessionBean.createBonus(selectedBonus.getPercent(), selectedBonus.getValueOf(), selectedBonus.getProduct());
        return "/admin/bonus/created.faces";
    }
    
    public String doStartCreateBonus(){
        selectedBonus = new Bonus();
        return "/admin/bonus/create.faces";
    }
    
    public String doListLastTenBonus(){        
        return "/admin/bonus/list.faces";
    }
    
    public String doStartEditBonus(){        
        return"/admin/bonus/edit.faces";
    }
    
    public String doFinishEditBonus(){
        bonusSessionBean.setBonus(selectedBonus);
        return doListLastTenBonus();
    }
    
    public String doRemoveBonus(){
        bonusSessionBean.removeBonus(selectedBonus.getId());
        return doListLastTenBonus();
    }
    
    public boolean isUsedBonus(Bonus bonus){
        return bonusSessionBean.isBonusUsed(bonus);
    }
    
    public List<Bonus> getLastTenBonus(){
        return bonusSessionBean.getLastBonus(10);
    }

    public Bonus getSelectedBonus() {
        return selectedBonus;
    }

    public void setSelectedBonus(Bonus selectedBonus) {
        this.selectedBonus = selectedBonus;
    }
    
    
    
}
