package com.ayouris.tawassol.security.resource.impl;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.admin.model.entity.PermissionRight;

import com.ayouris.tawassol.security.exception.ServiceSecurityException;
import com.ayouris.tawassol.security.model.OrganizationPermissonsResponse;
import com.ayouris.tawassol.security.resource.BaseSecurityResource;
import com.ayouris.tawassol.security.resource.PermissionResource;
import com.ayouris.tawassol.security.service.PermissionRightService;

@Component("permissionResource")
@Path("/permissions")
public class PermissionResourceImpl extends BaseSecurityResource<PermissionRight> implements PermissionResource {

    private static final Logger logger = LoggerFactory.getLogger(PermissionResourceImpl.class);

    @Autowired
    public PermissionResourceImpl(PermissionRightService service) {
		super(service);
	}

    @Override
    public Response findAll() {
        try {

            List<OrganizationPermissonsResponse> permissions = ((PermissionRightService) getService()).getPermissionsTransformed();
            return Response.ok(permissions).build();

        } catch (ServiceSecurityException e) {
            logger.error(e.getMessage(), e);
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

    @Override
    public Response getPermisionByOrganizationType(String organizationType) {
        try {

            return Response.ok().build();

        } catch (ServiceSecurityException e) {
            logger.error(e.getMessage(), e);
            throw new WebApplicationException(e.getError().getMessage());
        }
    }

}
