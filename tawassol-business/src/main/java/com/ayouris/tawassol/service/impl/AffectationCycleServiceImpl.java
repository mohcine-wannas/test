package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.common.model.bean.AffectationNiveauBean;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.common.model.enums.ClasseNominationType;
import com.ayouris.tawassol.repository.AffectationCycleRepository;
import com.ayouris.tawassol.security.service.AnneeScolaireSecurityService;
import com.ayouris.tawassol.security.service.CycleSecurityService;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.GroupeAppellationService;
import com.ayouris.tawassol.service.ProfesseurService;
import com.ayouris.tawassol.service.ServiceException;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author m.wannas
 */
@Service
public class AffectationCycleServiceImpl extends GenericServiceImpl2<AffectationCycle, Long, AffectationCycleBean> implements AffectationCycleService {

    @Autowired
    private AffectationCycleRepository affectationCycleRepository;
    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private GroupeAppellationService groupeAppellationService;
    @Autowired
    private CycleSecurityService cycleService;
    @Autowired
    private AnneeScolaireSecurityService anneeScolaireService;
    @Autowired
    private ProfesseurService professeurService;

    @Override
    public AffectationCycle getCurrentAffectationCycle() {
        Cycle currentCycle = SecurityUtils.getCurrentCycle();
        AnneeScolaire anneeScolaire = SecurityUtils.getCurrentAnneeScolaire();
        School school = SecurityUtils.getCurrentSchool();
        AffectationCycle affectationCycle = getAffectationCycleBySchoolAndCycleAndAnneeScolaire(school, currentCycle, anneeScolaire);
        if (affectationCycle == null) {
            affectationCycle = generateDefaultAffectationCycle(school, currentCycle, anneeScolaire);
        }
        return affectationCycle;
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    @Override
    public AffectationCycleBean getCurrentAffectationCycleForProfBean() {
        AffectationCycle affectationCycle = getCurrentAffectationCycle();
        if(!(SecurityUtils.getCurrentUser() instanceof Professeur)) {
            return null;
        }
        Professeur prof = professeurService.findOne(SecurityUtils.getCurrentUser().getId());


        List<AffectationNiveauClasseProf> list= prof.getAffectationsNiveauClasseProf();
        for(AffectationNiveau niveau : affectationCycle.getAffectationNiveaux()) {
            niveau.getClasses().removeIf((classe -> {
                boolean found = false;
                for (AffectationNiveauClasseProf affectation : list) {
                    if (affectation.getClasse().getId().equals(classe.getId()) && affectation.getNiveau().getId().equals(niveau.getId())) {
                        found = true;
                        break;
                    }
                }
                return !found;
            }));
        }
        affectationCycle.getAffectationNiveaux().removeIf(niveau -> niveau.getClasses().isEmpty());

        return mapper.map(affectationCycle, AffectationCycleBean.class);
    }

    @Override
    public AffectationCycleBean getCurrentAffectationCycleBean() {
        AffectationCycle affectationCycle = getCurrentAffectationCycle();
        return mapper.map(affectationCycle, AffectationCycleBean.class);
    }

    @Override
    public AffectationCycle generateDefaultAffectationCycle(School school, Cycle currentCycle,
                                                            AnneeScolaire anneeScolaire) {
        GroupeAppellation defaultGroupeAppellation = groupeAppellationService.getDefaultGroupeAppellation();
        AffectationCycle affectationCycle = new AffectationCycle(school, currentCycle, anneeScolaire, defaultGroupeAppellation);
        return save(affectationCycle);
    }

    @Override
    public AffectationCycle generateDefaultAffectationCycle(School school, CycleBean cycleBean,
                                                            AnneeScolaire anneeScolaire) {

        Cycle cycle = cycleService.findOne(cycleBean.getId());
        return generateDefaultAffectationCycle(school, cycle, anneeScolaire);
    }

    @Override
    public AffectationCycle getAffectationCycleBySchoolAndCycleAndAnneeScolaire(School school, Cycle cycle, AnneeScolaire anneeScolaire) {
        QAffectationCycle affectationcycle = QAffectationCycle.affectationCycle;
        BooleanExpression expression = affectationcycle.school.eq(school)
                .and(affectationcycle.cycle.eq(cycle))
                .and(affectationcycle.anneeScolaire.eq(anneeScolaire))
                .and(affectationcycle.active.isTrue());

        Iterable<AffectationCycle> affectationCycles = affectationCycleRepository.findAll(expression);
        if (affectationCycles.iterator().hasNext()) {
            return affectationCycles.iterator().next();
        }

        return null;
    }


    @Override
    public List<AffectationCycle> getAffectationsCycleBySchoolAndAnneeScolaire(School school, AnneeScolaire anneeScolaire) {
        QAffectationCycle affectationcycle = QAffectationCycle.affectationCycle;
        BooleanExpression expression = affectationcycle.school.id.eq(school.getId()).and(affectationcycle.anneeScolaire.id.eq(anneeScolaire.getId()))
                .and(affectationcycle.active.isTrue());

        List<AffectationCycle> affectationCycles = (List<AffectationCycle>) affectationCycleRepository.findAll(expression);

        return affectationCycles;
    }


    @Override
    public AffectationCycleBean getAffectationCycleBySchoolCodeAndByCycleIdAndCurrentAnneeScolaire(String schoolCode, Long cycleId) {

        AnneeScolaire currentYear = anneeScolaireService.getCurrentAnneeScolaire();
        if (currentYear != null) {

            QAffectationCycle affectationcycle = QAffectationCycle.affectationCycle;
            BooleanExpression expression = affectationcycle.school.code.eq(schoolCode).and(affectationcycle.cycle.id.eq(cycleId))
                    .and(affectationcycle.active.isTrue());
            return mapper.map(affectationCycleRepository.findOne(expression), AffectationCycleBean.class);

        }
        return null;

    }


    @Override
    public AffectationCycle getAffectationCycleBySchoolIdAndByCycleId(Long schoolId, Long cycleId) {
        QAffectationCycle affectationcycle = QAffectationCycle.affectationCycle;
        BooleanExpression expression = affectationcycle.school.id.eq(schoolId).and(affectationcycle.cycle.id.eq(cycleId))
                .and(affectationcycle.active.isTrue());

        return affectationCycleRepository.findOne(expression);

    }

    @Override
    public AffectationCycle getAffectationCycleByCycleIdAndSchoolIdAndAnneeScolaireId(Long cycleId, Long schoolId, Long anneeScolaireId) {

        Optional<AffectationCycle> affectationCycle = affectationCycleRepository.findByCycleIdAndSchoolIdAndAnneeScolaireId(cycleId, schoolId, anneeScolaireId);
        if (affectationCycle.isPresent()) {
            return affectationCycle.get();
        }
        return null;
    }

    @Override
    public Long save(AffectationCycleBean affectationCycle) {
        validateRequiredFields(affectationCycle);
        Long id = affectationCycle.getId();
        AffectationCycle oldAffectationCycle = findOne(id);
        if (oldAffectationCycle == null) {
            throw new ServiceException(ErrorMessageType.ENTRY_NOT_FOUND);
        }

        generateClasse(oldAffectationCycle, affectationCycle);
        if (!affectationCycle.getGroupeAppellation().getId().equals(oldAffectationCycle.getGroupeAppellation().getId()) || !affectationCycle.getClasseNominationType().equals(oldAffectationCycle.getClasseNominationType())) {
            oldAffectationCycle.setClasseNominationType(affectationCycle.getClasseNominationType());
            oldAffectationCycle.setGroupeAppellation(mapper.map(affectationCycle.getGroupeAppellation(), GroupeAppellation.class));
            renameClass(oldAffectationCycle);
        }
        return save(oldAffectationCycle).getId();
    }

    private void validateRequiredFields(AffectationCycleBean affectationCycle) {
        if (affectationCycle.getId() == null || affectationCycle.getGroupeAppellation() == null || affectationCycle.getGroupeAppellation().getId() == null
                || affectationCycle.getClasseNominationType() == null
                || affectationCycle.getAffectationNiveaux() == null
                ) {
            throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);
        }
        for (AffectationNiveauBean affectationNiveau : affectationCycle.getAffectationNiveaux()) {
            if (affectationNiveau == null || affectationNiveau.getId() == null || affectationNiveau.getNombreDeClasse() == null) {
                throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);
            }
        }
    }

    private void generateClasse(AffectationCycle oldAffectationCycle, AffectationCycleBean affectationCycleBean) {

        oldEntityLoop:
        for (AffectationNiveau oldAffectationNiveau : oldAffectationCycle.getAffectationNiveaux()) {
            for (AffectationNiveauBean affectationNiveau : affectationCycleBean.getAffectationNiveaux()) {
                if (affectationNiveau.getId().equals(oldAffectationNiveau.getId())) {
                    int nombreDeClasse = affectationNiveau.getNombreDeClasse();
                    int oldNombreDeClasse = oldAffectationNiveau.getNombreDeClasse();
                    if (nombreDeClasse > oldNombreDeClasse) {
                        int nombreDeClasseToAdd = nombreDeClasse - oldNombreDeClasse;
                        for (int i = 0; i < nombreDeClasseToAdd; i++) {
                            oldAffectationNiveau.getClasses().add(new Classe(oldAffectationNiveau, oldNombreDeClasse + i + 1));
                        }
                    } else if (nombreDeClasse < oldNombreDeClasse) {
                        int nombreDeClasseToRemove = oldNombreDeClasse - nombreDeClasse;
                        for (int i = 0; i < nombreDeClasseToRemove - 1; i++) {
                            oldAffectationNiveau.getClasses().remove(oldNombreDeClasse - i - 1);
                        }
                    }
                    continue oldEntityLoop;
                }
            }
        }
    }

    private void renameClass(AffectationCycle affectationCycle) {
        Map<Long, String> appellationsByNiveau = new HashMap<>();
        for (Appellation appellation : affectationCycle.getGroupeAppellation().getAppellations()) {
            appellationsByNiveau.put(appellation.getNiveau().getId(), appellation.getLibelle());
        }

        for (AffectationNiveau affectationNiveau : affectationCycle.getAffectationNiveaux()) {
            String niveauName = appellationsByNiveau.get(affectationNiveau.getNiveau().getId());
            int i = 1;

            for (Classe classe : affectationNiveau.getClasses()) {
                String groupeName = ClasseNominationType.ALPHABETIQUE.equals(affectationCycle.getClasseNominationType()) ? String.valueOf((char) (96 + i)) : String.valueOf(i);
                classe.setLibelle(niveauName + "-" + groupeName);
                i++;
            }
        }
    }

}
