package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationNiveauClasseProfBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurEditBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.repository.ProfesseurRepository;
import com.ayouris.tawassol.security.service.PasswordService;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.ProfesseurService;
import com.ayouris.tawassol.service.SchoolService;
import com.ayouris.tawassol.service.ServiceException;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProfesseurServiceImpl extends GenericServiceImpl2<Professeur, Long, ProfesseurBean> implements ProfesseurService {

    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private AffectationCycleService affectationCycleService;
    @Autowired
    private PasswordService passwordService;

    @Override
    public Boolean validateCodeSchool(String codeSchool) {
        School school = schoolService.findByCode(codeSchool);
        return school != null;
    }

    @Override
    public void enableProf(Long id, Boolean enable) {
        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        prof.setEnabled(enable);
        save(prof);

    }

    @Override
    public Long passwordChange(Long id, Map<String, String> bean) {
        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }
        return passwordService.passwordChange(prof, bean);
    }

    @Override
    public void delete(Long id) {
        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        delete(prof);
    }


    @Override
    public void enableAllProf(Boolean enable) {

        QProfesseur qProfesseur = QProfesseur.professeur;
        List<Professeur> professeurs = (List<Professeur>) professeurRepository.findAll(qProfesseur.school.id.eq(SecurityUtils.getCurrentSchool().getId()));
        for (Professeur prof : professeurs) {
            prof.setEnabled(enable);
            save(prof);
        }

    }

    @Override
    public void autoSendProf(Long id, Boolean autoSend) {
        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

//        prof.set(autoSend);
//        save(prof);
    }

    @Override
    public List<ProfesseurEditBean> getAllByCurrentSchool() {
        QProfesseur qProfesseur = QProfesseur.professeur;
        List<Professeur> professeurs = (List<Professeur>) professeurRepository.findAll(qProfesseur.school.id.eq(SecurityUtils.getCurrentSchool().getId()),
                new OrderSpecifier<>(Order.ASC, qProfesseur.enabled),
                new OrderSpecifier<>(Order.DESC, qProfesseur.createdOn));

        return mapper.map(professeurs, ProfesseurEditBean.LIST_BEAN_TYPE);

    }


    @Override
    public List<ProfesseurEditBean> getAllByCycleIdAndCurrentSchoolAndCurrentAnneScolaire(Long cycleId) {
        QProfesseur qProfesseur = QProfesseur.professeur;

        AnneeScolaire currentAnneeScolaire = SecurityUtils.getCurrentAnneeScolaire();
        School currentSchool = SecurityUtils.getCurrentSchool();

        AffectationCycle affectationCycle = affectationCycleService.getAffectationCycleByCycleIdAndSchoolIdAndAnneeScolaireId(cycleId, currentSchool.getId(), currentAnneeScolaire.getId());
        List<Professeur> professeurs = new ArrayList<>();
        if (affectationCycle != null) {
            professeurs = (List<Professeur>) professeurRepository.findAll(qProfesseur.affectationCycle.id.eq(affectationCycle.getId()),
                    new OrderSpecifier<>(Order.ASC, qProfesseur.enabled),
                    new OrderSpecifier<>(Order.DESC, qProfesseur.createdOn));
        } else {
            throw new ServiceException(ErrorMessageType.AFFECTATION_CYCLE_NOT_FOUND);
        }

        return mapper.map(professeurs, ProfesseurEditBean.LIST_BEAN_TYPE);

    }


    @Override
    public Long updateProfNivauxClasses(Long id, ProfesseurBean professeur) {
        validateAffectationsRequiredValue(professeur);
        validateAffectationNiveauClasse(professeur);

        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        Professeur entity = mapper.mapStrict(professeur, Professeur.class);
        prof.getAffectationsNiveauClasseProf().clear();

        for (AffectationNiveauClasseProf affectation : entity.getAffectationsNiveauClasseProf()) {
            affectation.setProfesseur(prof);
            prof.getAffectationsNiveauClasseProf().add(affectation);
        }

        return save(prof).getId();
    }

    private void validateAffectationsRequiredValue(ProfesseurBean professeur) {
        if (professeur.getAffectationsNiveauClasseProf().isEmpty()) {
            throw new ServiceException(ErrorMessageType.AFFECTATION_PROF_NIVEAU_EMPTY);
        } else {
            for (AffectationNiveauClasseProfBean affectation : professeur.getAffectationsNiveauClasseProf()) {
                if (affectation.getClasse() == null || affectation.getNiveau() == null) {
                    throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);
                }
            }
        }
    }

    @Override
    public Long updateProfUnites(Long id, ProfesseurBean professeur) {
        if (professeur.getAffectationsUniteProf().isEmpty()) {
            throw new ServiceException(ErrorMessageType.AFFECTATION_PROF_UNITE_EMPTY);
        }

        Professeur prof = findOne(id);
        if (prof == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        Professeur entity = mapper.mapStrict(professeur, Professeur.class);
        prof.getAffectationsUniteProf().clear();

        for (AffectationUniteProf affectation : entity.getAffectationsUniteProf()) {
            affectation.setProfesseur(prof);
            prof.getAffectationsUniteProf().add(affectation);
        }

        return save(prof).getId();
    }

    @Override
    public Long create(ProfesseurBean professeur) {
        validateRequiredFields(professeur);
        validateAffectationsRequiredValue(professeur);
        validateAffectationNiveauClasse(professeur);

        QProfesseur qProfesseur = QProfesseur.professeur;
        Professeur prof = professeurRepository.findOne(qProfesseur.username.eq(professeur.getPhoneNumber()));
        if (prof != null) {
            throw new ServiceException(ErrorMessageType.PROF_ALREADY_EXISTE);
        }

        Professeur entity = mapper.mapStrict(professeur, Professeur.class);

        entity.setPassword(passwordService.encodePassword(professeur.getPassword()));
        entity.setUsername(professeur.getPhoneNumber());
        entity.setEnabled(false);
        School school = schoolService.findByCode(professeur.getSchoolCode());
        entity.setSchool(school);

        AffectationCycle affectationCycle = affectationCycleService.getAffectationCycleBySchoolIdAndByCycleId(school.getId(),
                professeur.getCycle().getId());

        entity.setAffectationCycle(affectationCycle);


        for (AffectationNiveauClasseProf affectation : entity.getAffectationsNiveauClasseProf()) {
            affectation.setProfesseur(entity);
        }
        for (AffectationUniteProf affectation : entity.getAffectationsUniteProf()) {
            affectation.setProfesseur(entity);
        }

        return save(entity).getId();
    }

    private void validateRequiredFields(ProfesseurBean professeur) {

        if (StringUtils.isBlank(professeur.getFirstname()) || StringUtils.isBlank(professeur.getLastname())
                || StringUtils.isBlank(professeur.getPhoneNumber()) || StringUtils.isBlank(professeur.getPassword())
                || StringUtils.isBlank(professeur.getSchoolCode()) || professeur.getCycle() == null) {
            throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);

        }

        if (professeur.getAffectationsUniteProf().isEmpty()) {
            throw new ServiceException(ErrorMessageType.AFFECTATION_PROF_UNITE_EMPTY);
        }

        validateAffectationsRequiredValue(professeur);

    }

    private void validateAffectationNiveauClasse(ProfesseurBean professeur) {
        int count;
        for (AffectationNiveauClasseProfBean affectation : professeur.getAffectationsNiveauClasseProf()) {
            count = 0;
            for (AffectationNiveauClasseProfBean affectation2 : professeur.getAffectationsNiveauClasseProf()) {
                if (affectation.getNiveau().getId().equals(affectation2.getNiveau().getId())
                        && affectation.getClasse().getId().equals(affectation2.getClasse().getId())) {
                    count++;
                }
            }
            if (count > 1) {
                throw new ServiceException(ErrorMessageType.AFFECTATION_PROF_NIVEAU_DUPLICATE);
            }
        }

    }

}
