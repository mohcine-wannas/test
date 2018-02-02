package ma.salamgaz.gwic.repository.custom;

import ma.salamgaz.gwic.common.model.dto.AdherentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Adherent;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface AdherentRepositoryCustom {
    List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto);
}
