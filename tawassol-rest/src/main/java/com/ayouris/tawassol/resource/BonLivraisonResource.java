package com.ayouris.tawassol.resource;

import javax.ws.rs.Consumes;
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
import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views.EntryControlView;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * Created by Issmahane EL BAZ on 20/06/2017.
 */

@Component("bonLivraisonResource")
@Path("/bon-livraison")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BonLivraisonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonLivraisonResource.class);


    @Autowired
    private BonLivraisonService bonLivraisonService;


    @Autowired
    public BonLivraisonResource() {

    }
    @Autowired
    private ColumnDefService columnDefService;

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.bonLivraisonService.getDetails(id)).build();
    }

    @PUT
    @Path("/")
    @JsonView(EditionView.class)
    public Response createOrUpdate(BonLivraisonBean object) {
        try {
            this.bonLivraisonService.create(object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @PUT
    @Path("{id}/cancel")
    @JsonView(EditionView.class)
    //public Response annuler(@PathParam("id") Long id) {
    public Response annuler(@PathParam("id") Long id, String motif) {
        this.bonLivraisonService.annuler(id,motif);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/mark")
    @JsonView(EditionView.class)
    public Response marquerTraite(@PathParam("id") Long id) {
        this.bonLivraisonService.marquerTraite(id);
        return Response.ok().build();
    }

    @GET
    @Path("/")
    @JsonView(ListView.class)
    public Response getAll() {
        return Response.ok(this.bonLivraisonService.getAll()).build();
    }
    
    @GET
	@Path("columnDef")
	public Response getColumnDef() {
		try {
			return Response.ok(columnDefService.getByViewName(ViewName.BON_LIVRAISON)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new WebApplicationException(e.getError().getMessage());
		}
	}
    
    @POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(ListView.class)
	public Response list(PageDataBean pageDataBean) {
		return Response.ok(this.bonLivraisonService.list(pageDataBean)).build();
	}
    
    
    @GET
    @Path("/{id}/controle-camion-conformities")
    @JsonView(EntryControlView.class)
    public Response getControlCamionConformity(@PathParam("id") Long id) {
    		return Response.ok(bonLivraisonService.getControleCamionConformities(id)).build();
    }
    
    @POST
    @Path("/{id}/controle-camion-conformities")
    public Response getControlCamionConformity(BonLivraisonBean object) {
    	this.bonLivraisonService.setControleCamionConformity(object);
        return Response.ok().build();
    }

}
