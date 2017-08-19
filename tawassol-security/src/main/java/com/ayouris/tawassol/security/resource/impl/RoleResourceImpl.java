package com.ayouris.tawassol.security.resource.impl;

import static com.ayouris.tawassol.common.constant.ApplicationConstants.DATE_TIMESTAMP_FORMAT;
import static com.ayouris.tawassol.common.constant.ApplicationConstants.HTTP_DATA_REFERENCE;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;

import com.ayouris.tawassol.admin.model.beans.ResourceBean;
import com.ayouris.tawassol.admin.model.beans.ResultListBean;
import com.ayouris.tawassol.admin.model.beans.RoleBean;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.common.model.bean.DataCriteriaBean;
import com.ayouris.tawassol.common.view.RequestView.CreationRoleView;
import com.ayouris.tawassol.common.view.RequestView.ListRoleView;
import com.ayouris.tawassol.security.exception.ServiceSecurityException;
import com.ayouris.tawassol.security.resource.BaseSecurityResource;
import com.ayouris.tawassol.security.resource.RoleResource;
import com.ayouris.tawassol.security.service.RoleService;
import com.ayouris.tawassol.security.utils.csv.RoleCsvTransformer;

@Component("roleResource")
@Path("/roles")
public class RoleResourceImpl extends BaseSecurityResource<Role> implements RoleResource {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceImpl.class);
    
    @Autowired
    protected RoleCsvTransformer csvTransformer;
    
    @Autowired
    public RoleResourceImpl(RoleService service) {
		super(service);
	}

    @POST
    @Path("list")
    @JsonView(ListRoleView.class)
    public final Response getRoles(DataCriteriaBean criteria) {

        ResultListBean<RoleBean> roles = ((RoleService) getService()).getRoles(criteria);
        if (CollectionUtils.isEmpty(roles.getItems())) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.ok(roles).build();
    }

    @GET
    @Path("export")
    @Produces(MediaType.APPLICATION_OCTET_STREAM + "; charset=utf-8")
    public Response exportCSV() {

        ResultListBean<RoleBean> roles = ((RoleService) getService()).getRoles(new DataCriteriaBean());
        if (CollectionUtils.isNotEmpty(roles.getItems())) {
            String fileName = "export-" + new SimpleDateFormat(DATE_TIMESTAMP_FORMAT).format(new Date()) + ".csv";
            return new ResponseBuilderImpl().entity(csvTransformer.build(roles.getItems()))
                    .header(HTTP_DATA_REFERENCE, fileName).build();
        } else {
            return Response.status(Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("{id}/resources/list")
    public final Response getRoleResources(@PathParam("id") Long roleId, DataCriteriaBean criteria) {

        ResultListBean<ResourceBean> resources = ((RoleService) getService()).getRoleResources(roleId, criteria);
        if (CollectionUtils.isEmpty(resources.getItems())) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.ok(resources).build();
    }

    @POST
    @Path("create")
    @JsonView(CreationRoleView.class)
    public final Response createRole(RoleBean role) {
        try {
            RoleBean createdRole = ((RoleService) getService()).createRole(role);
            return Response.ok(createdRole).status(Status.CREATED).build();
        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @PUT
    @Path("{id}")
    public final Response updateRole(@PathParam("id") Long roleId, RoleBean role) {
        try {
        	((RoleService) getService()).updateRole(role);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ServiceSecurityException e) {
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

}
