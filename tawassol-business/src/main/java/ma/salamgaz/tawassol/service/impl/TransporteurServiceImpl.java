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
import ma.salamgaz.tawassol.common.model.bean.TransporteurBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Transporteur;
import ma.salamgaz.tawassol.repository.TransporteurRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.ServiceException;
import ma.salamgaz.tawassol.service.TransporteurService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class TransporteurServiceImpl extends GenericServiceImpl<Transporteur, Long> implements TransporteurService {

    @Autowired
    private TransporteurRepository transporteurRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(TransporteurBean transporteurBean) {
        validateRequiredValues(transporteurBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Transporteur entity = (Transporteur) mapper.map(transporteurBean, Transporteur.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(TransporteurBean transporteurBean){
        if( StringUtils.isBlank(transporteurBean.getAbreviation())
                || StringUtils.isBlank(transporteurBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.TRANSPORTEUR_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<TransporteurBean> list(PageDataBean paginateData){
        return transporteurRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public TransporteurBean getDetails(Long id) {
        Transporteur transporteur = findOne(id);
        return mapper.map(transporteur, TransporteurBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.TRANSPORTEUR);
    }


    @Override
    public List<TransporteurBean> getAll() {
        List<Transporteur> transporteurs = findAll();
        return mapper.map(transporteurs, TransporteurBean.LIST_BEAN_TYPE);
    }


}
