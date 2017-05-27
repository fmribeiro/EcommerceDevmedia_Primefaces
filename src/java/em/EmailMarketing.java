/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package em;

import entity.Bonus;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class EmailMarketing {

    private final String to;
    private final String nameTo;
    private String subject;
    private String body;
    private final Bonus bonus;

    public EmailMarketing(String to, String nameTo, Bonus bonus) {
        this.to = to;
        this.nameTo = nameTo;
        this.bonus = bonus;
    }

    private void replaceVars() throws IOException {
        String typeOfBonus = "";
        String valueOfBonus = "";
        if (bonus.getProduct() == null) {
            typeOfBonus = "Bonus for all Products";
        } else {
            typeOfBonus = "Bonus for" + bonus.getProduct().getName();
        }

        if (bonus.getValueOf() != null && bonus.getValueOf().intValue() > 0) {
            DecimalFormat df = new DecimalFormat("RS ###,##0.00");
            valueOfBonus = df.format(bonus.getValueOf()) + " OFF !";
        } else {
            valueOfBonus = bonus.getPercent() + " % OFF";
        }
        //LOADING BODY...
        String bodyToUse = loadBody();
        bodyToUse = bodyToUse.replaceAll("@@@NAME_OF_CUSTOMER@@@", nameTo);
        bodyToUse = bodyToUse.replaceAll("@@@TYPE_OF_BONUS@@@", typeOfBonus);
        bodyToUse = bodyToUse.replaceAll("@@@VALUE_OF_BONUS@@@", valueOfBonus);
        setBody(bodyToUse);
        setSubject("Hello, NEW DISCOUNTS ! FOR "+nameTo);
    }

    private String loadBody() {
        String bodyToReturn = "";
        try {
            InputStream inputStream = EmailMarketing.class.getResourceAsStream("emailm.html");
            int size = inputStream.available();
            for (int i = 0; i < size; i++) {
                byte[] buffer = new byte[1];
                inputStream.read(buffer);
                bodyToReturn += new String(buffer);
            }
        } catch (IOException ex) {
            Logger.getLogger(EmailMarketing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bodyToReturn;
    }

    public void sendMail()throws IOException{
        replaceVars();
    }

    public Bonus getBonus() {
        return bonus;
    }

    public String getTo() {
        return to;
    }

    public String getNameTo() {
        return nameTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
