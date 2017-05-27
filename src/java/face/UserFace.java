/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Address;
import entity.CallOnAvaliable;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import session.ProductSession;
import session.UserSession;
import types.CountryType;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class UserFace extends BaseCDIBean<User> {

    private static final long serialVersionUID = 1l;
    @Inject
    private UserSession bean;
    @Inject
    ProductSession productBean;
    @Inject
    ProductFace productFace;
    private CallOnAvaliable selectedCoa;
    private List<User> last = null;
    private List<User> mostActive = null;
    private User loggedUser = null;
    //private UIInput userUI;
    private UIComponent userUI;
    private UIInput passwordUI;
    private String redirectURLOnLogin;
    private Address selectedAddress;
    private User userLogin;

    public String getRedirectURLOnLogin() {
        return redirectURLOnLogin;
    }

    public void setRedirectURLOnLogin(String redirectURLOnLogin) {
        this.redirectURLOnLogin = redirectURLOnLogin;
    }

    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //String userName = userUI.getLocalValue().toString();        
        String userName = userLogin.getUsername();
        String password = userLogin.getPassword();
        User user = bean.isUserOk(userName, password);
        if (user == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, "The user is not valid", "The username or password is invalid"));
        }
    }

    public List<Address> getAddressesFromCurrentUser() {
        return bean.getAddressssOfUser(getLoggedUser());
    }

    public String doRemoveAddress() {
        bean.removeAddress(getSelectedAddress().getId());
        return doSeeTheAddress();
    }

    public String doEditAddress() {
        return "/editAddress.faces?faces-redirect=true";
    }

    public String doFinishEditAddress() {
        bean.setAddress(getSelectedAddress());
        return doSeeTheAddress();
    }

    public String doSeeTheAddress() {
        return "/addressList.faces?faces-redirect=true";
    }

    public String doLogout() {
        productFace.getBasketOfProducts().clear();
        setLoggedUser(null);
        return doCancel();
    }

    public String doFinishLoginSystem() throws IOException {
        loggedUser = bean.isUserOk(getSelectedBean().getUsername(), getSelectedBean().getPassword());
        String url = getRedirectURLOnLogin();
        if (url != null) {
            setRedirectURLOnLogin(null);
            return url;
        } else {
            if (loggedUser == null) {
                FacesContext.getCurrentInstance().addMessage("errorlogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha estão incorretos!", "Usuário ou senha estão incorretos!"));
            } else {
               url = "/index.faces?faces-redirect=true";
            }
            return url;
        }
    }

    public boolean isUserLogged() {
        return loggedUser != null;
    }

    public CountryType[] getCountryTypes() {
        return CountryType.values();
    }

    public UserFace() {
        setSelectedBean(new User());
    }

    public int getProductOnCallAvailableList() {
        return bean.getNumberOfProductsOnCallOnAvailable(getLoggedUser());
    }

    public String doSeeTheCallOnAvailableList() {
        return "/callList.faces?faces-redirect=true";
    }

    public String doListLastUsers() {
        last = bean.getLastUsers();
        return "/admin/users/last.faces?faces-redirect=true";
    }

    public String doListMostActiveUsers() {
        mostActive = bean.getMostActiveUsers();
        return "/admin/users/mostActive.faces?faces-redirect=true";
    }

    public String doAddUser() {
        bean.saveUser(getSelectedBean());
        return "/usercreated.faces?faces-redirect=true";

    }

    public String doCancel() {
        return "index.faces?faces-redirect=true";
    }

    public String createNewLogin() {
        setSelectedBean(new User());
        return "/createLogin.faces?faces-redirect=true";
    }

    public String doLoginSystem() {
        setSelectedBean(new User());
        return "/login.faces?faces-redirect=true";
    }

    public List<CallOnAvaliable> getCallOnAvailableOfCurrentUser() {
        return bean.getCallAvailableToUser(getLoggedUser());
    }

    public String doRemoveSelectCoa() {
        productBean.removeNotifiableUserOnAvaliable(getSelectedCoa().getId());
        return doSeeTheCallOnAvailableList();
    }

    public List<User> getLast() {
        return last;
    }

    public List<User> getMostActive() {
        return mostActive;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public UIComponent getUserUI() {
        return userUI;
    }

    public void setUserUI(UIComponent userUI) {
        this.userUI = userUI;
    }

    public UIInput getPasswordUI() {
        return passwordUI;
    }

    public void setPasswordUI(UIInput passwordUI) {
        this.passwordUI = passwordUI;
    }

    public CallOnAvaliable getSelectedCoa() {
        return selectedCoa;
    }

    public void setSelectedCoa(CallOnAvaliable selectedCoa) {
        this.selectedCoa = selectedCoa;
    }

    public Address getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }
}
