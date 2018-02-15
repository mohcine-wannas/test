package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CiterneBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Citerne;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.repository.CiterneRepository;
import com.ayouris.tawassol.service.CiterneService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class CiterneServiceImpl extends GenericServiceImpl<Citerne, Long> implements CiterneService {

    @Autowired
    private CiterneRepository citerneRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(CiterneBean citerneBean) {
        validateRequiredValues(citerneBean);
        
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Citerne entity = (Citerne) mapper.map(citerneBean, Citerne.class);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CITERNE_CODE_ALREADY_EXISTS);
        }

    }

    private void validateRequiredValues(CiterneBean citerneBean){
        if(StringUtils.isBlank(citerneBean.getCode())
                || StringUtils.isBlank(citerneBean.getPressionService())
                || StringUtils.isBlank(citerneBean.getNumFabrication())
                || citerneBean.getTransporteur() == null
                || citerneBean.getFabriquant() == null
                || citerneBean.getDateFabrication() == null
                || citerneBean.getDateReepreuve() == null
                || citerneBean.getAcceptePropane() == null
                || citerneBean.getAccepteButane() == null
                || citerneBean.getVolume() == null){

			throw new ServiceException(ErrorMessageType.CITERNE_MISSING_REQUIRED_VALUES);

		} else if (citerneBean.getAcceptePropane() && citerneBean.getCapacitePropane() == null
				|| citerneBean.getAccepteButane() && citerneBean.getCapaciteButane() == null) {
			
			throw new ServiceException(ErrorMessageType.CITERNE_MISSING_REQUIRED_VALUES);
		}
	}

    @Override
    public GridListBean<CiterneBean> list(PageDataBean paginateData){
        return citerneRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public CiterneBean getDetails(Long id) {
        Citerne citerne = findOne(id);
        return mapper.map(citerne, CiterneBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CITERNE);
    }


    @Override
    public List<CiterneBean> getAll() {
        List<Citerne> citernes = findAll();
        return mapper.map(citernes, CiterneBean.LIST_BEAN_TYPE);
    }

}
