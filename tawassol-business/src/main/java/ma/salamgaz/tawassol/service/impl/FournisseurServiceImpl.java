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
import ma.salamgaz.tawassol.common.model.bean.FournisseurBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Fournisseur;
import ma.salamgaz.tawassol.repository.FournisseurRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;
import ma.salamgaz.tawassol.service.ServiceException;
import ma.salamgaz.tawassol.service.FournisseurService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class FournisseurServiceImpl extends GenericServiceImpl<Fournisseur, Long> implements FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(FournisseurBean fournisseurBean) {
        validateRequiredValues(fournisseurBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Fournisseur entity = (Fournisseur) mapper.map(fournisseurBean, Fournisseur.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return save(entity).getId();

    }

    private void validateRequiredValues(FournisseurBean fournisseurBean){
        if(StringUtils.isBlank(fournisseurBean.getAbreviation())
                || StringUtils.isBlank(fournisseurBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.FOURNISSEURS_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    public GridListBean<FournisseurBean> list(PageDataBean paginateData){
        return fournisseurRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    public FournisseurBean getDetails(Long id) {
        Fournisseur fournisseur = findOne(id);
        return mapper.map(fournisseur, FournisseurBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.FOURNISSEUR);
    }


    @Override
    public List<FournisseurBean> getAll() {
        List<Fournisseur> fournisseurs = findAll();
        return mapper.map(fournisseurs, FournisseurBean.LIST_BEAN_TYPE);
    }


}
