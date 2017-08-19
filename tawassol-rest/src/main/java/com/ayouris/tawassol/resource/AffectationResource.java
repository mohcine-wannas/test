package com.ayouris.tawassol.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import com.ayouris.tawassol.common.model.bean.AffectationBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views.FileAttenteView;
import com.ayouris.tawassol.service.AffectationService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("affectationResource")
@Path("/affectation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AffectationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AffectationResource.class);

    @Autowired
    private AffectationService affectationService;

    @Autowired
    public AffectationResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.affectationService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.affectationService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    @JsonView(CommonView.class)
    public Response remove(@PathParam("id") Long id) {
        this.affectationService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(EditionView.class)
    public Response create(AffectationBean object) {
        try {
            return Response.ok( this.affectationService.createOrUpdate( object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.AFFECTATION)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
        return Response.ok(this.affectationService.getAll()).build();
    }
    
    @GET
    @Path("/produits")
    @JsonView(FileAttenteView.class)
    public Response getAll(@QueryParam("siteId") Long siteId,
    						@QueryParam("clientId") Long clientId,
    						@QueryParam("concessionnaireId") Long concessionnaireId,
    						@QueryParam("typeChargment") TypeChargement typeChargement) {
    	
    	List<AffectationBean> affectations;
    	affectations = this.affectationService.getAll();
    	affectations = this.affectationService.findBySiteIdAndClientIdAndConcessionnaireIdAndProduitTypeChargement(siteId, clientId, concessionnaireId,typeChargement);
    	return Response.ok(affectations).build();
    }

}
