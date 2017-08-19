package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteConcessionnaireBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PrixVenteConcessionnaire;
import com.ayouris.tawassol.common.model.entity.QPrixVenteConcessionnaire;
import com.ayouris.tawassol.repository.PrixVenteConcessionnaireRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.PrixVenteConcessionnaireService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class PrixVenteConcessionnaireServiceImpl extends GenericServiceImpl<PrixVenteConcessionnaire, Long>
		implements PrixVenteConcessionnaireService {

	@Autowired
	private PrixVenteConcessionnaireRepository prixVenteConcessionnaireRepository;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private ColumnDefService columnDefService;

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(PrixVenteConcessionnaireServiceImpl.class);

	@Override
	@Transactional
	public Long create(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean) {

		PrixVenteConcessionnaire entity = null;
		validateRequiredValues(prixVenteConcessionnaireBean);

		if (prixVenteConcessionnaireBean.getDateFin().before(prixVenteConcessionnaireBean.getDateDebut())) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_CONCESSIONNAIRE_DATE_INVALID);
		}

		entity = (PrixVenteConcessionnaire) mapper.mapStrict(prixVenteConcessionnaireBean,
				PrixVenteConcessionnaire.class);
		if (isUnique(prixVenteConcessionnaireBean)) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_CONCESSIONNAIRE_ALREADY_EXISTS);
		}

		if (!overlap(prixVenteConcessionnaireBean).isEmpty()) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_CONCESSIONNAIRE_OVERLAP);
		}

		try {
			save(entity);
			return entity.getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_CONCESSIONNAIRE_ALREADY_EXISTS);
		}
	}

	private void validateRequiredValues(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean) {
		if (prixVenteConcessionnaireBean.getDateDebut() == null || prixVenteConcessionnaireBean.getDateFin() == null
				|| prixVenteConcessionnaireBean.getPrix() == null
				|| prixVenteConcessionnaireBean.getProduit() == null) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_CONCESSIONNAIRE_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<PrixVenteConcessionnaireBean> list(PageDataBean paginateData) {
		return prixVenteConcessionnaireRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public PrixVenteConcessionnaireBean getDetails(Long id) {
		PrixVenteConcessionnaire prixVenteConcessionnaire = findOne(id);
		return mapper.map(prixVenteConcessionnaire, PrixVenteConcessionnaireBean.class);
	}

	@Override
	@Transactional
	public Boolean isUnique(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean) {
		QPrixVenteConcessionnaire prixVenteConcessionnaire = QPrixVenteConcessionnaire.prixVenteConcessionnaire;
		PrixVenteConcessionnaire entity = mapper.mapStrict(prixVenteConcessionnaireBean,
				PrixVenteConcessionnaire.class);
		Boolean unique = false;
		if (isNew(entity)) {
			if (prixVenteConcessionnaireBean.getConcessionnaire() == null
					&& prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
								.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.site.id
						.eq(prixVenteConcessionnaireBean.getSite().getId())
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			} else if (prixVenteConcessionnaireBean.getSite() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.concessionnaire.id
						.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			} else {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.concessionnaire.id
						.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
						.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId()))
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			}
		} else {
			if (prixVenteConcessionnaireBean.getConcessionnaire() == null
					&& prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
								.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
								.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.site.id
						.eq(prixVenteConcessionnaireBean.getSite().getId())
						.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			} else if (prixVenteConcessionnaireBean.getSite() == null) {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.concessionnaire.id
						.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
						.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			} else {
				unique = prixVenteConcessionnaireRepository.findAll(prixVenteConcessionnaire.concessionnaire.id
						.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
						.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
						.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId()))
						.and(prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()))
						.and(prixVenteConcessionnaire.dateDebut.eq(prixVenteConcessionnaireBean.getDateDebut()))
						.and(prixVenteConcessionnaire.dateFin.eq(prixVenteConcessionnaireBean.getDateFin()))).iterator()
						.hasNext();
			}
		}

		return unique;
	}

	@Override
	@Transactional
	public List<PrixVenteConcessionnaireBean> overlap(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean) {
		QPrixVenteConcessionnaire prixVenteConcessionnaire = QPrixVenteConcessionnaire.prixVenteConcessionnaire;
		PrixVenteConcessionnaire entity = mapper.mapStrict(prixVenteConcessionnaireBean,
				PrixVenteConcessionnaire.class);
		List<PrixVenteConcessionnaire> prixVenteConcessionnaires = new ArrayList<PrixVenteConcessionnaire>();
		if (isNew(entity)) {
			if (prixVenteConcessionnaireBean.getConcessionnaire() == null
					&& prixVenteConcessionnaireBean.getSite() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId()).and(
								(prixVenteConcessionnaire.dateDebut.between(prixVenteConcessionnaireBean.getDateDebut(),
										prixVenteConcessionnaireBean.getDateFin()))
												.or(prixVenteConcessionnaire.dateFin.between(
														prixVenteConcessionnaireBean
																.getDateDebut(),
														prixVenteConcessionnaireBean.getDateFin()))));
			} else if (prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId())
										.or(prixVenteConcessionnaire.site.isNull()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			} else if (prixVenteConcessionnaireBean.getSite() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.concessionnaire.id
										.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
										.or(prixVenteConcessionnaire.concessionnaire.isNull()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			} else {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.concessionnaire.id
										.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
										.or(prixVenteConcessionnaire.concessionnaire.isNull()))
								.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId())
										.or(prixVenteConcessionnaire.site.isNull()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			}
		} else {
			if (prixVenteConcessionnaireBean.getConcessionnaire() == null
					&& prixVenteConcessionnaireBean.getSite() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))

								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(),
										prixVenteConcessionnaireBean.getDateFin()))
												.or(prixVenteConcessionnaire.dateFin.between(
														prixVenteConcessionnaireBean.getDateDebut(),
														prixVenteConcessionnaireBean.getDateFin()))));
			} else if (prixVenteConcessionnaireBean.getConcessionnaire() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId())
										.or(prixVenteConcessionnaire.site.isNull()))
								.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			} else if (prixVenteConcessionnaireBean.getSite() == null) {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.concessionnaire.id
										.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
										.or(prixVenteConcessionnaire.concessionnaire.isNull()))
								.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			} else {
				prixVenteConcessionnaires = (List<PrixVenteConcessionnaire>) prixVenteConcessionnaireRepository.findAll(
						prixVenteConcessionnaire.produit.id.eq(prixVenteConcessionnaireBean.getProduit().getId())
								.and(prixVenteConcessionnaire.concessionnaire.id
										.eq(prixVenteConcessionnaireBean.getConcessionnaire().getId())
										.or(prixVenteConcessionnaire.concessionnaire.isNull()))
								.and(prixVenteConcessionnaire.site.id.eq(prixVenteConcessionnaireBean.getSite().getId())
										.or(prixVenteConcessionnaire.site.isNull()))
								.and(prixVenteConcessionnaire.id.ne(prixVenteConcessionnaireBean.getId()))
								.and((prixVenteConcessionnaire.dateDebut.between(
										prixVenteConcessionnaireBean.getDateDebut(), prixVenteConcessionnaireBean
												.getDateFin()))
														.or(prixVenteConcessionnaire.dateFin.between(
																prixVenteConcessionnaireBean
																		.getDateDebut(),
																prixVenteConcessionnaireBean.getDateFin()))));
			}
		}
		return mapper.map(prixVenteConcessionnaires, PrixVenteConcessionnaireBean.LIST_BEAN_TYPE);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.PRIX_VENTE_CONCESSIONNAIRE);
	}

	@Override
	@Transactional	
	public List<PrixVenteConcessionnaireBean> getAll() {
		List<PrixVenteConcessionnaire> prixVenteConcessionnaires = findAll();
		return mapper.map(prixVenteConcessionnaires, PrixVenteConcessionnaireBean.LIST_BEAN_TYPE);
	}
}
