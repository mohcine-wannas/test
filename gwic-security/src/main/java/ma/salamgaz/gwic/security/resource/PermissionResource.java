package ma.salamgaz.gwic.security.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface PermissionResource {

    @GET
    @Path("/organizationType/{organizationType}")
    public Response getPermisionByOrganizationType(@PathParam("organizationType") String organizationType);

}
