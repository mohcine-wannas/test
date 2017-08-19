package com.ayouris.tawassol.service;


import com.ayouris.tawassol.common.model.bean.DelegationBean;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;
import com.ayouris.tawassol.common.model.entity.Delegation;

import java.util.List;

/**
 * Created by Issmahane EL BAZ on 13/07/2017.
 */
public interface DelegationService extends GenericService<Delegation, Long>,
        RefService<DelegationBean> {
    List<DelegationBean> getAll();
}
