package ma.salamgaz.tawassol.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.common.model.entity.Cycle;
import ma.salamgaz.tawassol.common.model.entity.QCycle;
import ma.salamgaz.tawassol.common.service.impl.BaseServiceImpl;
import ma.salamgaz.tawassol.repository.CycleRepository;
import ma.salamgaz.tawassol.security.service.CycleSecurityService;

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
