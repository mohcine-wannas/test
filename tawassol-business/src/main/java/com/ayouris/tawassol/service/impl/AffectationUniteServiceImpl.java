package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationUniteBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.repository.AffectationUniteRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.AffectationUniteService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.UniteService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AffectationUniteServiceImpl extends GenericServiceImpl2<AffectationUnite, Long, AffectationUniteBean> implements AffectationUniteService {

    @Autowired
    private AffectationUniteRepository affectationUniteRepository;
    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private AffectationCycleService affectationCycleService;
    @Autowired
    private UniteService uniteService;

    @Transactional
    @Override
    public List<AffectationUniteBean> getAffectationsUniteByCycleId(Long cycleId) {

        AnneeScolaire currentAnneeScolaire = SecurityUtils.getCurrentAnneeScolaire();
        School currentSchool = SecurityUtils.getCurrentSchool();

        AffectationCycle affectationCycle = affectationCycleService.getAffectationCycleByCycleIdAndSchoolIdAndAnneeScolaireId(cycleId, currentSchool.getId(), currentAnneeScolaire.getId());
        List<AffectationUnite> affectations = new ArrayList<>();
        if (affectationCycle != null) {
            generateDefaultAffectationsUnite(cycleId, affectationCycle);

            affectations = affectationUniteRepository.findByAffectationCycleId(affectationCycle.getId());
        } else {
            throw new ServiceException(ErrorMessageType.AFFECTATION_CYCLE_NOT_FOUND);
        }

        return mapper.map(affectations, AffectationUniteBean.LIST_BEAN_TYPE);
    }

    @Transactional
    @Override
    public List<AffectationUniteBean> getAffectationsUnite() {

        AffectationCycle affectationCycle = affectationCycleService.getCurrentAffectationCycle();
        List<AffectationUnite> affectations;
        if (affectationCycle != null) {
            generateDefaultAffectationsUnite(affectationCycle.getCycle().getId(), affectationCycle);
            affectations = affectationUniteRepository.findByAffectationCycleId(affectationCycle.getId());
        } else {
            throw new ServiceException(ErrorMessageType.AFFECTATION_CYCLE_NOT_FOUND);
        }

        return mapper.map(affectations, AffectationUniteBean.LIST_BEAN_TYPE);
    }

    @Override
    @Transactional
    public void updateAffectationsUnite(List<AffectationUniteBean> affectations) {
        for (AffectationUniteBean item : affectations) {
            AffectationUnite oldEntity = affectationUniteRepository.findOne(item.getId());
            if (oldEntity != null) {
                oldEntity.setEnabled(item.getEnabled());
                save(oldEntity);
            } else {
                throw new ServiceException(ErrorMessageType.AFFECTATION_UNITE_SAVE_ERROR);
            }
        }

    }

    @Transactional
    private void generateDefaultAffectationsUnite(Long cycleId, AffectationCycle affectationCycle) {
        List<AffectationUnite> affectationUnites = affectationUniteRepository.findByAffectationCycleId(affectationCycle.getId());

        List<Unite> unites = uniteService.findUnitesByCycleId(cycleId);

        UNITE:
        for (Unite unite : unites) {
            for (AffectationUnite affectation : affectationUnites) {
                if (unite.getId().equals(affectation.getUnite().getId())) {
                    continue UNITE;
                }
            }
            createAffectationUnite(affectationCycle, unite);
        }
    }

    private void createAffectationUnite(AffectationCycle affectationCycle, Unite unite) {
        AffectationUnite affectationUnite = new AffectationUnite();
        affectationUnite.setUnite(unite);
        affectationUnite.setAffectationCycle(affectationCycle);
        affectationUnite.setEnabled(false);
        save(affectationUnite);
    }

    @Override
    public List<AffectationUniteBean> getAffectationsUniteBySchoolCodeAndByCycleId(String schoolCode, Long cycleId) {
        QAffectationUnite affectationUnite = QAffectationUnite.affectationUnite;
        BooleanExpression expression = affectationUnite.affectationCycle.school.code.eq(schoolCode).and(affectationUnite.affectationCycle.cycle.id.eq(cycleId))
                .and(affectationUnite.enabled.isTrue());

        List<AffectationUnite> affectations = (List<AffectationUnite>) affectationUniteRepository.findAll(expression);

        return mapper.map(affectations, AffectationUniteBean.LIST_BEAN_TYPE);
    }
}
