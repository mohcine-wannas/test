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
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.JourneeActiviteService;
import com.ayouris.tawassol.service.ServiceException;

@Component("journeeActiviteResource")
@Path("/journee-activite")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class JourneeActiviteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneeActiviteResource.class);

    @Autowired
    private JourneeActiviteService journeeActiviteService;

    @Autowired
    public JourneeActiviteResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.journeeActiviteService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.journeeActiviteService.getDetails(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JourneeActiviteBean object) {
        try {

            this.journeeActiviteService.createOrUpdate( object);
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
            return Response.ok(columnDefService.getByViewName(ViewName.JOURNEE_ACTIVITE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    
    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
    	
    	return Response.ok(this.journeeActiviteService.getAll()).build();
    }
    
    @GET
    @Path("/currents/{activite}")
    public Response getCurrentJourneeActivite(@PathParam("activite") ActiviteJourneeActivite activite) {
    	return Response.ok(this.journeeActiviteService.getCurrentJourneeActiviteOuvertByActivity(activite)).build();
    }
    

}
