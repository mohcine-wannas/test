package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.CiterneBean;
import ma.salamgaz.tawassol.common.model.entity.Citerne;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.CiterneRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface CiterneRepository extends  CommonRepository<Citerne>,CiterneRepositoryCustom, GridRepositoryCustom<CiterneBean> {

	
}
