package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Camion;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QCamion;
import com.ayouris.tawassol.repository.CamionRepository;
import com.ayouris.tawassol.service.CamionService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class CamionServiceImpl extends GenericServiceImpl<Camion, Long> implements CamionService {

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(CamionBean camionBean) {
        validateRequiredValues(camionBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Camion entity = (Camion) mapper.map(camionBean, Camion.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CAMION_NUMERO_ALREADY_EXISTS);
        }

    }

    private void validateRequiredValues(CamionBean camionBean){
        if(StringUtils.isBlank(camionBean.getMatricule())
                ||camionBean.getMarque() == null
                || camionBean.getTypeCamion() == null){
            throw new ServiceException(ErrorMessageType.CAMION_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<CamionBean> list(PageDataBean paginateData){
        return camionRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    @Transactional
    public CamionBean getDetails(Long id) {
        Camion camion = findOne(id);
        return mapper.map(camion, CamionBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CAMION);
    }

    @Override
    @Transactional
    public List<CamionBean> getAll() {
        Iterable<Camion> camions = camionRepository.findAll(QCamion.camion.active.isTrue());
        return mapper.map(camions, CamionBean.LIST_BEAN_TYPE);
    }

	@Override
	@Transactional
	public List<CamionBean> findByConcessionnaireId(Long id) {
		List<Camion> camions = camionRepository.findByConcessionnaireIdAndActiveTrue(id);
		return mapper.map(camions, CamionBean.LIST_BEAN_TYPE);
	}
	
}
