package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.BanqueBean;
import com.ayouris.tawassol.common.model.bean.BanqueListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Banque;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueService extends GenericService<Banque, Long> {

    Long createOrUpdate(BanqueBean object);

    BanqueBean getDetails(Long id);

    BanqueListBean list(PageDataBean paginateData);

}
