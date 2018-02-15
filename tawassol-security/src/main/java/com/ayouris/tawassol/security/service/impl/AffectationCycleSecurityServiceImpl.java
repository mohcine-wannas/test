package com.ayouris.tawassol.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.repository.AffectationCycleRepository;
import com.ayouris.tawassol.security.service.AffectationCycleSecurityService;
import com.ayouris.tawassol.security.utils.SecurityUtils;

/**
 * 
 * @author m.wannas
 *
 */
@Service
public class AffectationCycleSecurityServiceImpl extends BaseServiceImpl<AffectationCycle> implements AffectationCycleSecurityService {
    
    @Autowired
    private AffectationCycleRepository affectationCycleRepository;
    @Autowired
    private CustomModelMapper mapper;

	@Override
	public List<CycleBean> getCurrentCycles() {
		Long anneeScolaireId = SecurityUtils.getCurrentAnneeScolaire().getId();
		Long schoolId = SecurityUtils.getCurrentUser().getSchool().getId();
		List<AffectationCycle> affectationCycles = affectationCycleRepository.findBySchoolIdAndAnneeScolaireId(schoolId, anneeScolaireId);
		List<Cycle> cycles = new ArrayList<>();
		for(AffectationCycle affectationCycle : affectationCycles) {
			cycles.add(affectationCycle.getCycle());
		}
		return mapper.map(cycles,CycleBean.LIST_BEAN_TYPE);
	}
	
}
