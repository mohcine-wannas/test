package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonVracBean;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraisonVrac;

public interface LigneOLVRACService extends GenericService<LigneOrdreLivraisonVrac, Long> {

	void validateLigneOLVRACBean(LigneOrdreLivraisonVracBean ligneOrdreLivraisonVracBean);
}

