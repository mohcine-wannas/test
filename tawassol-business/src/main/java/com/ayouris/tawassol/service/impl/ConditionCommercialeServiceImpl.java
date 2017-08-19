package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PeriodeBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.repository.ConditionCommercialeRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ConditionCommercialeService;
import com.ayouris.tawassol.service.PeriodeService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ConditionCommercialeServiceImpl extends GenericServiceImpl<ConditionCommerciale, Long> implements ConditionCommercialeService {

    @Autowired
    private ConditionCommercialeRepository conditionCommercialeRepository;

    @Autowired
    private ColumnDefService columnDefService;
    
    @Autowired
    private PeriodeService periodeService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(ConditionCommercialeBean conditionCommercialeBean) {
        validateRequiredValues(conditionCommercialeBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        ConditionCommerciale entity = (ConditionCommerciale) mapper.map(conditionCommercialeBean, ConditionCommerciale.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        ConditionCommerciale savedConditionComerciale = save(entity);
        for (PeriodeBean periodeBean : conditionCommercialeBean.getPeriodes()) {
        	periodeBean.setId(savedConditionComerciale.getId());
        	periodeService.createOrUpdate(periodeBean);
		}
        return savedConditionComerciale.getId();
        
    }

    @Override
    public void validate(ConditionCommercialeBean conditionCommercialeBean) {
    	validateRequiredValues(conditionCommercialeBean);
    	validateCouverture(conditionCommercialeBean.getPeriodes(),conditionCommercialeBean.getPeriodicite());
    }
    
    private  void validateRequiredValues(ConditionCommercialeBean conditionCommercialeBean){
        if(conditionCommercialeBean.getPeriodicite() == null
                ||conditionCommercialeBean.getPieceReglement() == null) {
            throw new ServiceException(ErrorMessageType.CONDITION_COMMERCIALE_MISSING_REQUIRED_VALUES);
        }
        for (PeriodeBean periodeBean : conditionCommercialeBean.getPeriodes()) {
        	periodeService.validateRequiredValues(periodeBean,conditionCommercialeBean.getPeriodicite());
		}
    }

    private void validateCouverture(List<PeriodeBean> periodeBeans,Periodicite periodicite){
    	Integer max = 28;
    	switch (periodicite) {
    		case MENSUELLE:
    			max = 28;
    			break;
	    	case HEBDOMADAIRE:
	    		 max = 7;
	    		break;
	    	case TOUS_LES:
	    		max = 1;
	    		break;
			default:
				max = 0;
				break;
    	}
    	if(periodeBeans.size() > max) 
    		throw new ServiceException(ErrorMessageType.CONDITION_COMMERCIALE_PERIODE_NOT_VALID);
		if(max > 1) 
			validateCouverture(periodeBeans, max);
    }

	private void validateCouverture(List<PeriodeBean> periodeBeans, Integer max)  {
		List<Integer> result = new ArrayList<Integer>();
		for (PeriodeBean periode : periodeBeans) 
			putDays(periode.getStart(),periode.getEnd(),max,result);
		if(result.size() != max) 
			throw new ServiceException(ErrorMessageType.CONDITION_COMMERCIALE_PERIODE_NOT_COVERED);
	}

	private static List<Integer> putDays(int from, int to, int max,List<Integer> result)  {
		if(from < 1 || to < 1 || from > max || to > max ) 
			throw new ServiceException(ErrorMessageType.CONDITION_COMMERCIALE_PERIODE_NOT_VALID);
		
		List<Integer> days = new ArrayList<Integer>();
		if(from <= to) 
			for(int day = from; day<= to; day++) 
				putDay(day,result);
		else {
			for(int day = from; day<= max; day++) 
				putDay(day,result);
			for(int day = 1; day<= to; day++) 
				putDay(day,result);
		}
		
		return days;
	}

	private static void putDay(int day, List<Integer> result){
		if(result.contains(day))
			throw new ServiceException(ErrorMessageType.CONDITION_COMMERCIALE_PERIODE_NOT_VALID);
		else 
			result.add(day);
	}

    @Override
    public GridListBean<ConditionCommercialeBean> list(PageDataBean paginateData){
        return conditionCommercialeRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public ConditionCommercialeBean getDetails(Long id) {
        ConditionCommerciale conditionCommerciale = findOne(id);
        return mapper.map(conditionCommerciale, ConditionCommercialeBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CONDITION_COMMERCIALE);
    }

    @Override
    public List<ConditionCommercialeBean> getAll() {
        List<ConditionCommerciale> conditionCommerciales = findAll();
        return mapper.map(conditionCommerciales, ConditionCommercialeBean.LIST_BEAN_TYPE);
    }

	@Override
	@Transactional
	public void savePeriodes(ConditionCommerciale conditionCommerciale) {
		periodeService.removePeriodesByConditionComerciale(conditionCommerciale);
        for (Periode periode : conditionCommerciale.getPeriodes()) {
        	periode.setConditionCommerciale(conditionCommerciale);
        	periodeService.save(periode);
		}
	}

}
