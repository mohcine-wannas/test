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
import com.ayouris.tawassol.common.model.bean.DestinationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.DestinationOL;
import com.ayouris.tawassol.common.model.entity.QDestinationOL;
import com.ayouris.tawassol.repository.DestinationRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.DestinationService;
import com.ayouris.tawassol.service.ServiceException;



@Service
public class DestinationServiceImpl extends GenericServiceImpl<DestinationOL, Long> implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(DestinationBean destinationBean) {
        validateRequiredValues(destinationBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        DestinationOL entity = (DestinationOL) mapper.map(destinationBean, DestinationOL.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(DestinationBean destinationBean){
        if(StringUtils.isBlank(destinationBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.DESTINATION_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<DestinationBean> list(PageDataBean paginateData){
        return destinationRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public DestinationBean getDetails(Long id) {
        DestinationOL destination = findOne(id);
        return mapper.map(destination, DestinationBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.DESTINATION);
    }


    @Override
    public List<DestinationBean> getAll() {
    	Iterable<DestinationOL> destinations = destinationRepository.findAll(QDestinationOL.destinationOL.active.isTrue());
        return mapper.map(destinations, DestinationBean.LIST_BEAN_TYPE);
    }


}
