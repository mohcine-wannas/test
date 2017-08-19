package com.ayouris.tawassol.repository;


import com.ayouris.tawassol.common.model.bean.MotifBean;
import com.ayouris.tawassol.common.model.entity.Motif;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.MotifRepositoryCustom;

/**
 * Created by Issmahane EL BAZ on 22/06/2017.
 */
public interface MotifRepository extends CommonRepository<Motif>, MotifRepositoryCustom,
        GridRepositoryCustom<MotifBean> {

}