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

import com.fasterxml.jackson.annotation.JsonView;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ConcessionnaireService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("concessionnaireResource")
@Path("/concessionnaire")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConcessionnaireResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcessionnaireResource.class);

    @Autowired
    private ConcessionnaireService concessionnaireService;

    @Autowired
    public ConcessionnaireResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.concessionnaireService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.concessionnaireService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
    	try {
    		this.concessionnaireService.remove(id);
    	}catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ConcessionnaireBean object) {
        try {
            return Response.ok(this.concessionnaireService.createOrUpdate(object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.CONCESSIONNAIRE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
        return Response.ok(this.concessionnaireService.getAll()).build();
    }
    
    @GET
    @Path("/{id}/chauffeurs/")
    @JsonView(CommonView.class)
    public Response getChauffeursByConcessionnaire(@PathParam("id") Long id) {
        return Response.ok(this.concessionnaireService.getAllChauffeursByConcessionnaireId(id)).build();
    }
    
    @GET
    @Path("/{id}/camions/")
    @JsonView(CommonView.class)
    public Response getCamionsByConcessionnaire(@PathParam("id") Long id) {
        return Response.ok(this.concessionnaireService.getAllCamionsByConcessionnaireId(id)).build();
    }
    
    @GET
    @Path("/{id}/produits/")
    @JsonView(CommonView.class)
    public Response getProduitByConcessionnaire(@PathParam("id") Long id) {
        return Response.ok(this.concessionnaireService.getAllProduitByConcessionnaireId(id)).build();
    }
    
    @GET
    @Path("/{id}/signataires/")
    @JsonView(CommonView.class)
    public Response getSignatairesByConcessionnaire(@PathParam("id") Long id) {
        return Response.ok(this.concessionnaireService.getAllSignatairesByConcessionnaireId(id)).build();
    }
}
