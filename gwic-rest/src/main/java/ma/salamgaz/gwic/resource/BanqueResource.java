package ma.salamgaz.gwic.resource;

import ma.salamgaz.gwic.admin.model.enums.ViewName;
import ma.salamgaz.gwic.common.exception.CustomWebApplicationException;
import ma.salamgaz.gwic.common.model.bean.BanqueBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.service.BanqueService;
import ma.salamgaz.gwic.service.ColumnDefService;
import ma.salamgaz.gwic.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by YounesM on 05/05/2017.
 */

@Component("banqueResource")
@Path("/banque")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BanqueResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BanqueResource.class);

    @Autowired
    private BanqueService banqueService;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    public BanqueResource(){

    }

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list(PageDataBean pageDataBean){
        return Response.ok(this.banqueService.list(pageDataBean)).build();
    }

    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(this.banqueService.getDetails(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        this.banqueService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(BanqueBean object) {
        try {

            this.banqueService.createOrUpdate( object);
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
            return Response.ok(columnDefService.getByViewName(ViewName.BANQUE)).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

}
