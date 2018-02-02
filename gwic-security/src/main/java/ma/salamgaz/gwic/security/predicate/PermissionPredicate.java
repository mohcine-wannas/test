package ma.salamgaz.gwic.security.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import ma.salamgaz.gwic.admin.model.entity.QPermissionRight;
import ma.salamgaz.gwic.common.repository.predicate.BasePredicate;

public class PermissionPredicate extends BasePredicate {

	@Override
	public Predicate idEquals(Long id) {
		return QPermissionRight.permissionRight.id.eq(id);
	}

	@Override
	protected BooleanExpression getBasePredicate(String s) {
		return QPermissionRight.permissionRight.resource.name.equalsIgnoreCase(s);
	}

}
