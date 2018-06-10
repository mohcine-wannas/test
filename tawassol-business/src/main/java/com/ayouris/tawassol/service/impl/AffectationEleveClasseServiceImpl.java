package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.model.entity.QAffectationEleveClasse;
import com.ayouris.tawassol.repository.AffectationEleveClasseRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.bean.AffectationEleveClasseBean;
import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.service.AffectationEleveClasseService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AffectationEleveClasseServiceImpl extends GenericServiceImpl2<AffectationEleveClasse,Long,AffectationEleveClasseBean> implements AffectationEleveClasseService {


    @Autowired
    AffectationCycleService affectationCycleService;
    @Autowired
    AffectationEleveClasseRepository affectationEleveClasseRepository;

    @Override
    public AffectationEleveClasse getClasseByEleve(Eleve eleve) {
        if(eleve == null || eleve.getId() == null) {
            return null;
        }
        AffectationCycle currentAffectationCycle = affectationCycleService.getCurrentAffectationCycle();
        if(currentAffectationCycle != null) {
            QAffectationEleveClasse QAffectation = QAffectationEleveClasse.affectationEleveClasse;
            BooleanExpression predicat = QAffectation.classe.active.isTrue();
            predicat = predicat.and(QAffectation.classe.affectationNiveau.niveau.active.isTrue());
            predicat = predicat.and(QAffectation.classe.affectationNiveau.affecctationCycle.id.eq(currentAffectationCycle.getId()));
            predicat = predicat.and(QAffectation.eleve.id.eq(eleve.getId()));

            return affectationEleveClasseRepository.findOne(predicat);
        }
        return null;
    }

}
