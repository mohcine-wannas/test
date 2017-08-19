package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.model.entity.Concessionnaire;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.entity.Produit;
import com.ayouris.tawassol.common.model.entity.QConcessionnaire;
import com.ayouris.tawassol.repository.ConcessionnaireRepository;
import com.ayouris.tawassol.repository.ConditionCommercialeRepository;
import com.ayouris.tawassol.repository.PeriodeRepository;
import com.ayouris.tawassol.service.CamionService;
import com.ayouris.tawassol.service.ChauffeurService;
import com.ayouris.tawassol.service.ConcessionnaireService;
import com.ayouris.tawassol.service.ConditionCommercialeService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SignataireService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ConcessionnaireServiceImpl extends GenericServiceImpl2<Concessionnaire, Long, ConcessionnaireBean> implements ConcessionnaireService {

    @Autowired
    private ConcessionnaireRepository concessionnaireRepository;
    
    @Autowired
    private ChauffeurService chauffeurService;
    
    @Autowired
    private CamionService camionService;
    
    @Autowired
    private SignataireService signataireService;

    @Autowired
    private ConditionCommercialeService conditionCommercialeService;

    @Autowired
    private CustomModelMapper mapper;


    @Autowired
    private PeriodeRepository periodeRepository;

    @Override
    public Long createOrUpdate(ConcessionnaireBean concessionnaireBean) {
        validateRequiredValues(concessionnaireBean);
        removeCondition(concessionnaireBean);
        
        Concessionnaire entity = mapper.mapStrict(concessionnaireBean, Concessionnaire.class);
        
        try {

            Concessionnaire savedConcessionnaire =  save(entity);
        	if(savedConcessionnaire.getConditionCommerciale() != null) {
        		savedConcessionnaire.getConditionCommerciale().setId(savedConcessionnaire.getConditionCommerciale().getId());
        		conditionCommercialeService.savePeriodes(savedConcessionnaire.getConditionCommerciale());
        	}
        	return savedConcessionnaire.getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CONCESSIONNAIRE_CODE_ALREADY_EXISTS);
        }

    }

	@Override
	public void remove(Long id) {
        try {
        	delete(id);
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CONCESSIONNAIRE_ALREADY_USED);
        }
	}
	
    private void validateRequiredValues(ConcessionnaireBean concessionnaireBean){
        if(StringUtils.isBlank(concessionnaireBean.getCode())
                || StringUtils.isBlank(concessionnaireBean.getAbreviation())
                || StringUtils.isBlank(concessionnaireBean.getLibelle())){
            throw new ServiceException(ErrorMessageType.CONCESSIONNAIRE_MISSING_REQUIRED_VALUES);
        }
        if(concessionnaireBean.getConditionCommerciale() != null) {
        	conditionCommercialeService.validate(concessionnaireBean.getConditionCommerciale());
        }
    }

    private void removeCondition(ConcessionnaireBean concessionnaireBean){
        ConditionCommercialeBean conditionCommercialeBean = concessionnaireBean.getConditionCommerciale();
        if (conditionCommercialeBean != null){
            if (conditionCommercialeBean.getNoPayment()){
                concessionnaireBean.setConditionCommerciale(null);
                //conditionCommercialeBean.getId()
            }
            Long id = conditionCommercialeBean.getId();
            List<Periode> periodes = periodeRepository.findByConditionCommercialeId(id);
            for(Periode periode:periodes){
                periode.setConditionCommerciale(null);
                periodeRepository.save(periode);
            }
            conditionCommercialeService.delete(id);
        }

    }

    @Override
    @Transactional
    public GridListBean<ConcessionnaireBean> list(PageDataBean paginateData){
        return list(paginateData,ViewName.CONCESSIONNAIRE);
    }

    @Override
    @Transactional
    public ConcessionnaireBean getDetails(Long id) {
        Concessionnaire concessionnaire = findOne(id);
        if(concessionnaire == null) 
        	return null;
        return mapper.map(concessionnaire, ConcessionnaireBean.class);
    }

    @Override
	@Transactional
    public List<ConcessionnaireBean> getAll() {
    	Iterable<Concessionnaire> concessionnaires = concessionnaireRepository.findAll(QConcessionnaire.concessionnaire.active.isTrue());
        return mapper.map(concessionnaires, ConcessionnaireBean.LIST_BEAN_TYPE);
    }

	@Override
	public List<ChauffeurBean> getAllChauffeursByConcessionnaireId(Long id) {
			return chauffeurService.findByConcessionnaireId(id);
	}

	@Override
	public List<CamionBean> getAllCamionsByConcessionnaireId(Long id) {
		return camionService.findByConcessionnaireId(id);
	}
	@Override
	public List<SignataireBean> getAllSignatairesByConcessionnaireId(Long id) {
		return signataireService.findByConcessionnaireId(id);
	}

	@Override
	@Transactional
	public List<ConcessionnaireBean> findByClientId(Long id) {
		QConcessionnaire concessionnaire = QConcessionnaire.concessionnaire;
		BooleanExpression predicat = concessionnaire.affectations.any().client.id.eq(id).and(concessionnaire.active.isTrue());
		Iterable<Concessionnaire> concessionnaires = concessionnaireRepository.findAll(predicat);
		return mapper.map(concessionnaires, ConcessionnaireBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<ProduitBean> getAllProduitByConcessionnaireId(Long id) {
		List<Produit> produits = new ArrayList<Produit>();
		Concessionnaire concessionnaire = findOne(id);
        if(concessionnaire != null) {
        	List<Affectation> affectations = concessionnaire.getAffectations();
        	for (Affectation affectation : affectations) {
        		Produit produit = affectation.getProduit();
        		if(!produits.contains(produit)) {
        			produits.add(affectation.getProduit());
        		}
			}
        }
		return  mapper.map(produits, ProduitBean.LIST_BEAN_TYPE);
	}

}
