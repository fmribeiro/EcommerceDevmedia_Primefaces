/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import entity.Letter;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import session.LetterSession;

/**
 *
 * @author Administrador
 */
@Named
@SessionScoped
public class LetterFace extends BaseCDIBean<Letter> {

    private Letter newLetter = new Letter();
    private Letter letterOld;
    @Inject
    private LetterSession letterSession;
    private List<Letter> letters;
    private Letter selectedLetter;
    private String letterMail;
    @Resource(mappedName = "mail/myDevmediaSession")
    private Session mailSession;

    public String addNewLetter() {
        letterSession.addLetter(getNewLetter().getName(), getNewLetter().getEmail());
        letterOld = newLetter;
        setNewLetter(new Letter());
        return "/addedLetter.faces?faces-redirect=true";
    }

    public String doRemoveLetter() {
        letterSession.removeLetter(getSelectedLetter().getId());
        setLetters(null);
        return "/admin/letter/list.faces?faces-redirect=true";
    }

    public String doListNewsLetterPeoples() {
        return "/admin/letter/list.faces?faces-redirect=true";
    }

    public String doSendNewsLetter() {
        try {
            for (Letter letter : letters) {
                if (letter.isSelected()) {
                    System.out.println("Sending News Letter !!! To " + letter.getEmail() + " LIVE");
                    String bodyToSend = getLetterMail();
                    bodyToSend = bodyToSend.replaceAll("@@@NAME_OF_CUSTOMER@@@", letter.getName());
                    bodyToSend = bodyToSend.replaceAll("@@@EMAIL_OF_CUSTOMER@@@", letter.getEmail());                    

                    InternetAddress from = new InternetAddress("fmribeiro21@gmail.com");
                    InternetAddress to = new InternetAddress(letter.getEmail());
                    
                    Message message = new MimeMessage(mailSession);
                    message.setFrom(from);
                    message.setRecipient(Message.RecipientType.TO, to);
                    message.setSubject("News Letter");
                    message.setContent(bodyToSend, "text/html");
                    message.setReplyTo(new Address[]{new InternetAddress("fmendesribeiro@yahoo.com.br")});
                    
                    Transport transport = mailSession.getTransport("smtps");
                    transport.connect("smtp.gmail.com", "fmribeiro21@gmail.com", "236161537");
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }
            }
        } catch (Exception e) {
            System.out.println("See " + e);
        }

        return "/admin/letter/sended.faces?faces-redirect=true";
    }

    public String getLetterMail() {
        return letterMail;
    }

    public void setLetterMail(String letterMail) {
        this.letterMail = letterMail;
    }

    public Letter getSelectedLetter() {
        return selectedLetter;
    }

    public void setSelectedLetter(Letter selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public List<Letter> getLetters() {
        if (letters == null) {
            letters = letterSession.getAllLetters();
        }
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    public Letter getNewLetter() {
        return newLetter;
    }

    public void setNewLetter(Letter newLetter) {
        this.newLetter = newLetter;
    }

    public Letter getLetterOld() {
        return letterOld;
    }

    public void setLetterOld(Letter letterOld) {
        this.letterOld = letterOld;
    }

    @PreDestroy
    public void doExit() {
        System.out.println("Exit the instance of letter face");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Running FINALIZE of Letter face");
        super.finalize();
    }
}
