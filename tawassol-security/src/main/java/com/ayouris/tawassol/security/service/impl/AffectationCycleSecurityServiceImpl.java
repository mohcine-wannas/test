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
    

	private AffectationCycleRepository affectationCycleRepository;
    @Autowired
    private CustomModelMapper mapper;
    
    @Autowired
    AffectationCycleSecurityServiceImpl(AffectationCycleRepository affectationCycleRepository) {
		super(affectationCycleRepository);
		this.affectationCycleRepository = affectationCycleRepository;
	}
    

	@Override
	public List<CycleBean> getCurrentCycles() {
		Long anneeScolaireId = SecurityUtils.getCurrentAnneeScolaire().getId();
		Long schoolId = SecurityUtils.getCurrentUser().getSchool().getId();
		List<AffectationCycle> affectationCycles = affectationCycleRepository.findBySchoolIdAndAnneeScolaireIdOrderByOrderAsc(schoolId, anneeScolaireId);
		List<Cycle> cycles = new ArrayList<>();
		for(AffectationCycle affectationCycle : affectationCycles) {
			if(affectationCycle.getCycle().getActive() == null || !affectationCycle.getCycle().getActive()) {
				continue;
			}
			if(affectationCycle.getEnabled() == null || !affectationCycle.getEnabled() ) {
				continue;
			}
			cycles.add(affectationCycle.getCycle());
		}
		cycles.sort((e,e2) -> { return e.getOrder().compareTo(e2.getOrder()); });
		return mapper.map(cycles,CycleBean.LIST_BEAN_TYPE);
	}
		
	
}
