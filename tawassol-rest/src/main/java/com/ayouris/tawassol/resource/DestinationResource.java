package com.ayouris.tawassol.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.service.DestinationService;

/**
 * 
 * @author m.wannas
 *
 */

@Component("destinationResource")
@Path("/destination")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DestinationResource {

    @Autowired
    private DestinationService destinationDervice;

    @Autowired
    public DestinationResource(){

    }
   
    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.destinationDervice.getAll()).build();
    }

}
