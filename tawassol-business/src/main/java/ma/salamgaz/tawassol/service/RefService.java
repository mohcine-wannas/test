package ma.salamgaz.tawassol.service;

import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;

public interface RefService<T> {

    Long createOrUpdate(T object);

    T getDetails(Long id);

    GridListBean<T> list(PageDataBean paginateData);
}
