package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.SiteBean;
import ma.salamgaz.gwic.common.model.entity.Site;

public interface SiteService extends GenericService<Site, Long>, RefService<SiteBean> {


	List<SiteBean> getAll();
	
}
