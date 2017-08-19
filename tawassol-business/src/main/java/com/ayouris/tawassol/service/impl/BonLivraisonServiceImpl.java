package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.bean.ControleCamionConformityBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.LigneBonLivraisonConditionneBean;
import com.ayouris.tawassol.common.model.bean.LigneBonLivraisonVracBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ControleCamionBonLivraisonConformity;
import com.ayouris.tawassol.common.model.entity.LigneBonLivraisonConditionne;
import com.ayouris.tawassol.common.model.entity.LigneBonLivraisonVrac;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraisonVrac;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.QBonLivraison;
import com.ayouris.tawassol.common.model.entity.QLigneBonLivraisonConditionne;
import com.ayouris.tawassol.common.model.entity.QLigneBonLivraisonVrac;
import com.ayouris.tawassol.common.model.enums.StatutBonLivraison;
import com.ayouris.tawassol.common.model.enums.StatutLigneBonLivraison;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.AffectationProduitOperateurRepository;
import com.ayouris.tawassol.repository.BonLivraisonRepository;
import com.ayouris.tawassol.repository.LigneBonLivraisonRepository;
import com.ayouris.tawassol.repository.LigneBonLivraisonVracRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ControleCamionService;
import com.ayouris.tawassol.service.FileAttenteService;
import com.ayouris.tawassol.service.JournalisationService;

@Service
public class BonLivraisonServiceImpl extends GenericServiceImpl<BonLivraison, Long> implements BonLivraisonService {

	@Autowired
	private BonLivraisonRepository bonLivraisonRepository;
	@Autowired
	private LigneBonLivraisonRepository ligneBonLivraisonRepository;
	@Autowired
	private LigneBonLivraisonVracRepository ligneBonLivraisonVracRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	AffectationProduitOperateurRepository affectationProduitOperateurRepository;

	@Autowired
	JournalisationService journalisationService;

	@Autowired
	ControleCamionService controleCamionService;

	@Autowired
	private FileAttenteService fileAttenteService;

	private static final Logger LOGGER = LoggerFactory.getLogger(BonLivraisonServiceImpl.class);

	@Override
	public Long create(BonLivraisonBean bonLivraisonBean) {

		Long id = bonLivraisonBean.getId();
		BonLivraison oldBonLivraison = findOne(id);
		BonLivraison entity = mapper.mapStrict(bonLivraisonBean, BonLivraison.class);
		if (entity.getLigneBonLivraisonConditionne() != null) {
			for (LigneBonLivraisonConditionne ligne : entity.getLigneBonLivraisonConditionne()) {
				ligne.setBonLivraison(entity);
			}
		}

		List<LigneBonLivraisonConditionne> oldLblcToDelete = new ArrayList<LigneBonLivraisonConditionne>();
		List<LigneBonLivraisonConditionne> lblcToAdd = new ArrayList<LigneBonLivraisonConditionne>();
		if (entity.getLigneBonLivraisonConditionne() != null) {
			for (LigneBonLivraisonConditionne oldLblc : oldBonLivraison.getLigneBonLivraisonConditionne()) {
				for (LigneBonLivraisonConditionne lblc : entity.getLigneBonLivraisonConditionne()) {
					if (lblc.getId() != null && lblc.getId().equals(oldLblc.getId())) {
						lblc.setBonLivraison(oldBonLivraison);
						lblc.setStatut(StatutLigneBonLivraison.EN_TRAITEMENT);
						oldBonLivraison.setStatut(StatutBonLivraison.EN_TRAITEMENT);
						oldBonLivraison.getOrdreLivraison().setStatut(StatutOrdreLivraison.EN_TRAITEMENT);
						oldLblcToDelete.add(oldLblc);
						lblcToAdd.add(lblc);
					}
				}
			}
			oldBonLivraison.getLigneBonLivraisonConditionne().removeAll(oldLblcToDelete);
			oldBonLivraison.getLigneBonLivraisonConditionne().addAll(lblcToAdd);
		}

		if (entity.getLigneBonLivraisonVrac() != null) {
			oldBonLivraison.setLigneBonLivraisonVrac(entity.getLigneBonLivraisonVrac());
		}
		if (entity.getNumBL() != null) {
			oldBonLivraison.setNumBL(entity.getNumBL());
		}
		if (entity.getNote() != null) {
			oldBonLivraison.setNote(entity.getNote());
		}
		if (entity.getStatut() != null) {
			oldBonLivraison.setStatut(entity.getStatut());
		}
		if (entity.getTypeChargement() != null) {
			oldBonLivraison.setTypeChargement(entity.getTypeChargement());
		}

		if (StatutBonLivraison.A_VERIFIER.equals(oldBonLivraison.getStatut())) {

		} else if (StatutBonLivraison.A_VERIFIER.equals(oldBonLivraison.getStatut())) {
			oldBonLivraison.setStatut(StatutBonLivraison.EN_TRAITEMENT);
			List<LigneBonLivraisonConditionne> newLists = entity.getLigneBonLivraisonConditionne();
			List<LigneBonLivraisonConditionne> oldLists = oldBonLivraison.getLigneBonLivraisonConditionne();

			for (LigneBonLivraisonConditionne newLigne : newLists) {
				for (LigneBonLivraisonConditionne oldLigne : newLists) {
					if (newLigne.getId() != null && newLigne.getId().equals(oldLigne.getId())) {
						if (newLigne != oldLigne) {
							oldLigne.setStatut(StatutLigneBonLivraison.EN_INSTANCE);
						}
						continue;
					}
				}
			}

			fileAttenteService.setAuCentreOrdreLivraison(oldBonLivraison.getOrdreLivraison());
		}
		try {
			return save(oldBonLivraison).getId();
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}

	}

