package ma.salamgaz.tawassol.repository.custom;

import ma.salamgaz.tawassol.common.model.bean.BanqueListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;

import java.util.Map;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueRepositoryCustom {
    BanqueListBean findBanques(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap);
}
