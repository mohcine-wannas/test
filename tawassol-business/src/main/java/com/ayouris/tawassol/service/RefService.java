package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;

public interface RefService<T> {

    Long createOrUpdate(T object);

    T getDetails(Long id);

    GridListBean<T> list(PageDataBean paginateData);
}
