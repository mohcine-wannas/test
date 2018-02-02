package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.PrestationBean;
import ma.salamgaz.gwic.common.model.entity.Prestation;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.PrestationRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface PrestationRepository extends  CommonRepository<Prestation>,PrestationRepositoryCustom, GridRepositoryCustom<PrestationBean> {

	
}
