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
import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.RefView.ChauffeurListView;
import com.ayouris.tawassol.service.ChauffeurService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */
@Component("chauffeurResource")
@Path("/chauffeur")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChauffeurResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChauffeurResource.class);

    @Autowired
    private ChauffeurService chauffeurService;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    public ChauffeurResource(){

    }

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ChauffeurListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.chauffeurService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.chauffeurService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.chauffeurService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(EditionView.class)
    public Response create(ChauffeurBean object) {
        try {

            
            return Response.ok(this.chauffeurService.createOrUpdate( object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.CHAUFFEUR)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }
    
    @GET
    @Path("/")
    @JsonView(CommonView.class)
    public Response getAll() {
        return Response.ok(this.chauffeurService.getAll()).build();
    }


}
