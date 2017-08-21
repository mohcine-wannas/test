package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.dto.AdherentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Adherent;
import com.ayouris.tawassol.repository.AdherentRepository;
import com.ayouris.tawassol.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
@Service
public class AdherentServiceImpl extends GenericServiceImpl<Adherent, Long> implements AdherentService {

    private final AdherentRepository adherentRepository;

    @Autowired
    public AdherentServiceImpl(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @Override
    public Adherent saveAdherent(Adherent adherent) throws Exception{
        return adherentRepository.save(adherent);
    }

    @Override
    public List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto) throws Exception {
        return adherentRepository.findAdherentListByCriteria(adherentCriteriaDto);
    }
}
