// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R2)
// Generated source version: 1.1.3

package correiosWS;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.util.exception.LocalizableExceptionAdapter;

public class Calcula_fretePortType_traz_cep_RequestStruct_SOAPBuilder implements SOAPInstanceBuilder {
    private correiosWS.Calcula_fretePortType_traz_cep_RequestStruct _instance;
    private java.lang.String cep;
    private static final int myCEP_INDEX = 0;
    
    public Calcula_fretePortType_traz_cep_RequestStruct_SOAPBuilder() {
    }
    
    public void setCep(java.lang.String cep) {
        this.cep = cep;
    }
    
    public int memberGateType(int memberIndex) {
        switch (memberIndex) {
            case myCEP_INDEX:
                return GATES_INITIALIZATION | REQUIRES_CREATION;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    public void construct() {
    }
    
    public void setMember(int index, java.lang.Object memberValue) {
        try {
            switch(index) {
                case myCEP_INDEX:
                    _instance.setCep((java.lang.String)memberValue);
                    break;
                default:
                    throw new java.lang.IllegalArgumentException();
            }
        }
        catch (java.lang.RuntimeException e) {
            throw e;
        }
        catch (java.lang.Exception e) {
            throw new DeserializationException(new LocalizableExceptionAdapter(e));
        }
    }
    
    public void initialize() {
    }
    
    public void setInstance(java.lang.Object instance) {
        _instance = (correiosWS.Calcula_fretePortType_traz_cep_RequestStruct)instance;
    }
    
    public java.lang.Object getInstance() {
        return _instance;
    }
}
