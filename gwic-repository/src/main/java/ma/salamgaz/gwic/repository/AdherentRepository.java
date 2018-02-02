package ma.salamgaz.gwic.repository;

import org.springframework.stereotype.Repository;

import ma.salamgaz.gwic.repository.custom.AdherentRepositoryCustom;

/**
 * Created by chamakh on 06/01/2017.
 */
@Repository
public interface AdherentRepository extends AdherentRepositoryCustom { // JpaRepository<Adherent, Long>, AdherentRepositoryCustom {

}