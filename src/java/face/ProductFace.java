/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Address;
import entity.Bonus;
import entity.Category;
import entity.CreditCard;
import entity.Product;
import entity.Sell;
import entity.SellItem;
import exceptions.CreditcardInvalidException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import session.BonusSession;
import session.FreteSessionBean;
import session.ProductSession;
import session.SellSession;
import session.UserSession;
import types.FlagType;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class ProductFace extends BaseCDIBean<Product> {

    private static final long serialVersionUID = 1l;
    @Inject
    private ProductSession bean;
    @Inject
    private UserSession userSessionBean;
    @Inject
    private FreteSessionBean freteBean;
    @Inject
    private UserFace user;
    @Inject
    private SearchFace searchFaceBean;
    @Inject
    private SellSession sellSessionBean;
    @Inject
    private BonusSession bonusSessionBean;
    private List<Sell> sells;
    private List<Product> list;
    private Integer idOfViewProduct;
    private Address selectedAddressToBuy;
    private List<Product> basketOfProducts = new LinkedList<Product>();
    private float totalOfSell = 0;
    private float frete = 0;
    private String cepToCalcFrete;
    private String selectPaymentForm;
    private Sell selectedSell = null;
    private CreditCard creditCardToUse = new CreditCard();
    private Bonus selectedCupom;
    private Bonus bonusCupom = new Bonus();
    private String bonusCupomStr;    
    private String categoryName;
    private List<Product> productsToLayoutCentral;
    private List<Product> productsByCategory;

    public String getBonusCupomStr() {
        return bonusCupomStr;
    }

    public void setBonusCupomStr(String bonusCupomStr) {
        this.bonusCupomStr = bonusCupomStr;
    }

    public ProductFace() {
        setSelectedBean(new Product());        
    }
    
    public String doListProductsByCategory(){               
        productsByCategory = bean.getProductsByCategory(categoryName);
        System.out.println("A categoria selecionada no método doListProductsByCategory foi:"+categoryName);
        return "/dataGridProductsByCategory.faces?faces-redirect=true";
        
    }
   
    public List<Product> getProductsToRightBox() {
        return bean.getProductsWithHighStock();
    }

    public List<Product> getProductsToRigthTopBox() {
        return bean.getProductsWithLowStock();
    }

    public List<Product> getAllProducts() {
        List toReturn = bean.getAllProducts();
        return toReturn;
    }

    public String showProductDetail() throws IOException {
        setSelectedCupom(null);
        setBonusCupom(null);//?faces-redirect=true";        
        return "/productDetail.faces?faces-redirect=true";
    }

    public String buyNow() {
        return "/buy.faces?faces-redirect=true";
    }
    
    public String viewCart(){
        return "/buy_fase1.faces?faces-redirect=true";
    }
    
    public String proceedToBuy() throws IOException {
        addToBasketOfProducts();       
        return "/buy_fase1.faces?faces-redirect=true";
   }   

    public String gotoBuyFase2() throws Exception {
        /*Frete frt = freteBean.getFrete(getSelectedAddressToBuy().getPostalCode());
         setFrete(frt.getSedex());*/
        updateTotal();
        return "/buy_fase2.faces?faces-redirect=true";
    }

    public String gotoBuyFase3() {
        if (getSelectPaymentForm().contains("oleto")) {
            return "/buy_fase4.faces?faces-redirect=true";
        } else {
            return "/buy_fase3.faces?faces-redirect=true";
        }
    }

    public String gotoBuyFase4() {
        return "/buy_fase4.faces?faces-redirect=true";
    }

    public String goToFinishBuy() throws CreditcardInvalidException {
        Sell sell = new Sell();
        sell.setAddressToSend(getSelectedAddressToBuy());
        sell.setDateOfSell(new Date());
        sell.setTotal(BigDecimal.valueOf(getTotalOfSell()));
        sell.setUserOf(user.getLoggedUser());
        for (Product product : basketOfProducts) {
            SellItem sellItem = new SellItem();
            sellItem.setProduct(product);
            sellItem.setBonus(product.getUsedBonus());
            sellItem.setQnt(1);
            sell.addItem(sellItem);
        }
        if (!getSelectPaymentForm().contains("oleto")) {
            sell.setCreditcard(creditCardToUse);
        }
        sellSessionBean.saveSell(sell);
        setSelectedSell(sell);
        //list.clear();
        basketOfProducts.clear();
        setSelectedCupom(null);
        setBonusCupom(new Bonus());
        setSelectedBean(null);
        if (getSelectPaymentForm().contains("oleto")) {
            return "/buy_finished_boleto.faces?faces-redirect=true";
        } else {
            return "/buy_finished_cc.faces?faces-redirect=true";
        }
    }

    public String doUseBonusCupom() {
        System.out.println("O valor do bonus cupom é:" + bonusCupomStr);
        bonusCupom = new Bonus();
        bonusCupom.setCupom(bonusCupomStr);
        if (bonusCupom != null && !bonusSessionBean.isBonusUsed(bonusCupom.getCupom())) {
            setBonusCupom(bonusSessionBean.getBonusByCupom(bonusCupom.getCupom()));
            setSelectedCupom(getBonusCupom());
            updateTotal();
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Cupom not found.", "Cupom not found.");
            FacesContext.getCurrentInstance().addMessage("error message", fm);
        }
        return "/buy_fase1.faces?faces-redirect=true";
    }

    public boolean isSellWithBonusCupom() {
        return (getBonusCupom() != null && getBonusCupom().getId() != null);
    }

    public String gotoAddNewAddress() {
        setSelectedAddressToBuy(new Address());
        getSelectedAddressToBuy().setUserOf(user.getLoggedUser());
        return "/addNewAddress.faces?faces-redirect=true";
    }

    public String doFinishAddNewAddress() throws Exception {
        user.getLoggedUser().addAddress(selectedAddressToBuy);
        userSessionBean.setUser(user.getLoggedUser());
        return gotoBuyFase2();
    }

    public String callOnAvaliable() {
        if (!user.isUserLogged()) {
            user.setRedirectURLOnLogin("/callme.faces?faces-redirect=true");
            return user.doLoginSystem();
        } else {
            return "/callme.faces?faces-redirect=true";
        }
    }

    public BigDecimal getTotalOFDiscounts() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : basketOfProducts) {
            if (product.getDiscountValue() != null) {
                total = total.add(product.getDiscountValue());
            }
        }
        return total;
    }

    public String doCancel() {
        return "/index.faces?faces-redirect=true";
    }

    public String doFinishCallMe() {
        bean.addNotifiableUserOnAvaliable(user.getLoggedUser().getEmail(), getSelectedBean());
        return "/callmeOk.faces?faces-redirect=true";
    }

    public String doListProducts() {
        list = bean.getAllProducts();
        return "/admin/product/list.faces?faces-redirect=true";
    }

    public String doEditProduct() {
        return "/admin/product/edit.faces?faces-redirect=true";
    }

    public String doFinishEditProduct() {
        bean.setProduct(getSelectedBean());
        return doListProducts();
    }

    public String doCreateProduct() {
        setSelectedBean(new Product());
        return "/admin/product/add.faces?faces-redirect=true";
    }

    public String doFinishCreateProduct() {
        bean.saveProduct(getSelectedBean());
        return doListProducts();
    }

    public List<Category> getAllCategories() {
        return bean.getActiveCategories();
    }

    public String doRemoveProduct() {
        bean.removeProduct(getSelectedBean());
        return doListProducts();
    }

    public FlagType[] getFlagsToCreditCard() {
        return FlagType.values();
    }
       
    public List<Product> getList() {
        return bean.getAllProducts();
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public float getTotalOfSell() {
        return totalOfSell;
    }

    public void setTotalOfSell(Float totalOfSell) {
        this.totalOfSell = totalOfSell;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(Float frete) {
        this.frete = frete;
    }

    public String getCepToCalcFrete() {
        return cepToCalcFrete;
    }

    public void setCepToCalcFrete(String cepToCalcFrete) {
        this.cepToCalcFrete = cepToCalcFrete;
    }

    public String doCalcFrete() throws Exception {
        /*Frete frt = freteBean.getFrete(getCepToCalcFrete());
         setFrete(frt.getSedex());
         System.out.println("Valor do frete:" + frt.getSedex());*/
        updateTotal();
        return "/buy_fase1.faces?faces-redirect=true";
    }

    public Integer getIdOfViewProduct() {
        return idOfViewProduct;
    }

    public void setIdOfViewProduct(Integer idOfViewProduct) {
        this.idOfViewProduct = idOfViewProduct;
        if (idOfViewProduct != null && idOfViewProduct != 0) {
            setSelectedBean(bean.getProductById(idOfViewProduct));
        }
    }

    public Address getSelectedAddressToBuy() {
        return selectedAddressToBuy;
    }

    public void setSelectedAddressToBuy(Address selectedAddressToBuy) {
        this.selectedAddressToBuy = selectedAddressToBuy;
    }
    
    public String clearCart(){
        getBasketOfProducts().clear();
        return "/buy_fase1.faces?faces-redirect=true";
    }

    public List<Product> getBasketOfProducts() {
        return basketOfProducts;
    }

    private void updateTotal() {
        setTotalOfSell(0f);
        for (Product product : basketOfProducts) {
            if(product.getCost() != null) {
                totalOfSell += product.getCost().floatValue();
            }
        }
        totalOfSell = totalOfSell + frete;
        totalOfSell -= getTotalOFDiscounts().floatValue();
        if (getBonusCupom() != null && getBonusCupom().getId() != null) {
            BigDecimal actual = BigDecimal.valueOf(totalOfSell);
            if (getSelectedCupom().getValueOf() != null) {
                actual = actual.subtract(getSelectedCupom().getValueOf());
            }
            if (getSelectedCupom().getPercent() != null) {
                actual = actual.divide(BigDecimal.valueOf(100d)).multiply(BigDecimal.valueOf(100 - getSelectedCupom().getPercent()));
            }
            totalOfSell = actual.floatValue();
        }
        setTotalOfSell(totalOfSell);
    }
        
    public String buyAndGoToCheckout() throws IOException {
        // basketOfProducts.add(getSelectedBean());
        //updateTotal();
        addToBasketOfProducts();
        return proceedToBuy();
    }
    
    public String addToBasketOfProducts() {
        if (getCostWithCupom() != null && !getCostWithCupom().equals(getSelectedBean().getCost())) {
            getSelectedBean().setDiscountValue(getSelectedBean().getCost().subtract(getCostWithCupom()));
        }
        getSelectedBean().setUsedBonus(getSelectedCupom());
        basketOfProducts.add(getSelectedBean());
        updateTotal();
        //searchFaceBean.setSearchTerm(getSelectedBean().getName());
        return doCancel();
    }

    public boolean isSelectedProductWithValidBonusCupom() {
        if (getSelectedCupom() == null) {
            return false;
        } else if (bonusSessionBean.isBonusUsed(selectedCupom)) {
            return false;
        }
        return true;
    }

    public BigDecimal getCostWithCupom() {
        if (!isSelectedProductWithValidBonusCupom()) {
            return getSelectedBean().getCost();
        }
        BigDecimal actual = getSelectedBean().getCost();
        if (getSelectedCupom().getValueOf() != null) {
            actual = actual.subtract(getSelectedCupom().getValueOf());
        }
        if (getSelectedCupom().getPercent() != null) {
            actual = actual.divide(BigDecimal.valueOf(100d)).multiply(BigDecimal.valueOf(100 - getSelectedCupom().getPercent()));
        }
        return actual;
    }

    public String getSelectPaymentForm() {
        return selectPaymentForm;
    }

    public void setSelectPaymentForm(String selectPaymentForm) {
        this.selectPaymentForm = selectPaymentForm;
    }

    public Sell getSelectedSell() {
        return selectedSell;
    }

    public void setSelectedSell(Sell selectedSell) {
        this.selectedSell = selectedSell;
    }

    public CreditCard getCreditCardToUse() {
        return creditCardToUse;
    }

    public void setCreditCardToUse(CreditCard creditCardToUse) {
        this.creditCardToUse = creditCardToUse;
    }

    public List<Sell> getSells() {
        return sellSessionBean.getSellsByUser(user.getLoggedUser());
    }

    public String seeSelledProducts() {
        return "/sellList.faces?faces-redirect=true";
    }

    public String doViewSellDetails() {
        return "/viewSellDetails.faces?faces-redirect=true";
    }

    public Bonus getSelectedCupom() {
        return selectedCupom;
    }

    public void setSelectedCupom(Bonus selectedCupom) {
        this.selectedCupom = selectedCupom;
    }

    public Bonus getBonusCupom() {
        return bonusCupom;
    }

    public void setBonusCupom(Bonus bonusCupom) {
        this.bonusCupom = bonusCupom;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductsToLayoutCentral(List<Product> productsToLayoutCentral) {
        this.productsToLayoutCentral = productsToLayoutCentral;
    }
            
    public List<Product> getProductsToLayoutCentral() {
        productsToLayoutCentral = bean.getProductsWithHighStockDesc();
        return productsToLayoutCentral;
    }

    public List<Product> getProductsByCategory() {
        productsByCategory = bean.getProductsByCategory(categoryName);        
        return productsByCategory;
    }

    public void setProductsByCategory(List<Product> productsByCategory) {
        this.productsByCategory = productsByCategory;
    }
    
    
}
