package com.ayouris.tawassol.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;


import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.repository.CircuitDerogationRepository;
import com.ayouris.tawassol.service.CircuitDerogationService;
import com.ayouris.tawassol.service.ColumnDefService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.ayouris.tawassol.service.ServiceException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;


/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
@Service
public class CircuitDerogationServiceImpl extends GenericServiceImpl<CircuitDerogation, Long> implements CircuitDerogationService{

    @Autowired
    private CircuitDerogationRepository circuitDerogationRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CircuitDerogationServiceImpl.class);
    @Override
    public CircuitDerogationBean getDetails(Long id) {
        CircuitDerogation circuitDerogation = findOne(id);
        return mapper.map(circuitDerogation, CircuitDerogationBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
        return columnDefService.getColumnDefMapByViewName(ViewName.CIRCUIT_DEROGATION);
    }

    @Override
    public Long createOrUpdate(CircuitDerogationBean circuitDerogationBean) {

        validateRequiredValues(circuitDerogationBean);
        CircuitDerogation entity = mapper.mapStrict(circuitDerogationBean, CircuitDerogation.class);
        try {
            return save(entity).getId();
        }catch (DataIntegrityViolationException ex) {
            throw new ServiceException(ErrorMessageType.COMPOSISION_SITE_OBJET_EXIST);

        }
    }

    @Override
    @JsonView(ListView.class)
    public GridListBean<CircuitDerogationBean> list(PageDataBean paginateData) {
        return circuitDerogationRepository.findEntities(paginateData, getColumnDef());
    }

    @Override
    public List<CircuitDerogationBean> getAll() {
        Iterable<CircuitDerogation> circuitDerogations = findAll();
        return mapper.map(circuitDerogations, CircuitDerogationBean.LIST_BEAN_TYPE);

    }
    private void validateRequiredValues(CircuitDerogationBean circuitDerogationBean) {
        if (circuitDerogationBean.getSite()== null || circuitDerogationBean.getObjet() == null
                || circuitDerogationBean.getUser() == null || circuitDerogationBean.getActive() == null) {

            throw new ServiceException(ErrorMessageType.CIRCUIT_DEROGATION_MISSING_REQUIRED_VALUES);
        }
    }
}