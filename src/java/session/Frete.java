/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@XmlRootElement(name="frete")
@XmlAccessorType(XmlAccessType.FIELD)
public class Frete implements Serializable{
    
    @XmlElement(name="uf_nome")
    private String estado;
    @XmlElement(name="destino")
    private String destino;
    @XmlElement(name="valor_sedex")
    private float sedex;
    @XmlElement(name="valor_pac")
    private float pac;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getSedex() {
        return sedex;
    }

    public void setSedex(float sedex) {
        this.sedex = sedex;
    }

    public float getPac() {
        return pac;
    }

    public void setPac(float pac) {
        this.pac = pac;
    }
    
    
}
