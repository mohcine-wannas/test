//package com.ayouris.tawassol.security.predicate;
//
//import org.springframework.stereotype.Component;
//
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
//
//import com.ayouris.tawassol.admin.model.beans.RoleBean;
//import com.ayouris.tawassol.admin.model.enums.OrganizationType;
//import com.ayouris.tawassol.admin.model.entity.QRole;
//import com.ayouris.tawassol.common.repository.predicate.BasePredicate;
//
//
//@Component
//public class RolePredicate extends BasePredicate {
//
//    @Override
//    public Predicate idEquals(Long id) {
//        return QRole.role.id.eq(id);
//    }
//
//    @Override
//    protected BooleanExpression getBasePredicate(String s) {
//        return QRole.role.name.equalsIgnoreCase(s);
//    }
//
//    public Predicate matchByOrganizationType(OrganizationType organizationType) {
//        QRole role = QRole.role;
//        return role.organizationType.eq(organizationType);
//    }
//
//    public Predicate matchByName(RoleBean role) {
//        QRole qRole = QRole.role;
//        BooleanExpression predicate = qRole.name.eq(role.getName());
//        if (role.getId() != null) {
//            predicate = predicate.and(qRole.id.ne(role.getId()));
//        }
//        return predicate;
//    }
//
//    public Predicate matchByOrganizationTypeRoleId(RoleBean role) {
//        QRole qRole = QRole.role;
//        return qRole.organizationType.eq(role.getOrganizationType()).and(qRole.id.eq(role.getId()));
//    }
//}
