package com.ayouris.tawassol.repository.custom;

import com.ayouris.tawassol.common.model.dto.AdherentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Adherent;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface AdherentRepositoryCustom {
    List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto);
}
