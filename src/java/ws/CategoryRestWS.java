/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entity.Category;
import face.ManualCDILookup;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import session.ProductSession;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("category")
@RequestScoped
public class CategoryRestWS extends ManualCDILookup{

    @Context
    private UriInfo context;
    @Inject
    private ProductSession bean;

    /**
     * Creates a new instance of CategoryRestWS
     */
    public CategoryRestWS() {
        bean = getFacadeWithJNDI(ProductSession.class);
    }

    /**
     * Retrieves representation of an instance of ws.CategoryRestWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    @Path("list")
    public List<Category> getCategories() {
        System.out.println("Veja "+bean);
        return bean.getAllCategories();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("get/{id}")
    public Category getCategoriesById(@PathParam("id") int idCategory){
        return bean.getCategoryById(idCategory);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("list/{name}")
     public List<Category> getCategoriesByName(@PathParam("name") String name){
        return bean.getCategoriesByName(name);
    }
    
    /**
     * PUT method for updating or creating an instance of CategoryRestWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
