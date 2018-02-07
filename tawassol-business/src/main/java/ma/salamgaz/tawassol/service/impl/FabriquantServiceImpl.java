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
import ma.salamgaz.tawassol.common.model.bean.FabriquantBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Fabriquant;
import ma.salamgaz.tawassol.repository.FabriquantRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.ServiceException;
import ma.salamgaz.tawassol.service.FabriquantService;

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
