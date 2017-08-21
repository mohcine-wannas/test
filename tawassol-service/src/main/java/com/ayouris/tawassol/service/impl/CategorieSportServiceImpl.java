package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.entity.CategorieSport;
import com.ayouris.tawassol.repository.CategorieSportRepository;
import com.ayouris.tawassol.service.CategorieSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
@Service
public class CategorieSportServiceImpl extends GenericServiceImpl<CategorieSport, Long> implements CategorieSportService {

    private final CategorieSportRepository categorieSportRepository;

    @Autowired
    public CategorieSportServiceImpl(CategorieSportRepository categorieSportRepository) {
        this.categorieSportRepository = categorieSportRepository;
    }

    @Override
    public List<CategorieSport> findByAdherentId(Long adherentId) throws Exception {
        return categorieSportRepository.findByAdherentId(adherentId);
    }
}
