package com.ayouris.tawassol.repository;

import org.springframework.stereotype.Repository;

import com.ayouris.tawassol.repository.custom.AdherentRepositoryCustom;

/**
 * Created by chamakh on 06/01/2017.
 */
@Repository
public interface AdherentRepository extends AdherentRepositoryCustom { // JpaRepository<Adherent, Long>, AdherentRepositoryCustom {

}