package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.bean.AffectationEleveClasseBean;
import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.repository.AffectationEleveClasseRepository;
import com.ayouris.tawassol.service.AffectationEleveClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author m.wannas
 */

@Service
public class AffectationEleveClasseServiceImpl extends GenericServiceImpl2<AffectationEleveClasse, Long, AffectationEleveClasseBean> implements AffectationEleveClasseService {


    @Autowired
    private AffectationEleveClasseRepository affectationEleveClasseRepository;


    @Override
    public AffectationEleveClasse findByEleveIdAndClasseId(Long eleveId, Long classeId) {

        return affectationEleveClasseRepository.findByEleveIdAndClasseId(eleveId, classeId).get();
    }

    @Override
    public AffectationEleveClasse getOne(Long aLong) {
        return super.getOne(aLong);
    }
}
