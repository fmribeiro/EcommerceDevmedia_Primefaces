/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entity.Product;
import face.ManualCDILookup;
import java.util.List;
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
import session.ProductSession;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("product")
@RequestScoped
public class ProductRestWS extends ManualCDILookup{

    @Context
    private UriInfo context;
    private ProductSession bean;

    /**
     * Creates a new instance of ProductRestWS
     */
    public ProductRestWS() {
        bean = getFacadeWithJNDI(ProductSession.class);
    }

    /**
     * Retrieves representation of an instance of ws.ProductRestWS
     * @return an instance of entity.Product
     */
    @GET
    @Produces("application/xml")
    @Path("get/{id}")
    public Product getProductById(@PathParam("id") int productId) {
        return bean.getProductById(productId);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list/{name}")
    public List<Product> getProductByName(@PathParam("name") String name){
        return bean.getAllProductsByNameOrSpec(name);
    }
    
    /**
     * PUT method for updating or creating an instance of ProductRestWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(Product content) {
    }
}
