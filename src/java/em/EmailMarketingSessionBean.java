/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package em;

import com.sun.mail.smtp.SMTPTransport;
import entity.Bonus;
import entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import session.BonusSession;
import session.ProductSession;
import session.UserSession;

/**
 *
 * @author Administrador
 */
@Singleton
public class EmailMarketingSessionBean implements Serializable {

    @Resource(mappedName = "mail/myDevmediaSession")
    private Session mailSession;
    @EJB
    private BonusSession bonusSession;
    @EJB
    private UserSession userSession;
    @EJB
    private ProductSession productSession;

    //@Schedule(hour = "*", minute = "*")
    public void fireEmailMarket() throws AddressException, IOException, MessagingException {
        try {
            for (User userToMarket : userSession.getLastUsers()) {
                System.out.println("Sending Email !!! To " + userToMarket.getEmail() + " LIVE");
                Bonus bonusToUse = bonusSession.createBonus(0, new BigDecimal(10), productSession.getProductById(101));//geladeira
                EmailMarketing em = new EmailMarketing(userToMarket.getEmail(), userToMarket.getName(), bonusToUse);
                em.sendMail();
                InternetAddress from = new InternetAddress("fmribeiro21@gmail.com");
                InternetAddress to = new InternetAddress(userToMarket.getEmail());
                Message message = new MimeMessage(mailSession);
                message.setFrom(from);
                message.setRecipient(Message.RecipientType.TO, to);
                message.setSubject(em.getSubject());
                message.setContent(em.getBody(), "text/html");
                message.setReplyTo(new Address[]{new InternetAddress("fmendesribeiro@yahoo.com.br")});
                /*
                 SMTPTransport sMTPTransport = new SMTPTransport(mailSession, null);
                 sMTPTransport.connect();
                 sMTPTransport.sendMessage(message, message.getAllRecipients());
                 sMTPTransport.close();*/
                Transport transport = mailSession.getTransport("smtps");
                transport.connect("smtp.gmail.com", "fmribeiro21@gmail.com", "236161537");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
        } catch (Exception e) {
            System.out.println("See " + e);
            //e.printStackTrace();
        }
    }
}
