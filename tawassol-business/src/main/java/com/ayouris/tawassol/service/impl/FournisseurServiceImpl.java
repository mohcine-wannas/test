package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.FournisseurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Fournisseur;
import com.ayouris.tawassol.service.FournisseurService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class FournisseurServiceImpl extends GenericServiceImpl2<Fournisseur, Long, FournisseurBean>  implements FournisseurService {

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
        return list(paginateData,ViewName.FOURNISSEUR);
    }
    

    @Override
    public FournisseurBean getDetails(Long id) {
        Fournisseur fournisseur = findOne(id);
        return mapper.map(fournisseur, FournisseurBean.class);
    }



    @Override
    public List<FournisseurBean> getAll() {
        List<Fournisseur> fournisseurs = findAll();
        return mapper.map(fournisseurs, FournisseurBean.LIST_BEAN_TYPE);
    }


}