	@Override
	public GridListBean<BonLivraisonBean> list(PageDataBean paginateData) {
		return bonLivraisonRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public BonLivraisonBean getDetails(Long id) {
		BonLivraison bonLivraison = findOne(id);
		BonLivraisonBean newBonLivraison = mapper.map(bonLivraison, BonLivraisonBean.class);

		User currentUser = SecurityUtils.getCurrentUser();

		List<AffectationProduitOperateur> affectations = affectationProduitOperateurRepository
				.findByUserId(currentUser.getId());
		if (newBonLivraison.getTypeChargement() == TypeChargement.CONDITIONNE) {

			List<LigneBonLivraisonConditionneBean> oldLblcToDelete = new ArrayList<LigneBonLivraisonConditionneBean>();
			List<Long> usedIds = new ArrayList<Long>();

			if (newBonLivraison.getLigneBonLivraisonConditionne() != null) {
				for (LigneBonLivraisonConditionneBean oldLblc : newBonLivraison.getLigneBonLivraisonConditionne()) {

					Boolean found = false;
					if (oldLblc.getProduit() != null) {
						for (AffectationProduitOperateur affectation : affectations) {

							if (oldLblc.getProduit().getId().equals(affectation.getProduit().getId())) {
								found = true;
								continue;
							}
						}
						if (!found) {
							oldLblcToDelete.add(oldLblc);
						}
					}
				}
				newBonLivraison.getLigneBonLivraisonConditionne().removeAll(oldLblcToDelete);
			}
		} else if (newBonLivraison.getTypeChargement() == TypeChargement.VRAC) {
			if (newBonLivraison.getLigneBonLivraisonVrac() != null) {
				LigneBonLivraisonVracBean l = newBonLivraison.getLigneBonLivraisonVrac();
				if (l.getProduit() != null) {
					Boolean exist = false;
					for (AffectationProduitOperateur affectation : affectations) {
						if (l.getProduit().getId().equals(affectation.getProduit().getId())) {
							exist = true;
						}
					}
					if (!exist) {
						l = null;
						newBonLivraison.setLigneBonLivraisonVrac(l);
					}
				}
			}
		}

		removeDuplicated(newBonLivraison);
		return newBonLivraison;

	}

	private void removeDuplicated(BonLivraisonBean newBonLivraison) {
		List<LigneBonLivraisonConditionneBean> al = newBonLivraison.getLigneBonLivraisonConditionne();
		Set<LigneBonLivraisonConditionneBean> hs = new HashSet<LigneBonLivraisonConditionneBean>();
		hs.addAll(al);
		al.clear();
		al.addAll(hs);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.BON_LIVRAISON);
	}

	@Override
	@Transactional
	public List<BonLivraisonBean> getAll() {
		List<BonLivraison> bonLivraisons = findAll();
		return mapper.map(bonLivraisons, BonLivraisonBean.LIST_BEAN_TYPE);
	}

