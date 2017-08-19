package com.ayouris.tawassol.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;

import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.service.impl.DateServiceImpl;

@Component("dateResource")
@Path("/date")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class DateResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateResource.class);

	@Autowired
	private DateServiceImpl dateServiceImpl;

	@Autowired
	public DateResource() {

	}
	
	@GET
	@Path("/years/")
	@JsonView(EditionView.class)
	public Response getAllYears() {
		return Response.ok(this.dateServiceImpl.getAllYears()).build();
	}
	
	@GET
	@Path("/months/")
	@JsonView(EditionView.class)
	public Response getAllMonths() {
		return Response.ok(this.dateServiceImpl.getAllMonths()).build();
	}
}
