package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.SiteBean;
import ma.salamgaz.tawassol.common.model.entity.Site;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.SiteRepositoryCustom;

public interface SiteRepository extends  CommonRepository<Site>,SiteRepositoryCustom,GridRepositoryCustom<SiteBean>  {

	
}