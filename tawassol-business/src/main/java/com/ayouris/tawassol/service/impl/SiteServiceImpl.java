package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.beans.ColumnDefBean;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.admin.model.enums.SiteType;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.QJourneeActivite;
import com.ayouris.tawassol.common.model.entity.QSite;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.StatutJourneeActivite;
import com.ayouris.tawassol.repository.JourneeActiviteRepository;
import com.ayouris.tawassol.repository.SiteRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Service
public class SiteServiceImpl extends GenericServiceImpl<Site, Long> implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private JourneeActiviteRepository journeeActiviteRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public Long createOrUpdate(SiteBean siteBean) {

		ValidateRequiredValues(siteBean);

		Site entity = mapper.map(siteBean, Site.class);
		try {
			return save(entity).getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.SITE_CODE_ALREADY_EXISTS);
		}
	}

	private void ValidateRequiredValues(SiteBean siteBean) {

		if (StringUtils.isBlank(siteBean.getCode()) || StringUtils.isBlank(siteBean.getAbreviation())
				|| StringUtils.isBlank(siteBean.getLibelle()) || siteBean.getSiteType() == null
				|| siteBean.getTauxTransport() == null) {

			throw new ServiceException(ErrorMessageType.SITE_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<SiteBean> list(PageDataBean paginateData) {
		return siteRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public SiteBean getDetails(Long id) {

		Site site = findOne(id);
		return mapper.map(site, SiteBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		List<ColumnDefBean> columnsDefbeans = columnDefService.getByViewName(ViewName.SITE);

		List<ColumnDef> columnsDefs = mapper.map(columnsDefbeans, ColumnDefBean.LIST_ENTITY_TYPE);
		// TODO null verification
		Map<String, ColumnDef> columnDefMap = columnsDefs.stream()
				.collect(Collectors.toMap(ColumnDef::getField, Function.identity()));

		return columnDefMap;
	}
	
    @Override
    public List<SiteBean> getAll() {
        List<Site> sites = findAll();
        return mapper.map(sites, SiteBean.LIST_BEAN_TYPE);
    }

	@Override
	public List<SiteBean> getExcludeSiege() {
		Iterable<Site> sites = siteRepository.findAll(QSite.site.siteType.ne(SiteType.SIEGE));
		return mapper.map(sites, SiteBean.LIST_BEAN_TYPE);
	}

	@Override
	public List<SiteBean> getAllSiteNotSiege() {
		return mapper.map(siteRepository.findAll(QSite.site.siteType.ne(SiteType.SIEGE)), SiteBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<JourneeActiviteBean> getAllJourneeActOuverteBySiteId(Long id) {
		QJourneeActivite journeeActivite = QJourneeActivite.journeeActivite;
		Iterable<JourneeActivite> journeeActiviteIterable = journeeActiviteRepository
				.findAll(journeeActivite.site.id.eq(id).and(journeeActivite.statut.ne(StatutJourneeActivite.CLOTUREE)));
		List<JourneeActivite> journeeActivites = new ArrayList<JourneeActivite>();
		journeeActiviteIterable.forEach(journeeActivites::add);
		return mapper.map(journeeActivites, JourneeActiviteBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<JourneeActiviteBean> getAllJourneeActBySiteId(Long id) {
		QJourneeActivite journeeActivite = QJourneeActivite.journeeActivite;
		Iterable<JourneeActivite> journeeActiviteIterable = journeeActiviteRepository
				.findAll(journeeActivite.site.id.eq(id));
		List<JourneeActivite> journeeActivites = new ArrayList<JourneeActivite>();
		journeeActiviteIterable.forEach(journeeActivites::add);
		return mapper.map(journeeActivites, JourneeActiviteBean.LIST_BEAN_TYPE);
	}

}
