package com.ayouris.tawassol.security.service;

import com.ayouris.tawassol.common.enums.ContactType;
import com.ayouris.tawassol.security.aspect.CheckResourceOperations;

public interface PermissionEvaluator {

    boolean hasOrganization(ContactType organizationType);

    boolean hasAnyOrganization(ContactType[] organizationsType);

    boolean hasResource(String resource);

    boolean hasAnyResource(String[] resources);

    boolean hasResourceAndAnyOperation(CheckResourceOperations checkedResourceOperations);

    boolean hasRole(String role);

    boolean hasAnyRole(String[] roles);

}
