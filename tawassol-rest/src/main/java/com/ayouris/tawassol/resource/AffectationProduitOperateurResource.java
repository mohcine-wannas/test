package com.ayouris.tawassol.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.AffectationProduitListOperateurBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.service.AffectationProduitOperateurService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("affectationProduitOperateurResource")
@Path("/affectationProduitOperateur")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AffectationProduitOperateurResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AffectationProduitOperateurResource.class);

    @Autowired
    private AffectationProduitOperateurService affectationProduitOperateurService;

    @Autowired
    public AffectationProduitOperateurResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.affectationProduitOperateurService.list(pageDataBean)).build();
    }

    @GET
    @Path("sites/{idSite}/users/{idUser}")
    public Response getDetails(@PathParam("idSite") Long idSite, @PathParam("idUser") Long idUser) {
        return Response.ok(this.affectationProduitOperateurService.findBySiteIdAndUserId(idSite,idUser)).build();
    }
    
    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.affectationProduitOperateurService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.affectationProduitOperateurService.delete(id);
        return Response.ok().build();
    }
    @DELETE
    @Path("sites/{idSite}/users/{idUser}")
    public Response remove(@PathParam("idSite") Long idSite, @PathParam("idUser") Long idUser) {
    	this.affectationProduitOperateurService.removeBySiteIdAndUserId(idSite,idUser);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AffectationProduitListOperateurBean object) {
        try {
            return Response.ok(this.affectationProduitOperateurService.createOrUpdate(object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }


    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.AFFECTATION_PRODUIT_OPERATEUR)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.affectationProduitOperateurService.getAll()).build();
    }

}
