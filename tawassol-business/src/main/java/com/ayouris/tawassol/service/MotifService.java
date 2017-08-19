package com.ayouris.tawassol.service;


import com.ayouris.tawassol.common.model.bean.MotifBean;
import com.ayouris.tawassol.common.model.entity.Motif;

import java.util.List;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */

public interface MotifService extends GenericService<Motif, Long> {

    Long create(MotifBean object);
    List<MotifBean> getAll();
}
