package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.Concessionnaire;
import com.ayouris.tawassol.common.model.entity.DefinitionPeriodes;
import com.ayouris.tawassol.common.model.entity.PieceReglement;

public interface DefinitionPeriodesService extends GenericService<DefinitionPeriodes, Long> {

	List<DefinitionPeriodes> getPeriodeByConcessionnaire(Long id);

	void updateDefinitionPeriodesConcessionnaire(PieceReglement pieceReglement);
}
