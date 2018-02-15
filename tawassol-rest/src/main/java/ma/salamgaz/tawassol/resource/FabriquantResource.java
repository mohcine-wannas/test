package ma.salamgaz.tawassol.resource;

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

import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.exception.CustomWebApplicationException;
import ma.salamgaz.tawassol.common.model.bean.FabriquantBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.FabriquantService;
import ma.salamgaz.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Component("fabriquantResource")
@Path("/fabriquant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FabriquantResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FabriquantResource.class);

    @Autowired
    private FabriquantService fabriquantService;

    @Autowired
    public FabriquantResource(){

    }

    @Autowired
    private ColumnDefService columnDefService;


    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.fabriquantService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.fabriquantService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.fabriquantService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FabriquantBean object) {
        try {

            this.fabriquantService.createOrUpdate( object);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new CustomWebApplicationException(e);
        }
    }

    @GET
    @Path("columnDef")
    public Response getColumnDef() {
        try {
            return Response.ok(columnDefService.getByViewName(ViewName.FABRIQUANT)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.fabriquantService.getAll()).build();
    }

}
