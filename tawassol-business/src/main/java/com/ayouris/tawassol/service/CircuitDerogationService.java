package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;

import java.util.List;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
public interface CircuitDerogationService extends GenericService<CircuitDerogation, Long>,
        RefService<CircuitDerogationBean> {
    List<CircuitDerogationBean> getAll();
}
