package com.ayouris.tawassol.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.service.ConditionCommercialeService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("conditionCommercialeResource")
@Path("/conditionCommerciale")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConditionCommercialeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionCommercialeResource.class);

    @Autowired
    private ConditionCommercialeService conditionCommercialeService;

    @Autowired
    public ConditionCommercialeResource(){

    }

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.conditionCommercialeService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.conditionCommercialeService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.conditionCommercialeService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ConditionCommercialeBean object) {
        try {
            return Response.ok( this.conditionCommercialeService.createOrUpdate(object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("/")
    @JsonView(ListView.class)
    public Response getAll() {
        return Response.ok(this.conditionCommercialeService.getAll()).build();
    }

}
