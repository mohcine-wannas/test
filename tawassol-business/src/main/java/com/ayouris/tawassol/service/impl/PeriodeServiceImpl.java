package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PeriodeBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.entity.QPeriode;
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.repository.PeriodeRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.PeriodeService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class PeriodeServiceImpl extends GenericServiceImpl<Periode, Long> implements PeriodeService {

    @Autowired
    private PeriodeRepository periodeRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(PeriodeBean periodeBean) {


        Periode entity = (Periode) mapper.mapStrict(periodeBean, Periode.class);

        return save(entity).getId();

    }

    @Override
    public void validateRequiredValues(PeriodeBean periodeBean,Periodicite periodicite){
    	
    	switch (periodicite) {
    		case MENSUELLE:
    			if(periodeBean.getStart() == null || periodeBean.getDay() == null 
    				|| periodeBean.getEnd() == null || periodeBean.getTolerance() == null) {
    				throw new ServiceException(ErrorMessageType.PERIODE_MISSING_REQUIRED_VALUES);
    			}
    			break;
	    	case HEBDOMADAIRE:
    			if(periodeBean.getStart() == null || periodeBean.getDay() == null || periodeBean.getEnd() == null) {
    				throw new ServiceException(ErrorMessageType.PERIODE_MISSING_REQUIRED_VALUES);
    			}
	    		break;
	    	case TOUS_LES:
    			if(periodeBean.getFrom() == null || periodeBean.getDaysNumber() == null) {
    				throw new ServiceException(ErrorMessageType.PERIODE_MISSING_REQUIRED_VALUES);
    			}
	    		break;
			default:
				break;
    	}
    }
    
    
	
    @Override
    public GridListBean<PeriodeBean> list(PageDataBean paginateData){
        return periodeRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public PeriodeBean getDetails(Long id) {
        Periode periode = findOne(id);
        return mapper.map(periode, PeriodeBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.PRESTATION);
    }


    @Override
    public List<PeriodeBean> getAll() {
        List<Periode> periodes = findAll();
        return mapper.map(periodes, PeriodeBean.LIST_BEAN_TYPE);
    }

	@Override
	public void removePeriodesByConditionComerciale(ConditionCommerciale conditionCommerciale) {
		periodeRepository.removeByConditionCommercialeId(conditionCommerciale.getId());
	}

	@Override
	@Transactional
	public Periode getPeriodeByConcess(Long id, int day) {
		QPeriode periode = QPeriode.periode;
		return periodeRepository.findOne(periode.conditionCommerciale.id.eq(id)
				.and(periode.start.eq(day)));
	}

}
