package com.ayouris.tawassol.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;
import com.ayouris.tawassol.common.model.bean.DelegationBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.DelegationService;
import com.ayouris.tawassol.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Issmahane EL BAZ on 13/07/2017.
 */
@Component("DelegationResource")
@Path("/delegation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DelegationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelegationResource.class);

    @Autowired
    private DelegationService delegationService;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    public DelegationResource(){
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.delegationService.getDetails(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(EditionView.class)
    public Response create(DelegationBean object) {
        try {

            this.delegationService.createOrUpdate(object);
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
        return Response.ok(this.delegationService.list(pageDataBean)).build();
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.delegationService.getAll()).build();
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.DELEGATION)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.delegationService.delete(id);
        return Response.ok().build();
    }

}
