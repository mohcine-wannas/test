package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.entity.Ventilation;

public interface VentilationService extends GenericService<Ventilation,Long>{

	List<Ventilation> ventilationPeriodique(PieceReglement pieceReglement);

	void ventilationParEnlevement(Long id);
	
}
