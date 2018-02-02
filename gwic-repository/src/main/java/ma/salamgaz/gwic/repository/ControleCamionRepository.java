package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.ControleCamionBean;
import ma.salamgaz.gwic.common.model.entity.ControleCamion;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.ControleCamionRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface ControleCamionRepository extends  CommonRepository<ControleCamion>,ControleCamionRepositoryCustom, GridRepositoryCustom<ControleCamionBean> {

	
}