	@Override
	public BonLivraison createFromOL(OrdreLivraison ordrelivraison) {
		BonLivraison bonLivraison = new BonLivraison();
		if (TypeChargement.CONDITIONNE.equals(ordrelivraison.getTypeChargement())) {

			List<LigneBonLivraisonConditionne> lignesBonLivraisonConditionne = new ArrayList<LigneBonLivraisonConditionne>();
			LigneBonLivraisonConditionne ligneBonLivraisonConditionne = new LigneBonLivraisonConditionne();
			for (LigneOrdreLivraison ligneOrdreLivraison : ordrelivraison.getLigneOrdreLivraison()) {

				ligneBonLivraisonConditionne.setProduit(ligneOrdreLivraison.getProduit());
				ligneBonLivraisonConditionne.setStatut(StatutLigneBonLivraison.EN_INSTANCE);
				ligneBonLivraisonConditionne.setQteCommande(ligneOrdreLivraison.getQteCommande());
				lignesBonLivraisonConditionne.add(ligneBonLivraisonConditionne);
				ligneBonLivraisonConditionne = new LigneBonLivraisonConditionne();
			}
			bonLivraison.setLigneBonLivraisonConditionne(lignesBonLivraisonConditionne);

		} else {
			LigneOrdreLivraisonVrac ligneVrac = ordrelivraison.getLigneOrdreLivraisonVrac();
			LigneBonLivraisonVrac ligneBonLivraisonVrac = new LigneBonLivraisonVrac();
			ligneBonLivraisonVrac.setProduit(ordrelivraison.getLigneOrdreLivraisonVrac().getProduit());
			ligneBonLivraisonVrac.setStatut(StatutLigneBonLivraison.EN_INSTANCE);
			ligneBonLivraisonVrac.setQteCommandees(ligneVrac.getQteCommande());
			bonLivraison.setLigneBonLivraisonVrac(ligneBonLivraisonVrac);
		}
		bonLivraison.setNumBL(ordrelivraison.getNumOL());
		bonLivraison.setStatut(StatutBonLivraison.EN_INSTANCE);
		bonLivraison.setOrdreLivraison(ordrelivraison);
		bonLivraison.setTypeChargement(ordrelivraison.getTypeChargement());
		return save(bonLivraison);
	}

	@Override
	public void marquerTraite(Long id) {
		BonLivraison entity = findOne(id);
		User currentUser = SecurityUtils.getCurrentUser();
		if (entity.getTypeChargement() == TypeChargement.CONDITIONNE) {
			List<AffectationProduitOperateur> affectations = affectationProduitOperateurRepository
					.findByUserId(currentUser.getId());
			if (entity.getLigneBonLivraisonConditionne() != null) {
				List<LigneBonLivraisonConditionne> ligneBLs = entity.getLigneBonLivraisonConditionne();
				boolean allmarked = true;
				for (LigneBonLivraisonConditionne ligne : ligneBLs) {
					if (ligne.getProduit() != null) {
						for (AffectationProduitOperateur affectation : affectations) {
							if (ligne.getProduit().getId().equals(affectation.getProduit().getId())) {
								ligne.setStatut(StatutLigneBonLivraison.MARQUER_TRAITE);
							}
						}

						if (!StatutLigneBonLivraison.MARQUER_TRAITE.equals(ligne.getStatut())) {
							allmarked = false;
						}
					}
				}

				if (allmarked) {
					entity.setStatut(StatutBonLivraison.A_VERIFIER);
				}

			}
		} else if (entity.getTypeChargement() == TypeChargement.VRAC) {
			entity.setStatut(StatutBonLivraison.A_VERIFIER);
			LigneBonLivraisonVrac ligneVrca = entity.getLigneBonLivraisonVrac();
			ligneVrca.setStatut(StatutLigneBonLivraison.MARQUER_TRAITE);
		}
		OrdreLivraison ordre = entity.getOrdreLivraison();
		if (ordre.getNumOL() != null) {
			fileAttenteService.setAlaSortieByOrdreLivraison(ordre);
		}
		save(entity);
	}

	@Override
	public void annulerByOrdreLivraison(OrdreLivraison ordreLivraison, String motif) {

		BonLivraison bonLivraison = bonLivraisonRepository.findByOrdreLivraison(ordreLivraison);
		annuler(bonLivraison, "( Annulation OrdreLivraison ) " + motif);
	}

	@Override
	@Transactional
	public void annuler(Long id, String motif) {

		BonLivraison bonLivraison = findOne(id);
		annuler(bonLivraison, motif);
	}

