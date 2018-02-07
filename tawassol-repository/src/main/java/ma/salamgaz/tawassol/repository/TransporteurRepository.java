package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.TransporteurBean;
import ma.salamgaz.tawassol.common.model.entity.Transporteur;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.TransporteurRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface TransporteurRepository extends  CommonRepository<Transporteur>,TransporteurRepositoryCustom, GridRepositoryCustom<TransporteurBean> {

	
}
