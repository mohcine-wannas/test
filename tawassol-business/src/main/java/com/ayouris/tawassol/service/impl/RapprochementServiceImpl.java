package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.LigneFileAttenteBean;
import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;

@Service
public class RapprochementServiceImpl {

	private static final String N_CAMION = "N° Camion";
	private static final String TYPE_BD = "Type BD";
	private static final String QTE_RÉCLAMEE = "Qté réclamée";
	private static final String QTE_COMMANDEE = "Qté commandée";
	private static final String PRODUIT = "Produit";

	public List<String> doRapprochement(OrdreLivraisonBean ordreLivraisonBean, FileAttenteBean fileAttenteBean) {
		List<String> infoRapproch = new ArrayList<String>();
		if (!(fileAttenteBean.getCamion().getMatricule().equals(ordreLivraisonBean.getCamion().getMatricule()))) {
			infoRapproch.add(N_CAMION);
		}

		if (ordreLivraisonBean.getLigneOrdreLivraison().size() != fileAttenteBean.getLigneFileAttente().size()) {
			infoRapproch.add(PRODUIT);
		} else {

			for (LigneFileAttenteBean item : fileAttenteBean.getLigneFileAttente()) {
				for (LigneOrdreLivraisonBean ordre : ordreLivraisonBean.getLigneOrdreLivraison()) {
					if (item.getProduit().getId().equals(ordre.getProduit().getId())
							&& !(item.getQteCommande().equals(ordre.getQteCommande()))
							&& !infoRapproch.contains(QTE_COMMANDEE)) {
						infoRapproch.add(QTE_COMMANDEE);
					}
					if (item.getProduit().getId().equals(ordre.getProduit().getId())
							&& !(item.getQteDefectueuse().equals(ordre.getQteDefectueuse()))
							&& !infoRapproch.contains(QTE_RÉCLAMEE)) {
						infoRapproch.add(QTE_RÉCLAMEE);
					}
					if (item.getProduit().getId().equals(ordre.getProduit().getId())
							&& !(item.getProduit().getId().equals(ordre.getProduit().getId()))
							&& !infoRapproch.contains(TYPE_BD)) {
						infoRapproch.add(TYPE_BD);
					}
				}
			}
		}
		return infoRapproch;
	}
}
