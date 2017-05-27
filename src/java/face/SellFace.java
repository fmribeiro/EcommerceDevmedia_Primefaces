/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Sell;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import session.SellSession;
import types.StatusSellType;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class SellFace extends BaseCDIBean<Sell> {
    
    private static final long serialVersionUID = 1l;
    @Inject
    private SellSession bean;
    private List<Sell> last = null;
    private List<Sell> list = null;
    private StatusSellType selectStatus = StatusSellType.EMPROGRESSO;

    public SellFace() {
        setSelectedBean(new Sell());
    }
    
    public String doListLastSell(){
        last = bean.getLastSells();
        return "/admin/sells/last.faces";
    }

    public List<Sell> getList() {
        return list;
    }
    
    public void statusChanged(ValueChangeEvent event){
        setSelectStatus((StatusSellType) event.getNewValue());
        doListSellsByStatus();
    }
    
    public void doAjaxListener(){
        doListSellsByStatus();
    }

    public void setSelectStatus(StatusSellType selectStatus) {
        this.selectStatus = selectStatus;
    }
            
    public String doListSellsByStatus(){
        list = bean.getSellByStatus(selectStatus);
        return "/admin/sells/list.faces";
    }
    
    public String doSetStatusValid(){
        bean.setStatusOfSell(getSelectedBean(), StatusSellType.APROVADO);
        setSelectStatus(StatusSellType.APROVADO);
        return doListSellsByStatus();
    }
    
    public String doSetStatusInProgress(){
        bean.setStatusOfSell(getSelectedBean(), StatusSellType.EMPROGRESSO);
        setSelectStatus(StatusSellType.EMPROGRESSO);
        return doListSellsByStatus();
    }
    
    public String doSetStatusSended(){
        bean.setStatusOfSell(getSelectedBean(), StatusSellType.ENVIADO);
        setSelectStatus(StatusSellType.ENVIADO);
        return doListSellsByStatus();
    }
    
    public String doSetStatusDone(){
        bean.setStatusOfSell(getSelectedBean(), StatusSellType.FINALIZADO);
        setSelectStatus(StatusSellType.FINALIZADO);
        return doListSellsByStatus();
    }

    public StatusSellType getSelectStatus() {
        return selectStatus;
    }
    
    public List<Sell> getLast() {
        return last;
    }
    
    
}
