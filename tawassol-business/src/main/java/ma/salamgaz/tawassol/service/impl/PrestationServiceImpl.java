package ma.salamgaz.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;
import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.bean.PrestationBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Prestation;
import ma.salamgaz.tawassol.repository.PrestationRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.ServiceException;
import ma.salamgaz.tawassol.service.PrestationService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class PrestationServiceImpl extends GenericServiceImpl<Prestation, Long> implements PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(PrestationBean prestationBean) {
        validateRequiredValues(prestationBean);
        if(prestationBean.getLivree() == null || !prestationBean.getLivree()) {
        	 prestationBean.setControlee(false);
        }
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Prestation entity = (Prestation) mapper.map(prestationBean, Prestation.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(PrestationBean prestationBean){
        if(StringUtils.isBlank(prestationBean.getLibelleLivraison())
                || StringUtils.isBlank(prestationBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.PRESTATION_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<PrestationBean> list(PageDataBean paginateData){
        return prestationRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public PrestationBean getDetails(Long id) {
        Prestation prestation = findOne(id);
        return mapper.map(prestation, PrestationBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.PRESTATION);
    }


    @Override
    public List<PrestationBean> getAll() {
        List<Prestation> prestations = findAll();
        return mapper.map(prestations, PrestationBean.LIST_BEAN_TYPE);
    }


}
