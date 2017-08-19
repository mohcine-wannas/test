package com.ayouris.tawassol.service.impl;

import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonVracBean;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraisonVrac;
import com.ayouris.tawassol.service.LigneOLVRACService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class LigneOLVRACServiceImpl extends GenericServiceImpl<LigneOrdreLivraisonVrac, Long>
		implements LigneOLVRACService {
	
	private void validateRequiredValues(LigneOrdreLivraisonVracBean ligneOrdreLivraisonVracBean) {
		if (ligneOrdreLivraisonVracBean.getQteCommande() == null 
				|| ligneOrdreLivraisonVracBean.getProduit() == null) {
			throw new ServiceException(ErrorMessageType.LIGNE_ORDRE_LIVRAISON_VRAC_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public void validateLigneOLVRACBean(LigneOrdreLivraisonVracBean ligneOrdreLivraisonVracBean) {
		validateRequiredValues(ligneOrdreLivraisonVracBean);
		validatePositiveValues(ligneOrdreLivraisonVracBean);
	}

	private void validatePositiveValues(LigneOrdreLivraisonVracBean ligneOrdreLivraisonVracBean) {
		if (ligneOrdreLivraisonVracBean.getQteCommande() <= 0) {
			throw new ServiceException(ErrorMessageType.LIGNE_ORDRE_LIVRAISON_VRAC_QTE_COMMANDE_POSITIVE);
		}
	}
}
