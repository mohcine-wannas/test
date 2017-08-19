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
import com.ayouris.tawassol.common.model.bean.TransporteurBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Transporteur;
import com.ayouris.tawassol.repository.TransporteurRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.TransporteurService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class TransporteurServiceImpl extends GenericServiceImpl<Transporteur, Long> implements TransporteurService {

    @Autowired
    private TransporteurRepository transporteurRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(TransporteurBean transporteurBean) {
        validateRequiredValues(transporteurBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Transporteur entity = (Transporteur) mapper.map(transporteurBean, Transporteur.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(TransporteurBean transporteurBean){
        if( StringUtils.isBlank(transporteurBean.getAbreviation())
                || StringUtils.isBlank(transporteurBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.TRANSPORTEUR_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<TransporteurBean> list(PageDataBean paginateData){
        return transporteurRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public TransporteurBean getDetails(Long id) {
        Transporteur transporteur = findOne(id);
        return mapper.map(transporteur, TransporteurBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.TRANSPORTEUR);
    }


    @Override
    public List<TransporteurBean> getAll() {
        List<Transporteur> transporteurs = findAll();
        return mapper.map(transporteurs, TransporteurBean.LIST_BEAN_TYPE);
    }


}
