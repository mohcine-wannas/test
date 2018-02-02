package ma.salamgaz.gwic.common.repository.predicate;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public abstract class BasePredicate {

    public abstract Predicate idEquals(Long id);

    protected abstract BooleanExpression getBasePredicate(String s);

    protected String normalize(final String s) {
        if (s == null) {
            return null;
        }
        String temp = StringUtils.stripAccents(s);
        if (s.equals(temp)) {
            return null;
        }
        return temp;
    }

    public Predicate search(final String query) {
        if (query == null) {
            return null;
        }
        BooleanExpression predicate = getBasePredicate(query);
        String unaccentedQuery = normalize(query);
        if (unaccentedQuery != null) {
            predicate = predicate.or(getBasePredicate(unaccentedQuery));
        }
        return predicate;
    }

}
