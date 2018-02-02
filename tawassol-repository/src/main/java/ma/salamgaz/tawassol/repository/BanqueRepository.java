package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.entity.Banque;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.BanqueRepositoryCustom;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueRepository extends CommonRepository<Banque>, BanqueRepositoryCustom{
}
