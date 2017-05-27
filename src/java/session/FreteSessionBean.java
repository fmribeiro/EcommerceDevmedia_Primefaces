/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FreteSessionBean {

    public Frete getFrete(String cepDestino) throws MalformedURLException, IOException, JAXBException{
        cepDestino = cepDestino.replaceAll("\\.", "");
        cepDestino = cepDestino.replace("\\-", "");       
        System.out.println("O CEP é: "+cepDestino);
        String url = "http://frete.w21studio.com/calFrete.xml?cep="+cepDestino+"&cod=8341&peso=10&comprimento=60&largura=60&altura=5&servico=3";
        URL myURL = new URL(url);        
        JAXBContext jAXBContext = JAXBContext.newInstance(new Class[]{Frete.class});
        Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
        Frete toReturn =(Frete) unmarshaller.unmarshal(myURL);
        return toReturn;        
    }

}
