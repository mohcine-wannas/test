package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Adherent;
import com.ayouris.tawassol.repository.custom.AdherentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chamakh on 06/01/2017.
 */
@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long>, AdherentRepositoryCustom {

}