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
import com.ayouris.tawassol.common.model.bean.TransporteurBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.TransporteurService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("transporteurResource")
@Path("/transporteur")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransporteurResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransporteurResource.class);

    @Autowired
    private TransporteurService transporteurService;

    @Autowired
    public TransporteurResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.transporteurService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.transporteurService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.transporteurService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(TransporteurBean object) {
        try {

            this.transporteurService.createOrUpdate( object);
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
            return Response.ok(columnDefService.getByViewName(ViewName.TRANSPORTEUR)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.transporteurService.getAll()).build();
    }

}
