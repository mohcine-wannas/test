package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.entity.Banque;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.BanqueRepositoryCustom;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueRepository extends CommonRepository<Banque>, BanqueRepositoryCustom{
}
