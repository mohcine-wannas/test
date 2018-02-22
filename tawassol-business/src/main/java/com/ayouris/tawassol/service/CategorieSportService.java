package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.CategorieSport;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface CategorieSportService extends GenericService<CategorieSport, Long> {

    List<CategorieSport> findByAdherentId(Long adherentId) throws Exception;
}
