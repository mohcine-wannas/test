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

import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Component("siteResource")
@Path("/site")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SiteResource  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SiteResource.class);
	
	@Autowired
	private SiteService siteService;

    @Autowired
    private ColumnDefService columnDefService;
    
    @Autowired
    public SiteResource(){
	}
    

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean paginateData) {
        return Response.ok(this.siteService.list(paginateData)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.siteService.getDetails(id)).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
    	this.siteService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(SiteBean object) {
        try {
        	
            this.siteService.createOrUpdate( object);
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
        	return Response.ok(columnDefService.getByViewName(ViewName.SITE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }
    
    @GET
    @Path("/")
    public Response getAll() {
    	return Response.ok(this.siteService.getAll()).build();
    }
    @GET
    @Path("/notSiege")
    public Response getAllSiteNotSiege() {
        return Response.ok(this.siteService.getAllSiteNotSiege()).build();
    }

    @GET
    @Path("/excludeSiege")
    public Response getExcludeSiege() {
        return Response.ok(this.siteService.getExcludeSiege()).build();
    }

    @GET
    @Path("/{id}/journeesOuvertes/")
    @JsonView(CommonView.class)
    public Response getAllJourneesActOuvertesBySiteId(@PathParam("id") Long id) {
        return Response.ok(this.siteService.getAllJourneeActOuverteBySiteId(id)).build();
    }
    
    @GET
    @Path("/{id}/journees/")
    @JsonView(CommonView.class)
    public Response getAllJourneesActBySiteId(@PathParam("id") Long id) {
        return Response.ok(this.siteService.getAllJourneeActBySiteId(id)).build();
    }


}
