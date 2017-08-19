package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.entity.Site;

public interface SiteService extends GenericService<Site, Long>, RefService<SiteBean> {


	List<SiteBean> getAll();
	List<SiteBean> getExcludeSiege();
	List<JourneeActiviteBean> getAllJourneeActBySiteId(Long id);
	List<JourneeActiviteBean> getAllJourneeActOuverteBySiteId(Long id);
//	List<String> getAllTypeFileAttenteId(Long id);
	List<SiteBean> getAllSiteNotSiege();
	
	
}
