package ma.salamgaz.gwic.service;

import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;

public interface RefService<T> {

    Long createOrUpdate(T object);

    T getDetails(Long id);

    GridListBean<T> list(PageDataBean paginateData);
}
