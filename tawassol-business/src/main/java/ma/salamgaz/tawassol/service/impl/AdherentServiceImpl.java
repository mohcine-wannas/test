package ma.salamgaz.tawassol.service.impl;

import ma.salamgaz.tawassol.common.model.dto.AdherentCriteriaDto;
import ma.salamgaz.tawassol.common.model.entity.Adherent;
import ma.salamgaz.tawassol.repository.AdherentRepository;
import ma.salamgaz.tawassol.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */

public class AdherentServiceImpl extends GenericServiceImpl<Adherent, Long> implements AdherentService {

    private final AdherentRepository adherentRepository;


    public AdherentServiceImpl(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @Override
    public Adherent saveAdherent(Adherent adherent) throws Exception{
		return adherent;
        //return adherentRepository.save(adherent);
    }

    @Override
    public List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto) throws Exception {
        return adherentRepository.findAdherentListByCriteria(adherentCriteriaDto);
    }
}
