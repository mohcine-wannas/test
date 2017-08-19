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
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.EmplissageResultControlService;
import com.ayouris.tawassol.service.EmplissageService;
import com.ayouris.tawassol.service.ServiceException;

@Component("emplissageResource")
@Path("/emplissage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class EmplissageResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmplissageResource.class);

	@Autowired
	private EmplissageService emplissageService;
	
	@Autowired
	private EmplissageResultControlService emplissageResultControlService;

	@Autowired
	public EmplissageResource() {

	}

	@Autowired
	private ColumnDefService columnDefService;

	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(ListView.class)
	public Response list(PageDataBean pageDataBean) {
		return Response.ok(this.emplissageService.list(pageDataBean)).build();
	}

	@GET
	@Path("{id}")
	@JsonView(EditionView.class)
	public Response getDetails(@PathParam("id") Long id) {
		return Response.ok(this.emplissageService.getDetails(id)).build();
	}

	@PUT
	@JsonView(EditionView.class)
	public Response create(EmplissageBean object) {
		try {
			return Response.ok(this.emplissageService.create(object)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new CustomWebApplicationException(e);
		}
	}

	@GET
	@Path("columnDef")
	public Response getColumnDef() {
		try {
			return Response.ok(columnDefService.getByViewName(ViewName.EMPLISSAGE)).build();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			throw new WebApplicationException(e.getError().getMessage());
		}
	}

	@GET
	@Path("/")
	@JsonView(ListView.class)
	public Response getAll() {
		return Response.ok(this.emplissageService.getAll()).build();
	}

	@GET
	@Path("/controle/{id}/")
	@JsonView(EditionView.class)
	public Response getResultatsByEmplissageId(@PathParam("id") Long id) {
		return Response.ok(this.emplissageService.getControleEmplissage(id)).build();
	}
	
	@POST
	@Path("/controle/")
	@JsonView(EditionView.class)
	public Response updateEmplissageFromControl(EmplissageBean object) {
		try {

			return Response.ok(this.emplissageService.updateEmplissageFromControl(object)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
	}
	
	@GET
	@Path("/controle/ligne/")
	@JsonView(EditionView.class)
	public Response getAllControlLigne() {
		return Response.ok(this.emplissageResultControlService.getAllControlLigneBean()).build();
	}
	
	@GET
	@Path("/controle/colonne/")
	@JsonView(EditionView.class)
	public Response getAllControlColonne(Long id) {
		return Response.ok(this.emplissageResultControlService.getAllControlColonneBean()).build();
	}
}
