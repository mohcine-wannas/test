package ma.salamgaz.tawassol.common.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    T update(T entity);

    T findOne(Long id);

    //T findOne(Long id, String... attributeEntities);

    List<T> findAll();

    //List<T> findAll(String... attributeEntities);

    void delete(Long id);

    //Iterable<T> searchByFilter(String searchText, String... attributeEntities);

}
