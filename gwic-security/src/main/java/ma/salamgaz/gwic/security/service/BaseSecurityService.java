package ma.salamgaz.gwic.security.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author MHaddar
 * 
 */
public interface BaseSecurityService<T extends Serializable> {

    T save(T entity);

    T update(T entity);

    T findOne(Long id);

    List<T> findAll();

    T delete(Long id);

}
