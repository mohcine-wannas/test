package ma.salamgaz.gwic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ma.salamgaz.gwic.admin.model.enums.ViewName;
import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.ControleCamionBean;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.ControleCamion;
import ma.salamgaz.gwic.repository.ControleCamionRepository;
import ma.salamgaz.gwic.service.ColumnDefService;
import ma.salamgaz.gwic.service.ControleCamionService;
import ma.salamgaz.gwic.service.ServiceException;

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
        if(StringUtils.isBlank(controleCamionBean.getControle())) {
         //       || controleCamionBean.getTypeChargement() == null){
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
        List<ControleCamion> controleCamions = findAll();
        return mapper.map(controleCamions, ControleCamionBean.LIST_BEAN_TYPE);
    }


}
