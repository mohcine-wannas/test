package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.dto.AdherentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Adherent;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface AdherentService extends GenericService<Adherent, Long> {
    /**
     * Anregistrement d'un nouveau adherent
     * @param adherent
     * @return
     * @throws Exception
     */
    Adherent saveAdherent(Adherent adherent) throws Exception;

    /**
     * Recherche des adhernets par criteres
     * @param adherentCriteriaDto
     * @return
     * @throws Exception
     */
    List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto) throws Exception;
}
