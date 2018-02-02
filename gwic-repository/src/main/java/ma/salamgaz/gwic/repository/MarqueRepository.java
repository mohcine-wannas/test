package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.MarqueBean;
import ma.salamgaz.gwic.common.model.entity.Marque;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.MarqueRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface MarqueRepository extends  CommonRepository<Marque>,MarqueRepositoryCustom, GridRepositoryCustom<MarqueBean> {

	
}
