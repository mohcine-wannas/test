package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.SiteBean;
import ma.salamgaz.gwic.common.model.entity.Site;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.SiteRepositoryCustom;

public interface SiteRepository extends  CommonRepository<Site>,SiteRepositoryCustom,GridRepositoryCustom<SiteBean>  {

	
}