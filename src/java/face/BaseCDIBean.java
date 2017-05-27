/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
public abstract class BaseCDIBean<T> implements Serializable {

    private static final long serialVersionUID = 1l;
    private T selectedBean;
    private List<T> selectedBeans;
    @PersistenceContext
    protected EntityManager em;

    public T getSelectedBean() {
        return selectedBean;
    }

    public void setSelectedBean(T selectedBean) {
        this.selectedBean = selectedBean;
    }

    public List<T> getSelectedBeans() {
        return selectedBeans;
    }

    public void setSelectedBeans(List<T> selectedBeans) {
        this.selectedBeans = selectedBeans;
    }
}
