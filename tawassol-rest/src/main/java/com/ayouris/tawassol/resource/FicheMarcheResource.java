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
import com.ayouris.tawassol.common.model.bean.FicheMarcheBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.FicheMarcheService;
import com.ayouris.tawassol.service.ServiceException;

@Component("ficheMarcheResource")
@Path("/fiche-marche")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class FicheMarcheResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FicheMarcheResource.class);

    @Autowired
    private FicheMarcheService ficheMarcheService;

    @Autowired
    public FicheMarcheResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.ficheMarcheService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        
        try {
        	return Response.ok(this.ficheMarcheService.getDetails(id)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FicheMarcheBean object) {
        try {

            this.ficheMarcheService.createOrUpdate( object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }
    
    @PUT
	@Path("{id}/cloturer")
	@JsonView(EditionView.class)
	public Response cloturer(@PathParam("id") Long id) {
		
		try {
			this.ficheMarcheService.cloturer(id);
			return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
		
	}

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.FICHE_MARCHE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
    	
        return Response.ok(this.ficheMarcheService.getAll()).build();
    }
    
    @GET
    @Path("/posts")
    @JsonView(CommonView.class)
    public Response getAllPost() {
    	return Response.ok(this.ficheMarcheService.getAllPost()).build();
    }
    
    @GET
    @Path("/causesArret")
    @JsonView(CommonView.class)
    public Response getAllCausesArret() {
        return Response.ok(this.ficheMarcheService.getAllCausesArret()).build();
    }

}
