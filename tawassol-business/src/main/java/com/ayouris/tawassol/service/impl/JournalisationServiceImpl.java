package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.entity.Journalisation;
import com.ayouris.tawassol.common.model.entity.LigneFileAttente;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;
import com.ayouris.tawassol.common.model.entity.LogDetail;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.common.model.enums.TypeOperation;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.JournalisationService;

@Service
public class JournalisationServiceImpl  extends GenericServiceImpl<Journalisation, Long> implements  JournalisationService {

private static final String ORDRE_LIVRAISON = "OrdreLivraison";
private static final String LIGNE_ORDRE_LIVRAISON = "LigneOrdreLivraison";
private static final String BON_LIVRAISON = "BonLivraison";

@Override
public void logAnnulation(BaseEntity entity, String motif) {
	
	String type = null;
	if(entity instanceof OrdreLivraison) {
		type =	ORDRE_LIVRAISON;
	}else if(entity instanceof BonLivraison) {
		type =	BON_LIVRAISON;
	}
	
	if(StringUtils.isNotBlank(type)) {
		Journalisation log = getInstance(type, entity.getId(), TypeOperation.ANNULATION, motif);
		save(log);
	}
}	

	@Override
	public void logOrdreLivraison(OrdreLivraison oldEntity,OrdreLivraison newEntity,String motif) {
		
		if(!newEntity.getCamion().equals(oldEntity.getCamion())) {
			Journalisation log = getInstance( ORDRE_LIVRAISON, oldEntity.getId(), TypeOperation.RECTIFICATION, motif);
			buildLog(log, "camion", oldEntity.getCamion().getMatricule(), newEntity.getCamion().getMatricule());
			saveLog(log);
		}
		for(LigneOrdreLivraison ligneOldEntity : oldEntity.getLigneOrdreLivraison() ) {
			for(LigneOrdreLivraison ligneNewEntity : newEntity.getLigneOrdreLivraison() ) {
				if(ligneOldEntity.getId().equals(ligneNewEntity.getId())) {
					Long id = ligneNewEntity.getId();
					Journalisation log = getInstance( LIGNE_ORDRE_LIVRAISON, id, TypeOperation.RECTIFICATION, motif);
					if(!ligneOldEntity.getProduit().equals(ligneNewEntity.getProduit())) {
						buildLog(log, "produit", ligneOldEntity.getProduit().getLibelle(), ligneNewEntity.getProduit().getLibelle());
					}
					if(!ligneOldEntity.getQteCommande().equals(ligneNewEntity.getQteCommande())) {
						buildLog(log, "QteCommande", String.valueOf(ligneOldEntity.getQteCommande()), String.valueOf(ligneNewEntity.getQteCommande()));
					}
					if(!ligneOldEntity.getQteDefectueuse().equals(ligneNewEntity.getQteDefectueuse())) {
						buildLog(log, "QteDefectueuse", String.valueOf(ligneOldEntity.getQteDefectueuse()), String.valueOf(ligneNewEntity.getQteDefectueuse()));
					}
					saveLog(log);
				}
			}
		}

	}
	
	@Override
	@Transactional
	public void logFileAttente(FileAttente oldEntity,FileAttente newEntity,String motif) {
		
		if(!newEntity.getCamion().equals(oldEntity.getCamion())) {
			Journalisation log = getInstance( ORDRE_LIVRAISON, oldEntity.getId(), TypeOperation.RECTIFICATION, motif);
			buildLog(log, "camion", oldEntity.getCamion().getMatricule(), newEntity.getCamion().getMatricule());
			saveLog(log);
		}
		for(LigneFileAttente ligneOldEntity : oldEntity.getLigneFileAttente() ) {
			for(LigneFileAttente ligneNewEntity : newEntity.getLigneFileAttente() ) {
				if(ligneOldEntity.getId().equals(ligneNewEntity.getId())) {
					Long id = ligneNewEntity.getId();
					Journalisation log = getInstance( LIGNE_ORDRE_LIVRAISON, id, TypeOperation.RECTIFICATION, motif);
					if(!ligneOldEntity.getProduit().equals(ligneNewEntity.getProduit())) {
						buildLog(log, "produit", ligneOldEntity.getProduit().getLibelle(), ligneNewEntity.getProduit().getLibelle());
					}
					if(!ligneOldEntity.getQteCommande().equals(ligneNewEntity.getQteCommande())) {
						buildLog(log, "QteCommande", String.valueOf(ligneOldEntity.getQteCommande()), String.valueOf(ligneNewEntity.getQteCommande()));
					}
					if(!ligneOldEntity.getQteDefectueuse().equals(ligneNewEntity.getQteDefectueuse())) {
						buildLog(log, "QteDefectueuse", String.valueOf(ligneOldEntity.getQteDefectueuse()), String.valueOf(ligneNewEntity.getQteDefectueuse()));
					}
					saveLog(log);
				}
			}
		}
		
	}


	private void saveLog(Journalisation log) {
		if(log.getLogDetails() != null && !log.getLogDetails().isEmpty()) {
			save(log);
		}
	}

	private void buildLog(Journalisation journale, String champs, String oldvalue, String newValue) {

		if(journale.getLogDetails() == null) {
			journale.setLogDetails(new ArrayList<LogDetail>());
		}
		List<LogDetail> logDetails = journale.getLogDetails();
		LogDetail logDetail = new LogDetail();
		logDetail.setJournale(journale);
		logDetail.setChamps(champs);
		logDetail.setOldValue(oldvalue);
		logDetail.setNewValue(newValue);
		logDetails.add(logDetail);
	}


	private Journalisation getInstance(String typeName, Long reference, TypeOperation typeOperation,
			String motif) {
		
		User currenUser = SecurityUtils.getCurrentUser();

		Journalisation journalisation = new Journalisation();
		journalisation.setDateLog(new Date());
		journalisation.setTypeName(typeName);
		journalisation.setMembre(currenUser);
		journalisation.setSite(currenUser.getSite());
		journalisation.setReferenceId(reference);
		journalisation.setTypeOperation(typeOperation);
		
		return journalisation;
		
	}

	
}
