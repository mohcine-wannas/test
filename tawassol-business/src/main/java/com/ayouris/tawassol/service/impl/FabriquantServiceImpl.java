package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Fabriquant;
import com.ayouris.tawassol.repository.FabriquantRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.FabriquantService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class FabriquantServiceImpl extends GenericServiceImpl<Fabriquant, Long> implements FabriquantService {

    @Autowired
    private FabriquantRepository fabriquantRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(FabriquantBean fabriquantBean) {
        validateRequiredValues(fabriquantBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Fabriquant entity = (Fabriquant) mapper.map(fabriquantBean, Fabriquant.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(FabriquantBean fabriquantBean){
        if(StringUtils.isBlank(fabriquantBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.FABRIQUANT_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<FabriquantBean> list(PageDataBean paginateData){
        return fabriquantRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public FabriquantBean getDetails(Long id) {
        Fabriquant fabriquant = findOne(id);
        return mapper.map(fabriquant, FabriquantBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.FABRIQUANT);
    }


    @Override
    public List<FabriquantBean> getAll() {
        List<Fabriquant> fabriquants = findAll();
        return mapper.map(fabriquants, FabriquantBean.LIST_BEAN_TYPE);
    }


}
