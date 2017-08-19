package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.TypePieceReglementBean;
import com.ayouris.tawassol.common.model.entity.TypePieceReglement;

/**
 * 
 * @author m.wannas
 *
 */

public interface TypePieceReglementService extends GenericService<TypePieceReglement, Long>,
		RefService<TypePieceReglementBean> {

	List<TypePieceReglementBean> getAll();
}
