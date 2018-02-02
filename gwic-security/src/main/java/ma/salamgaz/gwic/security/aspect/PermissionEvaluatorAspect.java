package ma.salamgaz.gwic.security.aspect;

import javax.ws.rs.WebApplicationException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.security.service.PermissionEvaluator;

@Component
@Aspect
@Order(1)
public class PermissionEvaluatorAspect {

    @Autowired
    private PermissionEvaluator permissionEvaluator;

    @Around("@annotation(checkOrganization)")
    @Order(3)
    public Object processPermissionOrganization(ProceedingJoinPoint jointPoint, CheckOrganization checkOrganization)
            throws Throwable {

        if (!permissionEvaluator.hasAnyOrganization(checkOrganization.name())) {

            throw new WebApplicationException(ErrorMessageType.UNAUTHORIZED_ORGANIZATION.getMessagePattern());
        }

        return jointPoint.proceed();
    }

    @Around("@annotation(checkResources)")
    @Order(4)
    public Object processPermissionResources(ProceedingJoinPoint jointPoint, CheckResources checkResources)
            throws Throwable {

        if (!permissionEvaluator.hasAnyResource(checkResources.name())) {

            throw new WebApplicationException(ErrorMessageType.UNAUTHORIZED_ORGANIZATION.getMessagePattern());
        }
        return jointPoint.proceed();
    }

    @Around("@annotation(checkResourceOperations)")
    @Order(5)
    public Object processPermissionResourcesOperation(ProceedingJoinPoint jointPoint,
            CheckResourceOperations checkResourceOperations) throws Throwable {

        if (!permissionEvaluator.hasResourceAndAnyOperation(checkResourceOperations)) {

            throw new WebApplicationException(ErrorMessageType.UNAUTHORIZED_ORGANIZATION.getMessagePattern());
        }
        return jointPoint.proceed();
    }

}
