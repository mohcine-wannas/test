package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.entity.Ventilation;
import com.ayouris.tawassol.common.model.enums.StatutPieceReglement;
import com.ayouris.tawassol.common.model.enums.StatutVentilation;
import com.ayouris.tawassol.repository.VentilationRepository;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.PieceReglementService;
import com.ayouris.tawassol.service.VentilationService;

@Service
public class VentilationServiceImpl extends GenericServiceImpl<Ventilation, Long> implements VentilationService {

	@Autowired
	private VentilationRepository ventilationRepository;

	@Autowired
	private BonLivraisonService bonLivraisonService;

	@Autowired
	private PieceReglementService pieceReglementService;


	@Override
	@Transactional
	public List<Ventilation> ventilationPeriodique(PieceReglement pieceReglement) {

		List<BonLivraison> bonsLivraison = bonLivraisonService
				.getAllBLNonRegleByConcessId(pieceReglement.getConcessionnaire().getId());

		List<Ventilation> ventilations = new ArrayList<Ventilation>();

		if (!bonsLivraison.isEmpty()) {
			Collections.sort(bonsLivraison);

			for (BonLivraison item : bonsLivraison) {
				Double montant;
				if (pieceReglement.getMontantRestant() <= item.getMontantRestant()) {
					item.setMontantRestant(item.getMontantRestant() - pieceReglement.getMontantRestant());
					montant = pieceReglement.getMontantRestant();
					pieceReglement.setMontantRestant(0.0);
					pieceReglement.setSoldee(true);
					if(item.getMontantRestant()==0){
						item.setRegle(true);
					}
					createVentilation(ventilations, item, montant);
					break;
				} else {
					pieceReglement.setMontantRestant(pieceReglement.getMontantRestant() - item.getMontantRestant());
					montant = item.getMontantRestant();
					item.setMontantRestant(0.0);
					item.setRegle(true);
					createVentilation(ventilations, item, montant);
				}

			}
			pieceReglement.setStatut(StatutPieceReglement.VALIDEE);
		}
		return ventilations;
	}

	@Override
	@Transactional
	public void ventilationParEnlevement(Long id) {

		List<BonLivraison> bonsLivraison = bonLivraisonService.getAllBLNonRegleByConcessId(id);

		List<PieceReglement> piecesReglement = pieceReglementService
				.getAllPREnlevementNonSoldeByConcessId(id);
		List<BonLivraison> bons = new ArrayList<BonLivraison>();
		bons.addAll(bonsLivraison);
		if (!piecesReglement.isEmpty() && !bonsLivraison.isEmpty()) {
			Collections.sort(bonsLivraison);
			Collections.sort(piecesReglement);
			for (PieceReglement pieceReglement : piecesReglement) {
				bonsLivraison.clear();
				bonsLivraison.addAll(bons);
				for (BonLivraison item : bonsLivraison) {
					Double montant;
					if (pieceReglement.getMontantRestant() <= item.getMontantRestant()) {
						item.setMontantRestant(item.getMontantRestant() - pieceReglement.getMontantRestant());
						montant = pieceReglement.getMontantRestant();
						pieceReglement.setMontantRestant(0.0);
						pieceReglement.setSoldee(true);
						pieceReglement.setStatut(StatutPieceReglement.VALIDEE);
						if(item.getMontantRestant()==0){
							item.setRegle(true);
							bons.remove(item);
						}
						createVentilationEnlevement(pieceReglement, item, montant);
						break;
					} else {
						pieceReglement.setMontantRestant(pieceReglement.getMontantRestant() - item.getMontantRestant());
						montant = item.getMontantRestant();
						item.setMontantRestant(0.0);
						item.setRegle(true);
						bons.remove(item);
						createVentilationEnlevement(pieceReglement, item, montant);
					}

				}
				if (!pieceReglement.getSoldee()){
					pieceReglement.setStatut(StatutPieceReglement.VALIDEE);
					pieceReglementService.save(pieceReglement);
				}
				
				if (bons.isEmpty())
					break;
			}
		}
	}

	public void createVentilation(List<Ventilation> ventilations, BonLivraison bonLivraison, Double montant) {
		Ventilation ventilation = new Ventilation();
		ventilation.setBonLivraison(bonLivraison);
		ventilation.setMontant(montant);
		ventilation.setStatut(StatutVentilation.VALIDEE);
		ventilations.add(ventilation);
		bonLivraisonService.save(bonLivraison);
	}

	public void createVentilationEnlevement(PieceReglement pieceReglement, BonLivraison bonLivraison, Double montant) {
		Ventilation ventilation = new Ventilation();
		ventilation.setBonLivraison(bonLivraison);
		ventilation.setMontant(montant);
		ventilation.setStatut(StatutVentilation.VALIDEE);
		ventilation.setPieceReglement(pieceReglement);
		bonLivraisonService.save(bonLivraison);
		ventilationRepository.save(ventilation);
		if (pieceReglement.getSoldee())
			pieceReglementService.save(pieceReglement);
	}
}
