package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonBean;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;

public interface LigneOrdreLivraisonService extends GenericService<LigneOrdreLivraison, Long> {

	void validateLigneOrdreLivraisonBean(LigneOrdreLivraisonBean ligneOrdreLivraison);
}

