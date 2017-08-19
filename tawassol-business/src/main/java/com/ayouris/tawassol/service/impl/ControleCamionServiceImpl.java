package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ControleCamionBean;
import com.ayouris.tawassol.common.model.bean.ControleCamionConformityBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ControleCamion;
import com.ayouris.tawassol.common.model.entity.QControleCamion;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.ControleCamionRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ControleCamionService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ControleCamionServiceImpl extends GenericServiceImpl<ControleCamion, Long> implements ControleCamionService {

    @Autowired
    private ControleCamionRepository controleCamionRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(ControleCamionBean controleCamionBean) {
        validateRequiredValues(controleCamionBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        ControleCamion entity = (ControleCamion) mapper.map(controleCamionBean, ControleCamion.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CONTROLE_CAMION_CODE_ALREADY_EXISTS);
        }
    }

    private void validateRequiredValues(ControleCamionBean controleCamionBean){
        if(StringUtils.isBlank(controleCamionBean.getControle())|| controleCamionBean.getTypeChargement() == null){
            throw new ServiceException(ErrorMessageType.CONTROLE_CAMION_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<ControleCamionBean> list(PageDataBean paginateData){
        return controleCamionRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public ControleCamionBean getDetails(Long id) {
        ControleCamion controleCamion = findOne(id);
        return mapper.map(controleCamion, ControleCamionBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CONTROLE_CAMION);
    }

    @Override
    public List<ControleCamionBean> getAll() {
    	Iterable<ControleCamion> controleCamions = controleCamionRepository.findAll(QControleCamion.controleCamion.active.isTrue());
        return mapper.map(controleCamions, ControleCamionBean.LIST_BEAN_TYPE);
    }


	@Override
	public List<ControleCamionConformityBean> getControleCamionConformitiesBeans(TypeChargement typeChargement) {
		QControleCamion qControleCamion = QControleCamion.controleCamion;
		
		BooleanExpression predicat = qControleCamion.typeChargement.eq(typeChargement).and(qControleCamion.active.isTrue());
		Iterable<ControleCamion> controleCamions = controleCamionRepository.findAll(predicat);
		
		List<ControleCamionBean> controleCamionsBeans = mapper.map(controleCamions, ControleCamionBean.LIST_BEAN_TYPE);
		List<ControleCamionConformityBean> controleCamionsConformities = new ArrayList<ControleCamionConformityBean>();
		for(ControleCamionBean controleCamion : controleCamionsBeans ) {
			ControleCamionConformityBean ccc = new ControleCamionConformityBean();
			ccc.setControleCamion(controleCamion);
			ccc.setConforme(null);
			controleCamionsConformities.add(ccc);
		}
		return controleCamionsConformities;
	}


}
