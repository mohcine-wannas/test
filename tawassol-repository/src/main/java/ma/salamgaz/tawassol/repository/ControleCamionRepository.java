package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.ControleCamionBean;
import ma.salamgaz.tawassol.common.model.entity.ControleCamion;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.ControleCamionRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface ControleCamionRepository extends  CommonRepository<ControleCamion>,ControleCamionRepositoryCustom, GridRepositoryCustom<ControleCamionBean> {

	
}
