package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.AffectationRepository;
import com.ayouris.tawassol.service.AffectationService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AffectationServiceImpl extends GenericServiceImpl2<Affectation, Long,AffectationBean> implements AffectationService {

    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long createOrUpdate(AffectationBean affectationBean) {
        validateRequiredValues(affectationBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Affectation entity = (Affectation) mapper.map(affectationBean, Affectation.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	ex.printStackTrace();
        	throw new ServiceException(ErrorMessageType.AFFECTATION_CODE_ALREADY_EXISTS);
        }
    }

    private void validateRequiredValues(AffectationBean affectationBean){
        if( affectationBean.getClient() ==null
                || affectationBean.getSite() ==null
                || affectationBean.getProduit() ==null
                ){
            throw new ServiceException(ErrorMessageType.AFFECTATION_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    @Transactional
    public GridListBean<AffectationBean> list(PageDataBean paginateData){
        return list(paginateData,ViewName.FOURNISSEUR);
    }

    @Override
    @Transactional
    public AffectationBean getDetails(Long id) {
        Affectation affectation = findOne(id);
        return mapper.map(affectation, AffectationBean.class);
    }



    @Override
    @Transactional
    public List<AffectationBean> getAll() {
        List<Affectation> affectations = findAll();
        return mapper.map(affectations, AffectationBean.LIST_BEAN_TYPE);
    }

    @Override
    @Transactional
    public List<AffectationBean> findBySiteIdAndClientIdAndConcessionnaireIdAndProduitTypeChargement(Long siteId, Long clientId, Long concessionnaireId,TypeChargement typeChargement) {
    	
        List<Affectation> affectationsTemp = affectationRepository.findBySiteIdAndClientIdAndConcessionnaireIdAndProduitTypeChargement(siteId,clientId,concessionnaireId,typeChargement);
        List<Affectation> affectations = new ArrayList<Affectation>();

        Date date = new Date();
        affectationsTemp.forEach((affectation) -> {
        	if(affectation.getLimiter() != null && affectation.getLimiter()){
        		if(affectation.getDateDebut().before(date) && affectation.getDateFin().after(date) ) {
        			affectations.add(affectation);
        		}
        	}else{
        		affectations.add(affectation);
        	}
        });
        return mapper.map(affectations, AffectationBean.LIST_BEAN_TYPE);
    }
}
