package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.PrestationBean;
import ma.salamgaz.tawassol.common.model.entity.Prestation;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.PrestationRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface PrestationRepository extends  CommonRepository<Prestation>,PrestationRepositoryCustom, GridRepositoryCustom<PrestationBean> {

	
}
