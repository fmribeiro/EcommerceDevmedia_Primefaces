/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import correios.Calcula_frete;
import correios.Calcula_fretePortType;
import correios.Calcula_frete_Impl;
import entity.Address;
import java.util.StringTokenizer;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("correios")
@RequestScoped
public class CorreiosWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CorreiosWS
     */
    public CorreiosWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/{cep}")
    public Address getCategoriesById(@PathParam("cep") String cep){
       Address toReturn = new Address(); 
       try{     
    
            Calcula_frete calcula_frete = new Calcula_frete_Impl();
            Calcula_fretePortType calcula_fretePort = calcula_frete.getCalcula_fretePort();
            StringTokenizer stringTokenizer = new StringTokenizer(calcula_fretePort.traz_cep(cep),"#");
            stringTokenizer.nextToken();
            toReturn.setAddress(stringTokenizer.nextToken());
            stringTokenizer.nextToken();            
            stringTokenizer.nextToken();
            toReturn.setStateOfAddress(stringTokenizer.nextToken());            
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return toReturn;
    }
    /**
     * PUT method for updating or creating an instance of CorreiosWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
