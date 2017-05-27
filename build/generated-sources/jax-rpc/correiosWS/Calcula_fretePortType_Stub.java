// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R2)
// Generated source version: 1.1.3

package correiosWS;

import com.sun.xml.rpc.server.http.MessageContextProperties;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.soap.streaming.*;
import com.sun.xml.rpc.soap.message.*;
import com.sun.xml.rpc.soap.SOAPVersion;
import com.sun.xml.rpc.soap.SOAPEncodingConstants;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.lang.reflect.*;
import java.lang.Class;
import com.sun.xml.rpc.client.SenderException;
import com.sun.xml.rpc.client.*;
import com.sun.xml.rpc.client.http.*;
import javax.xml.rpc.handler.*;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPFaultException;

public class Calcula_fretePortType_Stub
    extends com.sun.xml.rpc.client.StubBase
    implements correiosWS.Calcula_fretePortType {
    
    
    
    /*
     *  public constructor
     */
    public Calcula_fretePortType_Stub(HandlerChain handlerChain) {
        super(handlerChain);
        _setProperty(ENDPOINT_ADDRESS_PROPERTY, "http://www.maniezo.com.br/webservice/soap-server.php");
    }
    
    
    /*
     *  implementation of traz_cep
     */
    public java.lang.String traz_cep(java.lang.String cep)
        throws java.rmi.RemoteException {
        
        try {
            
            StreamingSenderState _state = _start(_handlerChain);
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(traz_cep_OPCODE);
            correiosWS.Calcula_fretePortType_traz_cep_RequestStruct _myCalcula_fretePortType_traz_cep_RequestStruct =
                new correiosWS.Calcula_fretePortType_traz_cep_RequestStruct();
            
            _myCalcula_fretePortType_traz_cep_RequestStruct.setCep(cep);
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_traz_cep_traz_cep_QNAME);
            _bodyBlock.setValue(_myCalcula_fretePortType_traz_cep_RequestStruct);
            _bodyBlock.setSerializer(ns1_myCalcula_fretePortType_traz_cep_RequestStruct_SOAPSerializer);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty(HttpClientTransport.HTTP_SOAPACTION_PROPERTY, "http://");
            
            _send((java.lang.String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            correiosWS.Calcula_fretePortType_traz_cep_ResponseStruct _myCalcula_fretePortType_traz_cep_ResponseStruct = null;
            Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _myCalcula_fretePortType_traz_cep_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_traz_cep_ResponseStruct)((SOAPDeserializationState)_responseObj).getInstance();
            } else {
                _myCalcula_fretePortType_traz_cep_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_traz_cep_ResponseStruct)_responseObj;
            }
            
            return _myCalcula_fretePortType_traz_cep_ResponseStruct.get_return();
        } catch (RemoteException e) {
            // let this one through unchanged
            throw e;
        } catch (JAXRPCException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    /*
     *  implementation of calcula_frete
     */
    public java.lang.String calcula_frete(java.lang.String frete)
        throws java.rmi.RemoteException {
        
        try {
            
            StreamingSenderState _state = _start(_handlerChain);
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(calcula_frete_OPCODE);
            correiosWS.Calcula_fretePortType_calcula_frete_RequestStruct _myCalcula_fretePortType_calcula_frete_RequestStruct =
                new correiosWS.Calcula_fretePortType_calcula_frete_RequestStruct();
            
            _myCalcula_fretePortType_calcula_frete_RequestStruct.setFrete(frete);
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_calcula_frete_calcula_frete_QNAME);
            _bodyBlock.setValue(_myCalcula_fretePortType_calcula_frete_RequestStruct);
            _bodyBlock.setSerializer(ns1_myCalcula_fretePortType_calcula_frete_RequestStruct_SOAPSerializer);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty(HttpClientTransport.HTTP_SOAPACTION_PROPERTY, "http://");
            
            _send((java.lang.String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            correiosWS.Calcula_fretePortType_calcula_frete_ResponseStruct _myCalcula_fretePortType_calcula_frete_ResponseStruct = null;
            Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _myCalcula_fretePortType_calcula_frete_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_calcula_frete_ResponseStruct)((SOAPDeserializationState)_responseObj).getInstance();
            } else {
                _myCalcula_fretePortType_calcula_frete_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_calcula_frete_ResponseStruct)_responseObj;
            }
            
            return _myCalcula_fretePortType_calcula_frete_ResponseStruct.get_return();
        } catch (RemoteException e) {
            // let this one through unchanged
            throw e;
        } catch (JAXRPCException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    /*
     *  implementation of traz_cep_partes
     */
    public java.lang.String traz_cep_partes(java.lang.String cep)
        throws java.rmi.RemoteException {
        
        try {
            
            StreamingSenderState _state = _start(_handlerChain);
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(traz_cep_partes_OPCODE);
            correiosWS.Calcula_fretePortType_traz_cep_partes_RequestStruct _myCalcula_fretePortType_traz_cep_partes_RequestStruct =
                new correiosWS.Calcula_fretePortType_traz_cep_partes_RequestStruct();
            
            _myCalcula_fretePortType_traz_cep_partes_RequestStruct.setCep(cep);
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_traz_cep_partes_traz_cep_partes_QNAME);
            _bodyBlock.setValue(_myCalcula_fretePortType_traz_cep_partes_RequestStruct);
            _bodyBlock.setSerializer(ns1_myCalcula_fretePortType_traz_cep_partes_RequestStruct_SOAPSerializer);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty(HttpClientTransport.HTTP_SOAPACTION_PROPERTY, "http://");
            
            _send((java.lang.String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            correiosWS.Calcula_fretePortType_traz_cep_partes_ResponseStruct _myCalcula_fretePortType_traz_cep_partes_ResponseStruct = null;
            Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _myCalcula_fretePortType_traz_cep_partes_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_traz_cep_partes_ResponseStruct)((SOAPDeserializationState)_responseObj).getInstance();
            } else {
                _myCalcula_fretePortType_traz_cep_partes_ResponseStruct =
                    (correiosWS.Calcula_fretePortType_traz_cep_partes_ResponseStruct)_responseObj;
            }
            
            return _myCalcula_fretePortType_traz_cep_partes_ResponseStruct.get_return();
        } catch (RemoteException e) {
            // let this one through unchanged
            throw e;
        } catch (JAXRPCException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    
    /*
     *  this method deserializes the request/response structure in the body
     */
    protected void _readFirstBodyElement(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState  state) throws Exception {
        int opcode = state.getRequest().getOperationCode();
        switch (opcode) {
            case traz_cep_OPCODE:
                _deserialize_traz_cep(bodyReader, deserializationContext, state);
                break;
            case calcula_frete_OPCODE:
                _deserialize_calcula_frete(bodyReader, deserializationContext, state);
                break;
            case traz_cep_partes_OPCODE:
                _deserialize_traz_cep_partes(bodyReader, deserializationContext, state);
                break;
            default:
                throw new SenderException("sender.response.unrecognizedOperation", java.lang.Integer.toString(opcode));
        }
    }
    
    
    
    /*
     * This method deserializes the body of the traz_cep operation.
     */
    private void _deserialize_traz_cep(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        java.lang.Object myCalcula_fretePortType_traz_cep_ResponseStructObj =
            ns1_myCalcula_fretePortType_traz_cep_ResponseStruct_SOAPSerializer.deserialize(ns1_traz_cep_traz_cepResponse_QNAME,
                bodyReader, deserializationContext);
        
        SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_traz_cep_traz_cepResponse_QNAME);
        bodyBlock.setValue(myCalcula_fretePortType_traz_cep_ResponseStructObj);
        state.getResponse().setBody(bodyBlock);
    }
    
    /*
     * This method deserializes the body of the calcula_frete operation.
     */
    private void _deserialize_calcula_frete(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        java.lang.Object myCalcula_fretePortType_calcula_frete_ResponseStructObj =
            ns1_myCalcula_fretePortType_calcula_frete_ResponseStruct_SOAPSerializer.deserialize(ns1_calcula_frete_calcula_freteResponse_QNAME,
                bodyReader, deserializationContext);
        
        SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_calcula_frete_calcula_freteResponse_QNAME);
        bodyBlock.setValue(myCalcula_fretePortType_calcula_frete_ResponseStructObj);
        state.getResponse().setBody(bodyBlock);
    }
    
    /*
     * This method deserializes the body of the traz_cep_partes operation.
     */
    private void _deserialize_traz_cep_partes(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        java.lang.Object myCalcula_fretePortType_traz_cep_partes_ResponseStructObj =
            ns1_myCalcula_fretePortType_traz_cep_partes_ResponseStruct_SOAPSerializer.deserialize(ns1_traz_cep_partes_traz_cep_partesResponse_QNAME,
                bodyReader, deserializationContext);
        
        SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_traz_cep_partes_traz_cep_partesResponse_QNAME);
        bodyBlock.setValue(myCalcula_fretePortType_traz_cep_partes_ResponseStructObj);
        state.getResponse().setBody(bodyBlock);
    }
    
    
    
    public java.lang.String _getDefaultEnvelopeEncodingStyle() {
        return SOAPNamespaceConstants.ENCODING;
    }
    
    public java.lang.String _getImplicitEnvelopeEncodingStyle() {
        return "";
    }
    
    public java.lang.String _getEncodingStyle() {
        return SOAPNamespaceConstants.ENCODING;
    }
    
    public void _setEncodingStyle(java.lang.String encodingStyle) {
        throw new UnsupportedOperationException("cannot set encoding style");
    }
    
    
    
    
    
    /*
     * This method returns an array containing (prefix, nsURI) pairs.
     */
    protected java.lang.String[] _getNamespaceDeclarations() {
        return myNamespace_declarations;
    }
    
    /*
     * This method returns an array containing the names of the headers we understand.
     */
    public javax.xml.namespace.QName[] _getUnderstoodHeaders() {
        return understoodHeaderNames;
    }
    
    
    protected void _preHandlingHook(StreamingSenderState state) throws Exception {
        super._preHandlingHook(state);
    }
    
    
    protected boolean _preRequestSendingHook(StreamingSenderState state) throws Exception {
        boolean bool = false;
        bool = super._preRequestSendingHook(state);
        return bool;
    }
    
    public void _initialize(InternalTypeMappingRegistry registry) throws Exception {
        super._initialize(registry);
        ns1_myCalcula_fretePortType_traz_cep_partes_ResponseStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_traz_cep_partes_ResponseStruct.class, ns1_traz_cep_partesResponse_TYPE_QNAME);
        ns1_myCalcula_fretePortType_calcula_frete_ResponseStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_calcula_frete_ResponseStruct.class, ns1_calcula_freteResponse_TYPE_QNAME);
        ns1_myCalcula_fretePortType_traz_cep_partes_RequestStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_traz_cep_partes_RequestStruct.class, ns1_traz_cep_partes_TYPE_QNAME);
        ns1_myCalcula_fretePortType_calcula_frete_RequestStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_calcula_frete_RequestStruct.class, ns1_calcula_frete_TYPE_QNAME);
        ns1_myCalcula_fretePortType_traz_cep_RequestStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_traz_cep_RequestStruct.class, ns1_traz_cep_TYPE_QNAME);
        ns1_myCalcula_fretePortType_traz_cep_ResponseStruct_SOAPSerializer = (CombinedSerializer)registry.getSerializer(SOAPConstants.NS_SOAP_ENCODING, correiosWS.Calcula_fretePortType_traz_cep_ResponseStruct.class, ns1_traz_cepResponse_TYPE_QNAME);
    }
    
    private static final javax.xml.namespace.QName _portName = new QName("http://www.maniezo.com.br/", "calcula_fretePort");
    private static final int traz_cep_OPCODE = 0;
    private static final int calcula_frete_OPCODE = 1;
    private static final int traz_cep_partes_OPCODE = 2;
    private static final javax.xml.namespace.QName ns1_traz_cep_traz_cep_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep");
    private static final javax.xml.namespace.QName ns1_traz_cep_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep");
    private CombinedSerializer ns1_myCalcula_fretePortType_traz_cep_RequestStruct_SOAPSerializer;
    private static final javax.xml.namespace.QName ns1_traz_cep_traz_cepResponse_QNAME = new QName("http://www.maniezo.com.br/", "traz_cepResponse");
    private static final javax.xml.namespace.QName ns1_traz_cepResponse_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "traz_cepResponse");
    private CombinedSerializer ns1_myCalcula_fretePortType_traz_cep_ResponseStruct_SOAPSerializer;
    private static final javax.xml.namespace.QName ns1_calcula_frete_calcula_frete_QNAME = new QName("http://www.maniezo.com.br/", "calcula_frete");
    private static final javax.xml.namespace.QName ns1_calcula_frete_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "calcula_frete");
    private CombinedSerializer ns1_myCalcula_fretePortType_calcula_frete_RequestStruct_SOAPSerializer;
    private static final javax.xml.namespace.QName ns1_calcula_frete_calcula_freteResponse_QNAME = new QName("http://www.maniezo.com.br/", "calcula_freteResponse");
    private static final javax.xml.namespace.QName ns1_calcula_freteResponse_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "calcula_freteResponse");
    private CombinedSerializer ns1_myCalcula_fretePortType_calcula_frete_ResponseStruct_SOAPSerializer;
    private static final javax.xml.namespace.QName ns1_traz_cep_partes_traz_cep_partes_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep_partes");
    private static final javax.xml.namespace.QName ns1_traz_cep_partes_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep_partes");
    private CombinedSerializer ns1_myCalcula_fretePortType_traz_cep_partes_RequestStruct_SOAPSerializer;
    private static final javax.xml.namespace.QName ns1_traz_cep_partes_traz_cep_partesResponse_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep_partesResponse");
    private static final javax.xml.namespace.QName ns1_traz_cep_partesResponse_TYPE_QNAME = new QName("http://www.maniezo.com.br/", "traz_cep_partesResponse");
    private CombinedSerializer ns1_myCalcula_fretePortType_traz_cep_partes_ResponseStruct_SOAPSerializer;
    private static final java.lang.String[] myNamespace_declarations =
                                        new java.lang.String[] {
                                            "ns0", "http://www.maniezo.com.br/"
                                        };
    
    private static final QName[] understoodHeaderNames = new QName[] {  };
}
