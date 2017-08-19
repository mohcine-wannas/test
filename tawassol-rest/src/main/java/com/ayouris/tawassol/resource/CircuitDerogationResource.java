package com.ayouris.tawassol.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.CircuitDerogationService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
@Component("circuitDerogationResource")
@Path("/circuit-derogation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CircuitDerogationResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CircuitDerogationResource.class);

    @Autowired
    private CircuitDerogationService circuitDerogationService;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    public CircuitDerogationResource(){

    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.circuitDerogationService.getDetails(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(EditionView.class)
    public Response create(CircuitDerogationBean object) {
        try {

            this.circuitDerogationService.createOrUpdate( object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }
    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.circuitDerogationService.list(pageDataBean)).build();
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.circuitDerogationService.getAll()).build();
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.CIRCUIT_DEROGATION)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.circuitDerogationService.delete(id);
        return Response.ok().build();
    }

}

