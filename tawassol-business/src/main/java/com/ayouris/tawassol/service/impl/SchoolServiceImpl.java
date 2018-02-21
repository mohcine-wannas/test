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
import com.ayouris.tawassol.security.service.CycleSecurityService;
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
	@Autowired
	private CycleSecurityService cycleService;

	@Override
	public Long update(SchoolBean schoolBean) {
		// TODO validators
		School school = mapper.mapStrict(schoolBean, School.class);
		School oldSchool = findOne(schoolBean.getId());
		school.setVille(oldSchool.getVille());
		school.setPays(oldSchool.getPays());
		school.setId(oldSchool.getId());
		school.setNom(oldSchool.getNom());
		school.setAdresse(oldSchool.getAdresse());
		school.setCode(oldSchool.getCode());
		school.setCodeMassar(oldSchool.getCodeMassar());
		List<AffectationCycle> affectations = new ArrayList<>();
		for (CycleBean cycleBean : schoolBean.getCycles()) {
			AnneeScolaire currentAnneeScolaire = SecurityUtils.getCurrentAnneeScolaire();
			Cycle cycle = new Cycle();
			cycle =cycleService.findOne(cycleBean.getId());
			AffectationCycle affectation = new AffectationCycle(school, cycle, currentAnneeScolaire, null);
			affectations.add(affectation);
		}
		if (oldSchool.getAffectationCycles() != null) {
			for (AffectationCycle affectationCycle : oldSchool.getAffectationCycles()) {
				if (!affectations.contains(affectationCycle)) {
					affectationCycle.setEnabled(false);
				}
			}
		}

		for (AffectationCycle affectationCycle : affectations) {

			if (oldSchool.getAffectationCycles() == null) {
				oldSchool.setAffectationCycles(new ArrayList<AffectationCycle>());
			}

			boolean found = false;
			AffectationCycle founded = null;
			for (AffectationCycle affectationCycle2 : oldSchool.getAffectationCycles()) {
				if (affectationCycle2.equals(affectationCycle)) {
					found = true;
					founded = affectationCycle2;
					break;
				}
			}
			if (!found) {

				oldSchool.getAffectationCycles()
						.add(affectationCycleService.generateDefaultAffectationCycle(affectationCycle.getSchool(),
								affectationCycle.getCycle(), affectationCycle.getAnneeScolaire()));
			} else {
				founded.setEnabled(true);
			}
		}
		school.setAffectationCycles(oldSchool.getAffectationCycles());
		save(school);
		return school.getId();
	}

}
