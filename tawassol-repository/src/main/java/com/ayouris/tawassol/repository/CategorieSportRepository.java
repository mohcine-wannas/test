package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.CategorieSport;
import com.ayouris.tawassol.repository.custom.CategorieSportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface CategorieSportRepository extends CategorieSportRepositoryCustom {/// JpaRepository<CategorieSport, Long>,


}
