package com.ayouris.tawassol.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.CustomWebApplicationException;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CauseArretEmplissageBean;
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.FicheMarcheBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PostBean;
import com.ayouris.tawassol.common.model.entity.ArretEmplissage;
import com.ayouris.tawassol.common.model.entity.CauseArretEmplissage;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.CreuxEmplissage;
import com.ayouris.tawassol.common.model.entity.FicheMarche;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.Post;
import com.ayouris.tawassol.common.model.entity.QCauseArretEmplissage;
import com.ayouris.tawassol.common.model.entity.QFicheMarche;
import com.ayouris.tawassol.common.model.entity.QPost;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutFicheMarche;
import com.ayouris.tawassol.repository.CauseArretEmplissageRepository;
import com.ayouris.tawassol.repository.FicheMarcheRepository;
import com.ayouris.tawassol.repository.PostRepository;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.EmplissageService;
import com.ayouris.tawassol.service.FicheMarcheService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Service
public class FicheMarcheServiceImpl extends GenericServiceImpl<FicheMarche, Long> implements FicheMarcheService {

	@Autowired
	private FicheMarcheRepository ficheMarcheRepository;

	@Autowired
	private UserRepository userRepository;

	// @Autowired
	// private CreuxEmplissageRepository creuxEmplissageRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CauseArretEmplissageRepository causeArretEmplissageRepository;

	@Autowired
	private SiteService siteService;

