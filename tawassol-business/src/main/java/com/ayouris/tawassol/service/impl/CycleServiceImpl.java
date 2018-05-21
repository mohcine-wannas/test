package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.School;
import com.ayouris.tawassol.repository.CycleRepository;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.CycleService;
import com.ayouris.tawassol.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CycleServiceImpl extends GenericServiceImpl2<Cycle, Long, CycleBean> implements CycleService {

    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AffectationCycleService affectationCycleService;

    public Boolean validateCodeSchool(String codeSchool) {
        School school = schoolService.findByCode(codeSchool);
        return school != null;
    }

    @Override
    public List<CycleBean> getAllBySchoolCode(String schoolCode) {
        School school = schoolService.findByCode(schoolCode);
        List<Cycle> cycles = new ArrayList<>();

        if (school != null) {
            List<AffectationCycle> affectations = affectationCycleService.getAffectationsCycleBySchool(school);
            affectations.forEach(affectationCycle -> {
                cycles.add(affectationCycle.getCycle());
            });
        }
        return mapper.map(cycles, CycleBean.LIST_BEAN_TYPE);
    }
}
