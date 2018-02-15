package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.MarqueBean;
import ma.salamgaz.tawassol.common.model.entity.Marque;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.MarqueRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface MarqueRepository extends  CommonRepository<Marque>,MarqueRepositoryCustom, GridRepositoryCustom<MarqueBean> {

	
}
