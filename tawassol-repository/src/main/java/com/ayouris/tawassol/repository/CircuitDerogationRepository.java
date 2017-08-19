package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.CircuitDerogationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
public interface CircuitDerogationRepository extends CommonRepository<CircuitDerogation>, CircuitDerogationRepositoryCustom,
        GridRepositoryCustom<CircuitDerogationBean> {
}
