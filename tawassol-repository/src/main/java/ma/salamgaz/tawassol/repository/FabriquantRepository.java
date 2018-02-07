package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.FabriquantBean;
import ma.salamgaz.tawassol.common.model.entity.Fabriquant;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.FabriquantRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface FabriquantRepository extends  CommonRepository<Fabriquant>,FabriquantRepositoryCustom, GridRepositoryCustom<FabriquantBean> {

	
}
