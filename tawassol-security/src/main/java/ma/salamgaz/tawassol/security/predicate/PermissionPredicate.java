package ma.salamgaz.tawassol.security.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import ma.salamgaz.tawassol.admin.model.entity.QPermissionRight;
import ma.salamgaz.tawassol.common.repository.predicate.BasePredicate;

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
