package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.TypePieceReglementBean;
import com.ayouris.tawassol.common.model.entity.TypePieceReglement;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.TypePieceReglementRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface TypePieceReglementRepository extends  CommonRepository<TypePieceReglement>,TypePieceReglementRepositoryCustom, GridRepositoryCustom<TypePieceReglementBean> {

	
}
