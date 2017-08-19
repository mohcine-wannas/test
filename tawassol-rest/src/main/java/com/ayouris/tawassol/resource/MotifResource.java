package com.ayouris.tawassol.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.bean.MotifBean;
import com.ayouris.tawassol.common.model.entity.Motif;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.service.MotifService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.TypeFileAttenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */
@Component("motif")
@Path("/motif")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MotifResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(Motif.class);

    @Autowired
    private MotifService motifService;

    @Autowired
    public MotifResource(){

    }

    @PUT
    @JsonView(EditionView.class)
    public Response createOrUpdate(MotifBean object) {
        try {
            this.motifService.create(object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.motifService.getAll()).build();
    }
}
