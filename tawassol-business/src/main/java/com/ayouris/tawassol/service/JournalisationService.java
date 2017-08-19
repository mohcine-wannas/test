package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;

/**
 * 
 * @author m.wannas
 *
 */

public interface JournalisationService  {

	void logOrdreLivraison(OrdreLivraison newEntity, OrdreLivraison oldEntity, String motif);

	void logFileAttente(FileAttente oldEntity, FileAttente newEntity, String motif);

	void logAnnulation(BaseEntity entity, String motif);

}
