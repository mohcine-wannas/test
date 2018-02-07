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
import ma.salamgaz.tawassol.common.model.bean.MarqueBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Marque;
import ma.salamgaz.tawassol.repository.MarqueRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.MarqueService;
import ma.salamgaz.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class MarqueServiceImpl extends GenericServiceImpl<Marque, Long> implements MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(MarqueBean marqueBean) {
        validateRequiredValues(marqueBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Marque entity = (Marque) mapper.map(marqueBean, Marque.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(MarqueBean marqueBean){
        if(StringUtils.isBlank(marqueBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.MARQUE_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<MarqueBean> list(PageDataBean paginateData){
        return marqueRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public MarqueBean getDetails(Long id) {
        Marque marque = findOne(id);
        return mapper.map(marque, MarqueBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.MARQUE);
    }


    @Override
    public List<MarqueBean> getAll() {
        List<Marque> marques = findAll();
        return mapper.map(marques, MarqueBean.LIST_BEAN_TYPE);
    }


}
