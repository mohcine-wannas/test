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
import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views.EntryControlView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.FileAttenteService;
import com.ayouris.tawassol.service.ServiceException;

@Component("fileAttenteResource")
@Path("/file-attente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class FileAttenteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileAttenteResource.class);

    @Autowired
    private FileAttenteService fileAttenteService;

    @Autowired
    public FileAttenteResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(ListView.class)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.fileAttenteService.list(pageDataBean)).build();
    }
    
    

    @GET
    @Path("{id}")
    @JsonView(EditionView.class)
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.fileAttenteService.getDetails(id)).build();
    }
    
    @POST
    @Path("/{id}/controle-camion-conformities")
    public Response getControlCamionConformity(FileAttenteBean object) {
    	this.fileAttenteService.setControleCamionConformity(object);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}/controle-camion-conformities")
    @JsonView(EntryControlView.class)
    public Response getControlCamionConformity(@PathParam("id") Long id) {
    		return Response.ok(fileAttenteService.getControleCamionConformities(id)).build();
    }


    @POST
    @JsonView(EditionView.class)
    public Response create(FileAttenteBean object) {
        try {
            this.fileAttenteService.create(object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }
    
    @PUT
    @JsonView(EditionView.class)
    public Response createOrUpdate(FileAttenteBean object) {
    	try {
    		
    		return Response.ok(this.fileAttenteService.create(object)).build();
    	} catch (ServiceException e) {
    		LOGGER.error(e.getMessage());
    		throw new CustomWebApplicationException(e);
    	}
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.FILE_ATTENTE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    @JsonView(ListView.class)
    public Response getAll() {
        return Response.ok(this.fileAttenteService.getAll()).build();
    }
    
    @GET
    @Path("entree")
    @JsonView(ListView.class)
    public Response getALLFileAttenteEntree() {
        return Response.ok(this.fileAttenteService.getFileAttenteEntree()).build();
    }

}
