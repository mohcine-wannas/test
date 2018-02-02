package ma.salamgaz.gwic.security.service;

import ma.salamgaz.gwic.common.enums.ContactType;
import ma.salamgaz.gwic.security.aspect.CheckResourceOperations;

public interface PermissionEvaluator {

    boolean hasOrganization(ContactType organizationType);

    boolean hasAnyOrganization(ContactType[] organizationsType);

    boolean hasResource(String resource);

    boolean hasAnyResource(String[] resources);

    boolean hasResourceAndAnyOperation(CheckResourceOperations checkedResourceOperations);

    boolean hasRole(String role);

    boolean hasAnyRole(String[] roles);

}
