package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.PieceReglementBean;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PieceReglementRepositoryCustom;

/**
 * Created by Issmahane EL BAZ on 20/07/2017.
 */
public interface PieceReglementRepository extends CommonRepository<PieceReglement> ,
        PieceReglementRepositoryCustom, GridRepositoryCustom<PieceReglementBean> {
}
