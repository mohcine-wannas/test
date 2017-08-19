package com.ayouris.tawassol.repository.custom;

import com.ayouris.tawassol.common.model.entity.CategorieSport;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface CategorieSportRepositoryCustom {
    List<CategorieSport> findByAdherentId(Long adherentId) throws Exception;
}
