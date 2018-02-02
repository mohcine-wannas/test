package ma.salamgaz.gwic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.salamgaz.gwic.admin.model.enums.ViewName;
import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.bean.FabriquantBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.Fabriquant;
import ma.salamgaz.gwic.repository.FabriquantRepository;
import ma.salamgaz.gwic.service.ColumnDefService;
import ma.salamgaz.gwic.service.ServiceException;
import ma.salamgaz.gwic.service.FabriquantService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class FabriquantServiceImpl extends GenericServiceImpl<Fabriquant, Long> implements FabriquantService {

    @Autowired
    private FabriquantRepository fabriquantRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(FabriquantBean fabriquantBean) {
        validateRequiredValues(fabriquantBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Fabriquant entity = (Fabriquant) mapper.map(fabriquantBean, Fabriquant.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(FabriquantBean fabriquantBean){
        if(StringUtils.isBlank(fabriquantBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.FABRIQUANT_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<FabriquantBean> list(PageDataBean paginateData){
        return fabriquantRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public FabriquantBean getDetails(Long id) {
        Fabriquant fabriquant = findOne(id);
        return mapper.map(fabriquant, FabriquantBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.FABRIQUANT);
    }


    @Override
    public List<FabriquantBean> getAll() {
        List<Fabriquant> fabriquants = findAll();
        return mapper.map(fabriquants, FabriquantBean.LIST_BEAN_TYPE);
    }


}
