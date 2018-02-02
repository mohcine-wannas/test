package ma.salamgaz.tawassol.common.repository.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class PersistenceUtils {

    @SuppressWarnings({ "unchecked" })
    public static <T> T unproxifyEntity(final T proxied) {
        T entity = proxied;
        if (entity != null && entity instanceof HibernateProxy) {
            Hibernate.initialize(entity);
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }
}
