package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.LigneFileAttenteBean;
import com.ayouris.tawassol.common.model.entity.LigneFileAttente;

public interface LigneFileAttenteService extends GenericService<LigneFileAttente, Long> {

	void validateLigneFileAttenteBean(LigneFileAttenteBean ligneFileAttente);
}

