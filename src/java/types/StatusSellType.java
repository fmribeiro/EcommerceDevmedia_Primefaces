/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

/**
 *
 * @author Administrador
 */
public enum StatusSellType {
    INICIO("Início"),
    APROVADO("Aprovado pela financeira"),
    EMPROGRESSO("Em progresso"),
    ENVIADO("Enviado"),
    FINALIZADO("Finalizado");
    
    private String desc;

    private StatusSellType(String desc) {
        this.desc = desc;
    }
    
    public String getDescription(){
        return desc;
    }
    
}