	@Autowired
	private EmplissageService emplissageService;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	@Transactional
	public void cloturer(Long id) {

		FicheMarche entity = findOne(id);
		if (entity == null) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_NOT_FOUND);
		}
		if (emplissageService.isAllEmpNotValideFicheMarche(id)) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_CLOTURE_INVALID);
		}
		entity.setStatut(StatutFicheMarche.CLOTUREE);
		save(entity);
	}

	@Override
	@Transactional
	public Long createOrUpdate(FicheMarcheBean ficheMarcheBean) {
		validateRequiredValues(ficheMarcheBean);
		FicheMarche entity = (FicheMarche) mapper.mapStrict(ficheMarcheBean, FicheMarche.class);
		validateJourneeActiviteEmplissage(entity);
		if (ficheMarcheNonCloturee(entity)) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_ALREADY_PRODUIT_EXISTS);
		}
		if (isNew(entity)) {
			entity.setQteEmplissageTotal((long) 0);
			entity.setDateJournee(new Date());
		} else {
			entity.setStatut(StatutFicheMarche.VALIDE);
		}

		if (!entity.getCreuxEmplissage().isEmpty()) {
			for (CreuxEmplissage creux : entity.getCreuxEmplissage()) {
				creux.setFicheMarche(entity);
			}
		}
		if (!entity.getArretsEmplissage().isEmpty()) {
			for (ArretEmplissage arret : entity.getArretsEmplissage()) {
				arret.setFicheMarche(entity);
			}
		}
		try {
			return save(entity).getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_ALREADY_EXISTS);
		}
	}

	private void validateRequiredValues(FicheMarcheBean ficheMarcheBean) {
		if (ficheMarcheBean.getPost() == null || ficheMarcheBean.getProduit() == null
				|| ficheMarcheBean.getUser() == null) {

			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_MISSING_REQUIRED_VALUES);
		}
	}

	private void validateJourneeActiviteEmplissage(FicheMarche ficheMarche) {

		User user = userRepository.findOne(ficheMarche.getUser().getId());
		List<JourneeActiviteBean> journeeActivites = siteService
				.getAllJourneeActOuverteBySiteId(user.getSite().getId());
		if (journeeActivites.size() == 0 || journeeActivites.size() > 1) {
			throw new ServiceException(ErrorMessageType.EMPLISSAGE_JOURNEE_ACTIVITE_EMPLISSAGE_NO_EXISTS);
		}

		JourneeActiviteBean journeeActiviteBean = new JourneeActiviteBean();
		for (JourneeActiviteBean item : journeeActivites) {
			if (item.getActivite().equals(ActiviteJourneeActivite.EMPLISSAGE)) {
				journeeActiviteBean = item;
			}
		}
		if (journeeActiviteBean.getActivite() == null) {
			throw new ServiceException(ErrorMessageType.EMPLISSAGE_JOURNEE_ACTIVITE_EMPLISSAGE_NO_EXISTS);
		}

		if (isNew(ficheMarche)) {
			ficheMarche.setJourneeActivite(mapper.mapStrict(journeeActiviteBean, JourneeActivite.class));
		} else {
			if (!journeeActiviteBean.getId().equals(ficheMarche.getJourneeActivite().getId())) {
				throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_JOURNEE_ACTIVITE_EMPLISSAGE_NO_CONFORM);
			}
		}
	}

	@Override
	public GridListBean<FicheMarcheBean> list(PageDataBean paginateData) {
		return ficheMarcheRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public FicheMarcheBean getDetails(Long id) {
		FicheMarche ficheMarche = findOne(id);
		if (ficheMarche == null) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_NOT_FOUND);
		}
		return mapper.map(ficheMarche, FicheMarcheBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.FICHE_MARCHE);
	}

	@Override
	public List<FicheMarcheBean> getAll() {
		List<FicheMarche> ficheMarches = findAll();
		return mapper.map(ficheMarches, FicheMarcheBean.LIST_BEAN_TYPE);
	}

	@Override
	public List<PostBean> getAllPost() {
		List<Post> posts = (List<Post>) postRepository.findAll(QPost.post.active.isTrue());
		return mapper.map(posts, PostBean.LIST_BEAN_TYPE);
	}

	@Override
	public Boolean isAllFichesNotValide(Long id) {
		return ficheMarcheRepository.findAll(QFicheMarche.ficheMarche.statut.ne(StatutFicheMarche.VALIDE)
				.and(QFicheMarche.ficheMarche.journeeActivite.id.eq(id))).iterator().hasNext();
	}

	@Override
	public FicheMarche getFichesNotValideByUsername(EmplissageBean emplissageBean, User user) {
		QFicheMarche ficheMarche = QFicheMarche.ficheMarche;
		try {
			return ficheMarcheRepository.findOne(ficheMarche.statut.ne(StatutFicheMarche.CLOTUREE)
					.and(ficheMarche.user.username.eq(user.getUsername())
							.and(ficheMarche.produit.id.eq(emplissageBean.getProduit().getId()))));
		} catch (ServiceException e) {
			throw new CustomWebApplicationException(e);
		}
	}

	@Override
	public Boolean ficheMarcheNonCloturee(FicheMarche entity) {
		QFicheMarche ficheMarche = QFicheMarche.ficheMarche;
		Boolean cloturee = false;
		if (isNew(entity)) {
			cloturee = ficheMarcheRepository.findAll(ficheMarche.statut.ne(StatutFicheMarche.CLOTUREE)
					.and(ficheMarche.user.id.eq(entity.getUser().getId()))
					.and(ficheMarche.produit.id.eq(entity.getProduit().getId()))).iterator().hasNext();
		} else {
			cloturee = ficheMarcheRepository
					.findAll(ficheMarche.id.ne(entity.getId()).and(ficheMarche.statut.ne(StatutFicheMarche.CLOTUREE))
							.and(ficheMarche.user.id.eq(entity.getUser().getId()))
							.and(ficheMarche.produit.id.eq(entity.getProduit().getId())))
					.iterator().hasNext();
		}
		return cloturee;
	}

	@Override
	public List<CauseArretEmplissageBean> getAllCausesArret() {
		List<CauseArretEmplissage> causes = (List<CauseArretEmplissage>) causeArretEmplissageRepository
				.findAll(QCauseArretEmplissage.causeArretEmplissage.active.isTrue());
		return mapper.map(causes, CauseArretEmplissageBean.LIST_BEAN_TYPE);
	}
}
