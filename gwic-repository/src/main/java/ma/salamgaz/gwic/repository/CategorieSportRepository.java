package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.entity.CategorieSport;
import ma.salamgaz.gwic.repository.custom.CategorieSportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface CategorieSportRepository extends CategorieSportRepositoryCustom {/// JpaRepository<CategorieSport, Long>,


}
