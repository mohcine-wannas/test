package com.ayouris.tawassol.security.predicate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.repository.predicate.BasePredicate;
import com.ayouris.tawassol.common.model.bean.DataFilterBean;
import com.ayouris.tawassol.enums.Column;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class UserPredicate extends BasePredicate {

    public Predicate userEquals(User us) {
        if (us == null) {
            return null;
        }
        return (Predicate) QUser.user.eq(us);
    }

    @Override
    public Predicate idEquals(Long id) {
        if (id == null) {
            return null;
        }
        return (Predicate) QUser.user.id.eq(id);
    }

    @Override
    protected BooleanExpression getBasePredicate(String s) {
        throw new UnsupportedOperationException();
    }

    public BooleanExpression match(List<DataFilterBean> filters) {
        if (filters == null || filters.isEmpty()) {
            return null;
        }
        BooleanExpression be = null;
        for (DataFilterBean filter : filters) {
            BooleanExpression localBooleanExpression = null;
            if ("role.label".equals(filter.getColumn())) {
                localBooleanExpression = QUser.user.roles.any().name.containsIgnoreCase(filter.getValue());
            } else {
                switch (Column.valueOf(filter.getColumn())) {
                case firstname:
                    localBooleanExpression = QUser.user.firstname.startsWithIgnoreCase(filter.getValue());
                    break;
                case lastname:
                    localBooleanExpression = QUser.user.lastname.startsWithIgnoreCase(filter.getValue());
                    break;
                case organizationname:
                    localBooleanExpression = QUser.user.organization.name.containsIgnoreCase(filter.getValue());
                    break;
                case status:
                    localBooleanExpression = QUser.user.enabled.eq("True".equalsIgnoreCase(filter.getValue()));
                    break;
                case username:
                    localBooleanExpression = QUser.user.username.startsWithIgnoreCase(filter.getValue());
                    break;
                default:
                    break;

                }
            }
            if (be == null) {
                be = localBooleanExpression;
            } else {
                be = be.and(localBooleanExpression);
            }
        }
        return be;
    }

    public Predicate usernameEquals(String uName) {
        return QUser.user.username.equalsIgnoreCase(uName);
    }

    public Predicate userRole(Long roleId) {
        QUser user = QUser.user;
        return user.roles.any().id.eq(roleId);
    }
}
