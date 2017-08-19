package com.ayouris.tawassol.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.repository.EmplissageRepository;
import com.ayouris.tawassol.repository.OrdreLivraisonRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.JourneeActiviteService;

@Service
public class CodificationServiceImpl {
	
	@Autowired
	private OrdreLivraisonRepository ordreLivraisonRepository;
	
	@Autowired
	private EmplissageRepository emplissageRepository;

	@Autowired
	private JourneeActiviteService journeeActiviteService;
	
	public String getGeneratedCode(Class className) {

		String maxId=null;
		ActiviteJourneeActivite activite = null;
		if(className.equals(OrdreLivraison.class)) {
			activite = ActiviteJourneeActivite.LIVRAISON;
			String pattern = buildCode("___",activite);
			 maxId = ordreLivraisonRepository.getLastCode(pattern);
		}else if(className.equals(Emplissage.class)){
			activite = ActiviteJourneeActivite.EMPLISSAGE;
			String pattern = buildCode("___",activite);
			maxId = emplissageRepository.getLastCode(pattern);
			
		}
		return getNextCode(maxId,activite);
	}

	public String getNextCode(String code,ActiviteJourneeActivite activite) {
		String numero = null;

		if (code == null)
			numero = "001";
		else {
			int compteur = Integer.parseInt(code.substring(9, 12)) + 1;
			numero = StringUtils.leftPad(String.valueOf(compteur), 3, '0');
		}
		return buildCode(numero,activite);
	}

	private String buildCode(String numero,ActiviteJourneeActivite activite) {

		Site site = SecurityUtils.getCurrentUserSite();

		Calendar calendar = Calendar.getInstance();
		Date date = journeeActiviteService.getCurrentJourneeActiviteOuvertByActivity(activite).getDateJournee();
		calendar.setTime(date);
		String codeCentre = site.getCode().length() > 3 ? StringUtils.substring(site.getCode(), 0, 3) : site.getCode();
		String centre = StringUtils.leftPad(codeCentre, 3, '0');
		String mois = StringUtils.leftPad(String.valueOf((calendar.get(Calendar.MONTH) + 1)), 2, '0');
		String day = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2, '0');
		String annee = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.YEAR) % 100), 2, '0');

		return new StringBuilder(centre).append(annee).append(mois).append(day).append(numero).toString();

	}

}