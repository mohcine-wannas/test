package com.ayouris.tawassol.security.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import com.ayouris.tawassol.admin.model.entity.PermissionRight;
import com.ayouris.tawassol.admin.model.entity.QPermissionRight;
import com.ayouris.tawassol.common.enums.ContactType;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.security.aspect.CheckOrganization;
import com.ayouris.tawassol.security.exception.ServiceSecurityException;
import com.ayouris.tawassol.security.model.OrganizationPermissonsResponse;
import com.ayouris.tawassol.security.model.PermissionResponseUtils;
import com.ayouris.tawassol.security.repository.PermissionRightRepository;
import com.ayouris.tawassol.security.service.PermissionRightService;

@Transactional
@Service("permissionRightService")
public class PermissionRightServiceImpl implements PermissionRightService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionRightServiceImpl.class);

    @Autowired
    private PermissionRightRepository permissionRepository;

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public List<PermissionRight> findAll() {
        try {
            return permissionRepository.findAll();

        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }

    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public PermissionRight save(PermissionRight permission) {
        try {

            return permissionRepository.saveAndFlush(permission);

        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public PermissionRight findOne(Long id) {
        try {
            QPermissionRight permission = QPermissionRight.permissionRight;
            Predicate pr = permission.id.eq(id);
            return permissionRepository.findOne(pr);
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public PermissionRight update(PermissionRight permission) {
        try {
            return permissionRepository.saveAndFlush(permission);

        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public void delete(Long id) {
        try {
            permissionRepository.delete(id);

        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    public List<OrganizationPermissonsResponse> getPermissionsTransformed() {
        List<PermissionRight> permissions = findAll();
        return PermissionResponseUtils.transformePermissions(permissions);
    }

}
