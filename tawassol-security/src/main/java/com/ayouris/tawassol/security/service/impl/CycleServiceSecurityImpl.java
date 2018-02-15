package com.ayouris.tawassol.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.QCycle;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.repository.CycleRepository;
import com.ayouris.tawassol.security.service.CycleSecurityService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class CycleServiceSecurityImpl extends BaseServiceImpl<Cycle> implements CycleSecurityService {


    @Autowired
    private CustomModelMapper mapper;
    
    @Autowired
    private CycleRepository cycleRepository;
    
    @Override
    public List<CycleBean> getAll() {
        Iterable<Cycle> cycles = cycleRepository.findAll(QCycle.cycle.active.isTrue());
        return mapper.map(cycles, CycleBean.LIST_BEAN_TYPE);
    }

}
