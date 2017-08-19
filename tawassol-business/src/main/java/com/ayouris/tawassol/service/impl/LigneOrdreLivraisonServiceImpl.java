package com.ayouris.tawassol.service.impl;

import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonBean;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;
import com.ayouris.tawassol.service.LigneOrdreLivraisonService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class LigneOrdreLivraisonServiceImpl extends GenericServiceImpl<LigneOrdreLivraison, Long>
		implements LigneOrdreLivraisonService {

	
	private void validateRequiredValues(LigneOrdreLivraisonBean ligneOrdreLivraisonBean) {
		if (ligneOrdreLivraisonBean.getQteCommande() == null 
				|| ligneOrdreLivraisonBean.getQteDefectueuse() == null
				|| ligneOrdreLivraisonBean.getProduit() == null) {

			throw new ServiceException(ErrorMessageType.LIGNE_ORDRE_LIVRAISON_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public void validateLigneOrdreLivraisonBean(LigneOrdreLivraisonBean ordreLivraisonBean) {
		validateRequiredValues(ordreLivraisonBean);
		validatePositiveValues(ordreLivraisonBean);
	}

	private void validatePositiveValues(LigneOrdreLivraisonBean ligneOrdreLivraisonBean) {
		if (ligneOrdreLivraisonBean.getQteCommande() <= 0) {
			throw new ServiceException(ErrorMessageType.LIGNE_ORDRE_LIVRAISON_QTE_COMMANDE_POSITIVE);
		}
		if (ligneOrdreLivraisonBean.getQteDefectueuse() < 0) {
			throw new ServiceException(ErrorMessageType.LIGNE_ORDRE_LIVRAISON_QTE_DEFECTIEUSE_POSITIVE);
		}
	}
}
