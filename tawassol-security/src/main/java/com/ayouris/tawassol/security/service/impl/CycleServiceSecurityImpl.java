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
import com.querydsl.core.types.OrderSpecifier;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class CycleServiceSecurityImpl extends BaseServiceImpl<Cycle> implements CycleSecurityService {


	private final OrderSpecifier<Integer> ORDER_SPECIFIER = QCycle.cycle.order.asc();


	@Autowired
    private CustomModelMapper mapper;
 
    private CycleRepository cycleRepository;
    
    @Autowired
    CycleServiceSecurityImpl(CycleRepository cycleRepository) {
		super(cycleRepository);
		this.cycleRepository = cycleRepository;
	}
    
    @Override
    public List<CycleBean> getAll() {
        Iterable<Cycle> cycles = cycleRepository.findAll(QCycle.cycle.active.isTrue(),ORDER_SPECIFIER);
        return mapper.map(cycles, CycleBean.LIST_BEAN_TYPE);
    }

}
