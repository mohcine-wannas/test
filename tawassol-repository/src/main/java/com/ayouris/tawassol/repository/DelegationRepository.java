package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.DelegationBean;
import com.ayouris.tawassol.common.model.entity.Delegation;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.DelegationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * Created by Issmahane EL BAZ on 14/07/2017.
 */
public interface DelegationRepository extends CommonRepository<Delegation>, DelegationRepositoryCustom,
        GridRepositoryCustom<DelegationBean> {
}
