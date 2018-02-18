package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.School;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.SchoolService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class SchoolServiceImpl extends GenericServiceImpl2<School, Long, SchoolBean> implements SchoolService {

	@Autowired
	private CustomModelMapper mapper;
	@Autowired
	private AffectationCycleService affectationCycleService;
	
	@Override
	public Long update(SchoolBean schoolBean) {
		//TODO validators
		School school = mapper.mapStrict(schoolBean, School.class);
		School oldSchool  = findOne(schoolBean.getId());
		school.setVille(oldSchool.getVille());
		school.setPays(oldSchool.getPays());
		school.setId(oldSchool.getId());
		school.setNom(oldSchool.getNom());
		school.setAdresse(oldSchool.getAdresse());
		school.setCode(oldSchool.getCode());
		school.setCodeMassar(oldSchool.getCodeMassar());
		List<AffectationCycle> affectations = new ArrayList<>();
		for(CycleBean cycleBean: schoolBean.getCycles()) {
			AnneeScolaire currentAnneeScolaire = SecurityUtils.getCurrentAnneeScolaire();
			AffectationCycle affectation = affectationCycleService.generateDefaultAffectationCycle(school,cycleBean,currentAnneeScolaire);
			affectations.add(affectation);
		}
		school.setAffectationCycles(affectations);
		save(school);
		return school.getId();
	}



	
}
