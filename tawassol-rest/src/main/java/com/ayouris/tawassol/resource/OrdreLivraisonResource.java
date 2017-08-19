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
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.OrdreLivraisonService;
import com.ayouris.tawassol.service.ServiceException;

@Component("ordreLivraisonResource")
@Path("/ordre-livraison")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class OrdreLivraisonResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrdreLivraisonResource.class);

	@Autowired
	private OrdreLivraisonService ordreLivraisonService;


	@Autowired
	public OrdreLivraisonResource() {

	}

	@Autowired
	private ColumnDefService columnDefService;

	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(ListView.class)
	public Response list(PageDataBean pageDataBean) {
		return Response.ok(this.ordreLivraisonService.list(pageDataBean)).build();
	}
	
	@POST
	@Path("vrac/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(ListView.class)
	public Response listOLVrac(PageDataBean pageDataBean) {
		return Response.ok(this.ordreLivraisonService.list(pageDataBean)).build();
	}


	@GET
	@Path("{id}")
	@JsonView(EditionView.class)
	public Response getDetails(@PathParam("id") Long id) {
		return Response.ok(this.ordreLivraisonService.getDetails(id)).build();
	}

	@PUT
	@Path("{id}/cancel")
	@JsonView(EditionView.class)
	public Response annuler(@PathParam("id") Long id, String motif) {
		this.ordreLivraisonService.annuler(id,motif);
		return Response.ok().build();
	}
	
	@PUT
	@JsonView(EditionView.class)
	public Response createOrUpdate(OrdreLivraisonBean object) {
		try {
			return Response.ok(this.ordreLivraisonService.create(object)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new CustomWebApplicationException(e);
		}
	}

	@GET
	@Path("columnDef")
	public Response getColumnDef() {
		try {
			return Response.ok(columnDefService.getByViewName(ViewName.ORDRE_LIVRAISON)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new WebApplicationException(e.getError().getMessage());
		}
	}
	@GET
	@Path("vrac/columnDef")
	public Response getColumnDefVrac() {
		try {
			return Response.ok(columnDefService.getByViewName(ViewName.ORDRE_LIVRAISON_VRAC)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new WebApplicationException(e.getError().getMessage());
		}
	}

	@GET
	@Path("/")
	@JsonView(ListView.class)
	public Response getAll() {
		return Response.ok(this.ordreLivraisonService.getAll()).build();
	}

}
