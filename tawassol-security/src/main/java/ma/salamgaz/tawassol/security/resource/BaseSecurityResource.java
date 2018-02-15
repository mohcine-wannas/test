package ma.salamgaz.tawassol.security.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

import org.springframework.util.CollectionUtils;

import ma.salamgaz.tawassol.common.model.bean.FieldBean;
import ma.salamgaz.tawassol.common.model.bean.SearchBean;
import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;
import ma.salamgaz.tawassol.common.service.BaseService;
import ma.salamgaz.tawassol.security.exception.ServiceSecurityException;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public abstract class BaseSecurityResource<T extends BaseEntity> {
    
	private static final String RELATIONS_SEPARATOR = ",";

    public static final String ATTRIBUTE_ENTITIES_FIELDNAME = "relations";
    
    private BaseService<T> service;
    
    public BaseSecurityResource(BaseService<T> service) {
        setService(service);
    }
    
    protected final void setService(BaseService<T> service) {
        this.service = service;
    }

    protected final BaseService<T> getService() {
        return service;
    }
   
    protected final String[] processAttributeEntities(List<String> attributeEntities) {
        List<String> copie = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(attributeEntities)) {
            for (Iterator<String> it = attributeEntities.iterator(); it.hasNext();) {
                String s = it.next();
                copie.addAll(Arrays.asList(s.split(RELATIONS_SEPARATOR)));
            }
        }
        return copie.toArray(new String[copie.size()]);
    }

    protected final String[] processAttributeEntities(SearchBean searchData) {
        List<String> copie = new ArrayList<String>();
        if (searchData != null && searchData.getFields() != null) {
            for (FieldBean field : searchData.getFields()) {
                copie.add(field.getField());
            }
        }
        return copie.toArray(new String[copie.size()]);
    }

    @GET
    public Response findAll() {
        try {
            List<T> items = service.findAll();
            return Response.ok(items).build();

        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        try {
            T retour = service.findOne(id);
            return Response.ok(retour).build();

        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @POST
    public Response create(T object) {
        try {
            T retour = service.save(object);
            return Response.ok(retour).build();

        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @PUT
    @Path("{id}")
    public Response update(T object) {
        try {
            T retour = service.update(object);
            return Response.ok(retour).build();

        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
        	service.delete(id);
            return Response.ok().build();

        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

}
