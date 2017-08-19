package com.ayouris.tawassol.service.impl;

import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.bean.LigneFileAttenteBean;
import com.ayouris.tawassol.common.model.entity.LigneFileAttente;
import com.ayouris.tawassol.service.LigneFileAttenteService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class LigneFileAttenteServiceImpl extends GenericServiceImpl<LigneFileAttente, Long>
		implements LigneFileAttenteService {

	
	private void validateRequiredValues(LigneFileAttenteBean ligneFileAttenteBean) {
		if (ligneFileAttenteBean.getQteCommande() == null 
				|| ligneFileAttenteBean.getQteDefectueuse() == null
				|| ligneFileAttenteBean.getProduit() == null) {

			throw new ServiceException(ErrorMessageType.LIGNE_FILE_ATTENTE_MISSING_REQUIRED_VALUES);
		}

	}

	@Override
	public void validateLigneFileAttenteBean(LigneFileAttenteBean ligneFileAttente) {
		validateRequiredValues(ligneFileAttente);
		validatePositiveValues(ligneFileAttente);
	}

	private void validatePositiveValues(LigneFileAttenteBean ligneFileAttente) {
		if (ligneFileAttente.getQteCommande() <= 0) {
			throw new ServiceException(ErrorMessageType.LIGNE_FILE_ATTENTE_QTE_COMMANDE_POSITIVE);
		}
		if (ligneFileAttente.getQteDefectueuse() < 0) {
			throw new ServiceException(ErrorMessageType.LIGNE_FILE_ATTENTE_QTE_DEFECTIEUSE_POSITIVE);
		}

		
	}


}
