package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonView;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Chauffeur;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QChauffeur;
import com.ayouris.tawassol.common.view.RefView.ChauffeurListView;
import com.ayouris.tawassol.repository.ChauffeurRepository;
import com.ayouris.tawassol.service.ChauffeurService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ChauffeurServiceImpl extends GenericServiceImpl<Chauffeur, Long> implements ChauffeurService {

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private ColumnDefService columnDefService;

    @Autowired
    private CustomModelMapper mapper;

    @Override

    public Long createOrUpdate(ChauffeurBean chauffeurBean) {
        validateRequiredValues(chauffeurBean);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
        Chauffeur entity = (Chauffeur) mapper.map(chauffeurBean, Chauffeur.class);
        mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);
       
        try {
        	return save(entity).getId();
        }catch(DataIntegrityViolationException ex) {
        	throw new ServiceException(ErrorMessageType.CHAUFFEUR_CIN_ALREADY_EXISTS);
        }

    }

    private void validateRequiredValues(ChauffeurBean chauffeurBean){
        if(StringUtils.isBlank(chauffeurBean.getNom())
                || StringUtils.isBlank(chauffeurBean.getPermis())
                || StringUtils.isBlank(chauffeurBean.getCin())
                || chauffeurBean.getConcessionnaire() == null){
            throw new ServiceException(ErrorMessageType.CHAUFFEUR_MISSING_REQUIRED_VALUES);
        }
    }

    @Override
    @JsonView(ChauffeurListView.class)
    public GridListBean<ChauffeurBean> list(PageDataBean paginateData){
        return chauffeurRepository.findEntities(paginateData,getColumnDef());
    }

    @Override
    @Transactional
    public ChauffeurBean getDetails(Long id) {
        Chauffeur chauffeur = findOne(id);
        return mapper.map(chauffeur, ChauffeurBean.class);
    }

    public Map<String, ColumnDef> getColumnDef() {
    		return columnDefService.getColumnDefMapByViewName(ViewName.CHAUFFEUR);
    }

	@Override
	@Transactional
	public List<ChauffeurBean> findByConcessionnaireId(Long id) {
		List<Chauffeur> chauffeurs = chauffeurRepository.findByConcessionnaireIdAndActiveTrue(id);
		return mapper.map(chauffeurs, ChauffeurBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<ChauffeurBean> getAll() {
    	Iterable<Chauffeur> chauffeurs = chauffeurRepository.findAll(QChauffeur.chauffeur.active.isTrue());
	    return mapper.map(chauffeurs, ChauffeurBean.LIST_BEAN_TYPE);
	}
}
