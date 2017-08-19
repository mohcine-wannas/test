
package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ClientBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteClientBean;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PeriodeFacturation;
import com.ayouris.tawassol.common.model.entity.PrixVenteClient;
import com.ayouris.tawassol.common.model.entity.QPeriodeFacturation;
import com.ayouris.tawassol.common.model.entity.QPrixVenteClient;
import com.ayouris.tawassol.common.model.enums.StatutPeriodeFacturation;
import com.ayouris.tawassol.repository.AnneeRepository;
import com.ayouris.tawassol.repository.MoisRepository;
import com.ayouris.tawassol.repository.PeriodeFacturationRepository;
import com.ayouris.tawassol.repository.PrixVenteClientRepository;
import com.ayouris.tawassol.service.ClientService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.PrixVenteClientService;
import com.ayouris.tawassol.service.ProduitService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Service
public class PrixVenteClientServiceImpl extends GenericServiceImpl<PrixVenteClient, Long>
		implements PrixVenteClientService {

	@Autowired
	private PrixVenteClientRepository prixVenteClientRepository;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private ProduitService produitService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private PeriodeFacturationRepository periodeFacturationRepository;

	@Autowired
	private MoisRepository moisRepository;

	@Autowired
	private AnneeRepository anneeRepository;

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(PrixVenteClientServiceImpl.class);

	@Override
	public Long create(PrixVenteClientBean prixVenteClientBean) {

		PrixVenteClient entity = null;
		validateRequiredValues(prixVenteClientBean);

		if (prixVenteClientBean.getDateFin().before(prixVenteClientBean.getDateDebut())) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_DATE_INVALID);
		}

		entity = (PrixVenteClient) mapper.mapStrict(prixVenteClientBean, PrixVenteClient.class);
		if (isUnique(prixVenteClientBean)) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_ALREADY_EXISTS);
		}

		if (!overlap(prixVenteClientBean).isEmpty()) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_OVERLAP);
		}
		try {
			PeriodeFacturation periodeFacturation = createPeriodeFacturatyion(entity.getDateDebut());
			if (periodeFacturation != null) {
				periodeFacturationRepository.save(periodeFacturation).getId();
			}
			if (compareDatesPrixVente(prixVenteClientBean)) {
				periodeFacturation = new PeriodeFacturation();
				periodeFacturation = createPeriodeFacturatyion(entity.getDateFin());
				if (periodeFacturation != null) {

					periodeFacturationRepository.save(periodeFacturation);
				}
			}
			save(entity);
			return entity.getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_ALREADY_EXISTS);
		}
	}

	public Boolean compareDatesPrixVente(PrixVenteClientBean prixVenteClientBean) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(prixVenteClientBean.getDateDebut());
		String moisDebut = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
		String anneeDebut = "" + Calendar.getInstance().get(Calendar.YEAR);

		cal.setTime(prixVenteClientBean.getDateFin());
		String moisFin = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
		String anneeFin = "" + Calendar.getInstance().get(Calendar.YEAR);

		if (moisDebut.equals(moisFin) && anneeDebut.equals(anneeFin)) {
			return false;
		}
		return true;

	}

	public PeriodeFacturation createPeriodeFacturatyion(Date date) {
		PeriodeFacturation periodeFacturation = new PeriodeFacturation();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String mois = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
		String annee = "" + Calendar.getInstance().get(Calendar.YEAR);

		if (!isUniquePeriodeFacturation(mois, annee)) {
			periodeFacturation.setMois(moisRepository.findByLibelleIgnoreCase(mois));
			periodeFacturation.setAnnee(anneeRepository.findByLibelleIgnoreCase(annee));
			periodeFacturation.setStatut(StatutPeriodeFacturation.EN_ISTANCE);
			return periodeFacturation;
		}

		return null;
	}

	@Override
	@Transactional
	public List<PrixVenteClientBean> controle(PrixVenteClientBean object) {

		List<ProduitBean> produits = produitService.getAllProduitActif();
		List<SiteBean> sites = siteService.getAllSiteNotSiege();
		List<ClientBean> clients = clientService.getAll();
		List<PrixVenteClientBean> prixVentesControleResult = new ArrayList<PrixVenteClientBean>();
		Interval interval = new Interval(new DateTime(object.getDateDebut()), new DateTime(object.getDateFin()));
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(interval);
		for (ProduitBean produitBean : produits) {
			List<PrixVenteClientBean> prixVenteClientsList = AllPrixVenteInIntervalByProduct(interval, produitBean);
			if (!prixVenteClientsList.isEmpty()) {
				Map<String, List<PrixVenteClientBean>> prixVente = buildPrixVenteMap(prixVenteClientsList);
				List<PrixVenteClientBean> prixVenteClients = getPrixVente(prixVente, null, null);
				List<Interval> gaps = getGapIntervals(prixVenteClients, intervals);
				if (gaps.isEmpty()) {
					continue;
				} else {
					for (SiteBean site : sites) {
						prixVenteClients = getPrixVente(prixVente, site.getId(), null);
						List<Interval> siteGaps = getGapIntervals(prixVenteClients, gaps);
						if (siteGaps.isEmpty()) {
							continue;
						} else {
							for (ClientBean client : clients) {
								prixVenteClients = getPrixVente(prixVente, null, client.getId());
								List<Interval> clientGaps = getGapIntervals(prixVenteClients, siteGaps);
								if (clientGaps.isEmpty()) {
									continue;
								} else {
									prixVenteClients = getPrixVente(prixVente, site.getId(), client.getId());
									clientGaps = getGapIntervals(prixVenteClients, clientGaps);
									if (clientGaps.isEmpty()) {
										continue;
									} else {
										addGapsClient(prixVentesControleResult, produitBean, site, client, clientGaps);
									}
								}
							}
						}
					}
				}
			} else {
				addGapsClient(prixVentesControleResult, produitBean, null, null, intervals);
			}
		}

		if (prixVentesControleResult.isEmpty()) {
			PeriodeFacturation periodeFacturation = createPeriodeFacturatyion(object.getDateDebut());
			periodeFacturation.setStatut(StatutPeriodeFacturation.VALIDE);
			periodeFacturationRepository.save(periodeFacturation);

		}

		return prixVentesControleResult;
	}

	public List<Interval> getGapIntervals(List<PrixVenteClientBean> prixVenteClients, List<Interval> intervals) {
		List<Interval> intervalList = new ArrayList<Interval>();
		if (prixVenteClients == null) {
			return intervals;
		}
		for (PrixVenteClientBean prixVenteIter : prixVenteClients) {
			Interval intervalIter = new Interval(new DateTime(prixVenteIter.getDateDebut()),
					new DateTime(prixVenteIter.getDateFin()));
			intervalList.add(intervalIter);
		}

		List<Interval> gaps = new ArrayList<Interval>();
		gaps.addAll(intervals);
		for (Interval item : intervalList) {
			for (Interval gap : gaps) {
				if (item.contains(gap.getStart())
						&& (item.contains(gap.getEnd()) || item.getEnd().equals(gap.getEnd()))) {
					gaps.remove(gap);
					break;
				} else {
					gaps = findGap(gap, item);
				}
			}
		}
		return gaps;
	}

	public void addGapsClient(List<PrixVenteClientBean> prixVentesControleResult, ProduitBean produitBean,
			SiteBean site, ClientBean client, List<Interval> clientGaps) {
		PrixVenteClientBean prixClient = new PrixVenteClientBean();
		prixClient.setProduit(produitBean);
		prixClient.setClient(client);
		prixClient.setSite(site);
		for (Interval interv : clientGaps) {
			prixClient.setDateDebut(interv.getStart().toDate());
			prixClient.setDateFin(interv.getEnd().toDate());
			prixVentesControleResult.add(prixClient);
		}
	}

	public List<PrixVenteClientBean> getPrixVente(Map<String, List<PrixVenteClientBean>> prixVente, Long idSite,
			Long idClient) {
		String key = buildKey(idSite, idClient);
		return prixVente.get(key);
	}

	public Map<String, List<PrixVenteClientBean>> buildPrixVenteMap(List<PrixVenteClientBean> prixVenteClientsList) {
		Map<String, List<PrixVenteClientBean>> prixVente = new LinkedHashMap<String, List<PrixVenteClientBean>>();

		for (PrixVenteClientBean prix : prixVenteClientsList) {
			String key = buildKey(prix.getSite() == null ? null : prix.getSite().getId(),
					prix.getClient() == null ? null : prix.getClient().getId());

			if (!prixVente.containsKey(key)) {
				prixVente.put(key, new ArrayList<PrixVenteClientBean>());
			}
			prixVente.get(key).add(prix);

		}
		return prixVente;
	}

	public String buildKey(Long idSite, Long idClient) {

		String siteKey = idSite == null ? "#" : idSite.toString();
		String clientKey = idClient == null ? "#" : idClient.toString();
		String key = siteKey + '-' + clientKey;
		return key;
	}

	@Override
	public List<Interval> findGap(Interval interval, Interval otherInterval) {
		List<Interval> intervals = new ArrayList<Interval>();
		DateTime otherStart = otherInterval.getStart();
		DateTime otherEnd = otherInterval.getEnd();
		DateTime start = interval.getStart();
		DateTime end = interval.getEnd();
		if (!start.isBefore(otherStart)) {
			intervals.add(new Interval(otherEnd.plusDays(1), end));
		} else if (!end.isAfter(otherEnd)) {
			intervals.add(new Interval(start, otherStart.minusDays(1)));
		} else {
			intervals.add(new Interval(start, otherStart.minusDays(1)));
			intervals.add(new Interval(otherEnd.plusDays(1), end));
		}
		return intervals;
	}

	private void validateRequiredValues(PrixVenteClientBean prixVenteClientBean) {
		if (prixVenteClientBean.getDateDebut() == null || prixVenteClientBean.getDateFin() == null
				|| prixVenteClientBean.getPrix() == null || prixVenteClientBean.getProduit() == null) {
			throw new ServiceException(ErrorMessageType.PRIX_VENTE_CLIENT_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<PrixVenteClientBean> list(PageDataBean paginateData) {
		return prixVenteClientRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public PrixVenteClientBean getDetails(Long id) {
		PrixVenteClient prixVenteClient = findOne(id);
		return mapper.map(prixVenteClient, PrixVenteClientBean.class);
	}

	@Override
	@Transactional
	public List<PrixVenteClientBean> AllPrixVenteInIntervalByProduct(Interval interval, ProduitBean produitBean) {
		QPrixVenteClient prixVenteClient = QPrixVenteClient.prixVenteClient;
		List<PrixVenteClient> prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository
				.findAll(prixVenteClient.produit.id.eq(produitBean.getId()).and(prixVenteClient.dateDebut
						.between(interval.getStart().toDate(), interval.getEnd().toDate())
						.or(prixVenteClient.dateFin.between(interval.getStart().toDate(), interval.getEnd().toDate()))
						.or((prixVenteClient.dateDebut.before(interval.getStart().toDate()))
								.and(prixVenteClient.dateFin.after(interval.getEnd().toDate())))
						.or((prixVenteClient.dateDebut.eq(interval.getStart().toDate()))
								.and(prixVenteClient.dateFin.eq(interval.getEnd().toDate())))));

		return mapper.map(prixVenteClients, PrixVenteClientBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public Boolean isUniquePeriodeFacturation(String mois, String annee) {
		QPeriodeFacturation periodeFacturation = QPeriodeFacturation.periodeFacturation;
		return periodeFacturationRepository
				.findAll(periodeFacturation.mois.libelle.eq(mois).and(periodeFacturation.annee.libelle.eq(annee)))
				.iterator().hasNext();

	}

	@Override
	@Transactional
	public Boolean isUnique(PrixVenteClientBean prixVenteClientBean) {
		QPrixVenteClient prixVenteClient = QPrixVenteClient.prixVenteClient;
		PrixVenteClient entity = mapper.mapStrict(prixVenteClientBean, PrixVenteClient.class);
		Boolean unique = false;
		if (isNew(entity)) {
			if (prixVenteClientBean.getSite() == null && prixVenteClientBean.getClient() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId())
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteClientBean.getClient() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteClientBean.getSite() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId()))
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			}
		} else {
			if (prixVenteClientBean.getSite() == null && prixVenteClientBean.getClient() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId())
								.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteClientBean.getClient() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else if (prixVenteClientBean.getSite() == null) {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			} else {
				unique = prixVenteClientRepository
						.findAll(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
								.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId()))
								.and(prixVenteClient.produit.id.eq(prixVenteClientBean.getProduit().getId()))
								.and(prixVenteClient.dateDebut.eq(prixVenteClientBean.getDateDebut()))
								.and(prixVenteClient.dateFin.eq(prixVenteClientBean.getDateFin())))
						.iterator().hasNext();
			}
		}

		return unique;
	}

	@Override
	@Transactional
	public List<PrixVenteClientBean> overlap(PrixVenteClientBean prixVenteClientBean) {
		QPrixVenteClient prixVenteClient = QPrixVenteClient.prixVenteClient;
		PrixVenteClient entity = mapper.mapStrict(prixVenteClientBean, PrixVenteClient.class);
		List<PrixVenteClient> prixVenteClients = new ArrayList<PrixVenteClient>();
		if (isNew(entity)) {
			if (prixVenteClientBean.getClient() == null && prixVenteClientBean.getSite() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else if (prixVenteClientBean.getClient() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.or(prixVenteClient.site.isNull()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else if (prixVenteClientBean.getSite() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.or(prixVenteClient.client.isNull()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.or(prixVenteClient.client.isNull()))
						.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.or(prixVenteClient.site.isNull()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			}
		} else {
			if (prixVenteClientBean.getClient() == null && prixVenteClientBean.getSite() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else if (prixVenteClientBean.getClient() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.or(prixVenteClient.site.isNull()))
						.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else if (prixVenteClientBean.getSite() == null) {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.or(prixVenteClient.client.isNull()))
						.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			} else {
				prixVenteClients = (List<PrixVenteClient>) prixVenteClientRepository.findAll(prixVenteClient.produit.id
						.eq(prixVenteClientBean.getProduit().getId())
						.and(prixVenteClient.client.id.eq(prixVenteClientBean.getClient().getId())
								.or(prixVenteClient.client.isNull()))
						.and(prixVenteClient.site.id.eq(prixVenteClientBean.getSite().getId())
								.or(prixVenteClient.site.isNull()))
						.and(prixVenteClient.id.ne(prixVenteClientBean.getId()))
						.and((prixVenteClient.dateDebut.between(prixVenteClientBean.getDateDebut(),
								prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateFin.between(prixVenteClientBean.getDateDebut(),
												prixVenteClientBean.getDateFin()))
										.or(prixVenteClient.dateDebut.before(prixVenteClientBean.getDateDebut()).and(
												prixVenteClient.dateFin.after(prixVenteClientBean.getDateFin())))));
			}
		}
		return mapper.map(prixVenteClients, PrixVenteClientBean.LIST_BEAN_TYPE);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.PRIX_VENTE_CLIENT);
	}

	@Override
	@Transactional
	public List<PrixVenteClientBean> getAll() {
		List<PrixVenteClient> prixVenteClients = findAll();
		return mapper.map(prixVenteClients, PrixVenteClientBean.LIST_BEAN_TYPE);
	}
}
