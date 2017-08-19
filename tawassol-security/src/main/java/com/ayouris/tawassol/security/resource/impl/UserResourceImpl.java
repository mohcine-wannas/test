package com.ayouris.tawassol.security.resource.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.UserDetailsBean;
import com.ayouris.tawassol.security.resource.BaseSecurityResource;
import com.ayouris.tawassol.security.resource.UserResource;
import com.ayouris.tawassol.security.service.UserService;
import com.ayouris.tawassol.security.utils.csv.UserCsvTransformer;

@Component("userResource")
@Path("/users")
public class UserResourceImpl extends BaseSecurityResource<User> implements UserResource {

    private final Logger LOGGER = LoggerFactory.getLogger(UserResourceImpl.class);

    @Autowired
    protected UserCsvTransformer csvTransformer;

    @Autowired
    public UserResourceImpl(UserService service) {
		super(service);
	}
    
    @GET
    @Path("list")
    public Response list(@BeanParam PageDataBean paginateData) {
        return Response.ok(((UserService) getService()).list(paginateData)).build();
    }

    /*
    @PUT
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response create(UserDetailsBean userDto) {
        DocumentBase db = service.save(userDto.getUsername(), userDto);
        if (db == null) {
            return Response.ok().build();
        } else {
            return new ResponseBuilderImpl().entity(db.getDocumentContent().getContent())
                    .type(db.getDocumentContent().getMimeType()).header("Data-reference", db.getFileName()).build();
        }
    }
    */


    @GET
    @Path("{id}")
    public Response getDetails(@PathParam("id") Long id) {
        return Response.ok(((UserService) getService()).getUserDetails(id)).build();
    }

    @POST
    @Path("listCsv")
    @Produces(MediaType.APPLICATION_OCTET_STREAM + "; charset=UTF-8")
    public Response listCsv(PageDataBean paginateData) {
        try {
            paginateData.setPageNumber(1);
            paginateData.setPageSize(0);
            List<UserDetailsBean> result = new ArrayList<>();
            result.addAll(((UserService) getService()).list(paginateData).getData());

            if (!result.isEmpty()) {
                return new ResponseBuilderImpl()
                        .entity(csvTransformer.build(result))
                        .type(MediaType.APPLICATION_OCTET_STREAM + "; charset=utf-8")
                        .header("Data-reference","users-" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".csv").build();
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage(), e);
            }
        }
        return null;
    }
    
    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(((UserService) getService()).getAll()).build();
    }
    
    @GET
    @Path("/bySite")
    public Response getBySite(@QueryParam("site") Long siteId) {
        return Response.ok(((UserService) getService()).getBySite(siteId)).build();
    }

}
