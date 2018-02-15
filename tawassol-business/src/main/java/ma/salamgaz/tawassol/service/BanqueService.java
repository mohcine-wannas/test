package ma.salamgaz.tawassol.service;

import ma.salamgaz.tawassol.common.model.bean.BanqueBean;
import ma.salamgaz.tawassol.common.model.bean.BanqueListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.Banque;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueService extends GenericService<Banque, Long> {

    Long createOrUpdate(BanqueBean object);

    BanqueBean getDetails(Long id);

    BanqueListBean list(PageDataBean paginateData);

}
