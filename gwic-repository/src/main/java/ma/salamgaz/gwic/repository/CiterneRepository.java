package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.CiterneBean;
import ma.salamgaz.gwic.common.model.entity.Citerne;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.CiterneRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface CiterneRepository extends  CommonRepository<Citerne>,CiterneRepositoryCustom, GridRepositoryCustom<CiterneBean> {

	
}
