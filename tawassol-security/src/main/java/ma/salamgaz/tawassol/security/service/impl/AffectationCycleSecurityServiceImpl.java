package ma.salamgaz.tawassol.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.common.model.entity.AffectationCycle;
import ma.salamgaz.tawassol.common.model.entity.Cycle;
import ma.salamgaz.tawassol.common.service.impl.BaseServiceImpl;
import ma.salamgaz.tawassol.repository.AffectationCycleRepository;
import ma.salamgaz.tawassol.security.service.AffectationCycleSecurityService;
import ma.salamgaz.tawassol.security.utils.SecurityUtils;

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
