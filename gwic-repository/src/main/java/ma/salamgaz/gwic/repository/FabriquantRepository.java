package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.FabriquantBean;
import ma.salamgaz.gwic.common.model.entity.Fabriquant;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.FabriquantRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface FabriquantRepository extends  CommonRepository<Fabriquant>,FabriquantRepositoryCustom, GridRepositoryCustom<FabriquantBean> {

	
}
