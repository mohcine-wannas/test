package ma.salamgaz.gwic.service.impl;

import ma.salamgaz.gwic.common.model.dto.AdherentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Adherent;
import ma.salamgaz.gwic.repository.AdherentRepository;
import ma.salamgaz.gwic.service.AdherentService;
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
