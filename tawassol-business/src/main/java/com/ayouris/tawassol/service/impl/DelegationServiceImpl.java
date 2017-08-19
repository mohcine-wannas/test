package com.ayouris.tawassol.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.DelegationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Delegation;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.repository.DelegationRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.DelegationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.ayouris.tawassol.service.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by Issmahane EL BAZ on 14/07/2017.
 */
@Service
public class DelegationServiceImpl extends GenericServiceImpl<Delegation, Long> implements DelegationService {

    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(DelegationServiceImpl.class);

    @Override
    public DelegationBean getDetails(Long id) {
        Delegation delegation = findOne(id);
        return mapper.map(delegation, DelegationBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
        return columnDefService.getColumnDefMapByViewName(ViewName.DELEGATION);
    }

    @Override
    public Long createOrUpdate(DelegationBean delegationBean) {

        validateRequiredValues(delegationBean);
        Delegation entity = mapper.mapStrict(delegationBean, Delegation.class);
        try {
            return save(entity).getId();
        }catch (DataIntegrityViolationException ex) {
            LOGGER.error(ex.getMessage());
            throw ex;

        }
    }

    @Override
    @JsonView(ListView.class)
    public GridListBean<DelegationBean> list(PageDataBean paginateData) {
        return delegationRepository.findEntities(paginateData, getColumnDef());
    }

    @Override
    public List<DelegationBean> getAll() {
        Iterable<Delegation> delegations = findAll();
        return mapper.map(delegations, DelegationBean.LIST_BEAN_TYPE);

    }
    private void validateRequiredValues(DelegationBean delegationBean) {
        if (delegationBean.getObjet()== null || delegationBean.getUser() == null
                || delegationBean.getDu() == null || delegationBean.getAu() == null) {

            throw new ServiceException(ErrorMessageType.DELEGATION_MISSING_REQUIRED_VALUES);
        }
    }
}
