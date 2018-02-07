package ma.salamgaz.tawassol.security.service;

import ma.salamgaz.tawassol.common.enums.ContactType;
import ma.salamgaz.tawassol.security.aspect.CheckResourceOperations;

public interface PermissionEvaluator {

    boolean hasOrganization(ContactType organizationType);

    boolean hasAnyOrganization(ContactType[] organizationsType);

    boolean hasResource(String resource);

    boolean hasAnyResource(String[] resources);

    boolean hasResourceAndAnyOperation(CheckResourceOperations checkedResourceOperations);

    boolean hasRole(String role);

    boolean hasAnyRole(String[] roles);

}