	@Override
	public void annuler(BonLivraison bonLivraison, String motif) {

		if (bonLivraison == null) {
			return;
		}
		StatutBonLivraison annule;
		if (StatutBonLivraison.VALIDE.equals(bonLivraison.getStatut())) {
			annule = StatutBonLivraison.EN_INSTANCE_ANNULATION;
		} else if (StatutBonLivraison.EN_INSTANCE.equals(bonLivraison.getStatut())) {
			annule = StatutBonLivraison.ANNULE;
		} else {
			return;
		}

		bonLivraison.setStatut(annule);
		if (bonLivraison.getOrdreLivraison().getTypeChargement().equals(TypeChargement.CONDITIONNE)) {
			for (LigneBonLivraisonConditionne ligneBonLivraison : bonLivraison.getLigneBonLivraisonConditionne()) {
				ligneBonLivraison.setStatut(StatutLigneBonLivraison.ANNULE);
			}
		} else {
			bonLivraison.getLigneBonLivraisonVrac().setStatut(StatutLigneBonLivraison.ANNULE);
		}
		journalisationService.logAnnulation(bonLivraison, motif);
		save(bonLivraison);

		OrdreLivraison ordre = bonLivraison.getOrdreLivraison();
		if (ordre.getNumOL() != null) {
			fileAttenteService.annulerByOrdreLivraison(ordre);
		}
		ordre.setStatut(StatutOrdreLivraison.ANNULE);
	}

	@Override
	@Transactional
	public void setControleCamionConformity(BonLivraisonBean BonLivraisonBean) {
		if (BonLivraisonBean.getId() != null) {
			BonLivraison BonLivraison = findOne(BonLivraisonBean.getId());
			BonLivraison entity = (BonLivraison) mapper.mapStrict(BonLivraisonBean, BonLivraison.class);
			List<ControleCamionBonLivraisonConformity> ccc = entity.getControleCamionConformities();
			List<ControleCamionBonLivraisonConformity> controleCamionConformities = BonLivraison
					.getControleCamionConformities();
			if (ccc != null) {
				for (ControleCamionBonLivraisonConformity value : ccc) {
					value.setBonLivraison(entity);
				}
				if (controleCamionConformities == null) {
					controleCamionConformities = ccc;
				} else {
					controleCamionConformities.retainAll(ccc);
					controleCamionConformities.addAll(ccc);
				}
			}
			BonLivraison.setStatut(StatutBonLivraison.EN_TRAITEMENT);
			save(BonLivraison);
		}
	}

	@Override
	@Transactional
	public List<ControleCamionConformityBean> getControleCamionConformities(Long id) {
		BonLivraison bonLivraison = findOne(id);
		if (bonLivraison.getControleCamionConformities().isEmpty()) {
			return controleCamionService
					.getControleCamionConformitiesBeans(bonLivraison.getOrdreLivraison().getTypeChargement());
		} else {
			List<ControleCamionBonLivraisonConformity> controleCamionConformities = bonLivraison
					.getControleCamionConformities();
			List<ControleCamionConformityBean> beans = new ArrayList<ControleCamionConformityBean>();
			// for (ControleCamionBonLivraisonConformity
			// controleCamionBonLivraisonConformity:
			// controleCamionConformities) {
			// ControleCamionConformityBean bean = new
			// ControleCamionConformityBean();
			// bean.setConforme(controleCamionBonLivraisonConformity.getConforme());
			// }

			beans = mapper.map(controleCamionConformities, ControleCamionConformityBean.LIST_BEAN_TYPE);

			return beans;
		}
	}

	@Override
	@Transactional
	public Boolean isAnyBlNotValide(Long id) {
		Boolean res = bonLivraisonRepository.findAll(QBonLivraison.bonLivraison.statut.ne(StatutBonLivraison.VALIDE)
				.and(QBonLivraison.bonLivraison.ordreLivraison.journeeActivite.id.eq(id))).iterator().hasNext();
		res = res && ligneBonLivraisonRepository.findAll(
				QLigneBonLivraisonConditionne.ligneBonLivraisonConditionne.statut.ne(StatutLigneBonLivraison.VALIDE)
						.and(QLigneBonLivraisonConditionne.ligneBonLivraisonConditionne.bonLivraison.ordreLivraison.journeeActivite.id
								.eq(id)))
				.iterator().hasNext();
		res = res && ligneBonLivraisonVracRepository
				.findAll(QLigneBonLivraisonVrac.ligneBonLivraisonVrac.statut.ne(StatutLigneBonLivraison.VALIDE)
						.and(QLigneBonLivraisonConditionne.ligneBonLivraisonConditionne.bonLivraison.ordreLivraison.journeeActivite.id
								.eq(id)))
				.iterator().hasNext();
		return res;
	}

	@Override
	@Transactional
	public List<BonLivraison> getAllBLNonRegleByConcessId(Long id) {
		QBonLivraison bonLivraison = QBonLivraison.bonLivraison;
		return (List<BonLivraison>) bonLivraisonRepository.findAll(bonLivraison.ordreLivraison.concessionnaire.id.eq(id)
				.and(bonLivraison.regle.isFalse()).and(bonLivraison.statut.eq(StatutBonLivraison.VALIDE)));
	}

}
