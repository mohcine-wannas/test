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
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteClientBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.PrixVenteClientService;
import com.ayouris.tawassol.service.ServiceException;

@Component("prixVenteClientResource")
@Path("/prix-vente-client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class PrixVenteClientResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrixVenteClientResource.class);

	@Autowired
	private PrixVenteClientService prixVenteClientService;

	@Autowired
	public PrixVenteClientResource() {

	}

	@Autowired
	private ColumnDefService columnDefService;

	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(ListView.class)
	public Response list(PageDataBean pageDataBean) {
		return Response.ok(this.prixVenteClientService.list(pageDataBean)).build();
	}

	@GET
	@Path("{id}")
	@JsonView(EditionView.class)
	public Response getDetails(@PathParam("id") Long id) {
		return Response.ok(this.prixVenteClientService.getDetails(id)).build();
	}

	@PUT
	@JsonView(EditionView.class)
	public Response createOrUpdate(PrixVenteClientBean object) {
		try {
			return Response.ok(this.prixVenteClientService.create(object)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new CustomWebApplicationException(e);
		}
	}

	@GET
	@Path("columnDef")
	public Response getColumnDef() {
		try {
			return Response.ok(columnDefService.getByViewName(ViewName.PRIX_VENTE_CLIENT)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new WebApplicationException(e.getError().getMessage());
		}
	}

	@GET
	@Path("/")
	@JsonView(ListView.class)
	public Response getAll() {
		return Response.ok(this.prixVenteClientService.getAll()).build();
	}

	@PUT
	@Path("/overlap")
	@JsonView(EditionView.class)
	public Response overlap(PrixVenteClientBean object) {
		return Response.ok(this.prixVenteClientService.overlap(object)).build();
	}

	@POST
	@Path("/controle")
	public Response controle(PrixVenteClientBean object) {
		return Response.ok(this.prixVenteClientService.controle(object)).build();
	}

}
