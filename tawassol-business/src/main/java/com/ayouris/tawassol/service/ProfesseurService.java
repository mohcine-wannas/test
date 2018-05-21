package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.ProfesseurBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurEditBean;
import com.ayouris.tawassol.common.model.entity.Professeur;

import java.util.List;
import java.util.Map;

public interface ProfesseurService extends GenericService<Professeur, Long> {

    Boolean validateCodeSchool(String codeSchool);

    Long updateProfNivauxClasses(Long id, ProfesseurBean professeur);

    Long updateProfUnites(Long id, ProfesseurBean professeur);

    Long create(ProfesseurBean professeur);

    List<ProfesseurEditBean> getAllByCurrentSchool();

    List<ProfesseurEditBean> getAllByCycleIdAndCurrentSchoolAndCurrentAnneScolaire(Long cycleId);

    void enableProf(Long id, Boolean enable);

    void enableAllProf(Boolean enable);

    void autoSendProf(Long id, Boolean autoSend);

    void delete(Long id);

    Long passwordChange(Long id, Map<String, String> bean);
}
