package com.ayouris.tawassol.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.Profil;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.service.ProfilService;
import com.ayouris.tawassol.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
@Component("profil")
@Path("/profil")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfilResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(Profil.class);

    @Autowired
    private ProfilService profilService;

    @Autowired
    public ProfilResource(){

    }

    @PUT
    @JsonView(EditionView.class)
    public Response createOrUpdate(ProfilBean object) {
        try {
            this.profilService.create(object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.profilService.getDetails(id)).build();
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.profilService.getAll()).build();
    }
}
