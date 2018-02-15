package ma.salamgaz.tawassol.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.salamgaz.tawassol.service.GroupService;

/**
 * 
 * @author m.wannas
 *
 */

@Component("groupResource")
@Path("/groupe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    //private static final Logger LOGGER = LoggerFactory.getLogger(GroupResource.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    public GroupResource(){

    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(this.groupService.getAll()).build();
    }

}
