package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.TransporteurBean;
import ma.salamgaz.gwic.common.model.entity.Transporteur;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.TransporteurRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface TransporteurRepository extends  CommonRepository<Transporteur>,TransporteurRepositoryCustom, GridRepositoryCustom<TransporteurBean> {

	
}
