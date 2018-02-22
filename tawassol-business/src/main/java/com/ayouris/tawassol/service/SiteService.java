package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.SiteBean;
import com.ayouris.tawassol.common.model.entity.Site;

public interface SiteService extends GenericService<Site, Long>, RefService<SiteBean> {


	List<SiteBean> getAll();
	
}
