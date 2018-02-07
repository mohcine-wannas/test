package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.SiteBean;
import ma.salamgaz.tawassol.common.model.entity.Site;

public interface SiteService extends GenericService<Site, Long>, RefService<SiteBean> {


	List<SiteBean> getAll();
	
}
