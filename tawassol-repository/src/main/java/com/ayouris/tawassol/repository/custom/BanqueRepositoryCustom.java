package com.ayouris.tawassol.repository.custom;

import com.ayouris.tawassol.common.model.bean.BanqueListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;

import java.util.Map;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueRepositoryCustom {
    BanqueListBean findBanques(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap);
}
