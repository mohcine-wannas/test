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

import com.ayouris.tawassol.admin.model.beans.UserBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.UsersService;

/**
 * Created by Issmahane EL BAZ on 17/07/2017.
 */
@Component("usersResource")
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CircuitDerogationResource.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    public UsersResource(){

    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.usersService.getDetails(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(EditionView.class)
    public Response create(UserBean object) {
        try {
            this.usersService.createOrUpdate(object);
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
        return Response.ok(this.usersService.list(pageDataBean)).build();
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.usersService.getAll()).build();
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.USERS)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.usersService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/generate-password")
    public Response generatePassowrd(@PathParam("id") Long id) {
        try {
            return Response.ok(usersService.generatePassword(id)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

}
