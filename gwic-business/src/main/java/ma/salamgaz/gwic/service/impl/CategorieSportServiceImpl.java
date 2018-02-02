package ma.salamgaz.gwic.service.impl;

import ma.salamgaz.gwic.common.model.entity.CategorieSport;
import ma.salamgaz.gwic.repository.CategorieSportRepository;
import ma.salamgaz.gwic.service.CategorieSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
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
