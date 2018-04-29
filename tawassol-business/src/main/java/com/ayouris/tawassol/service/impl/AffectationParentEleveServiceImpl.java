package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.entity.Parent;
import com.ayouris.tawassol.repository.AffectationParentEleveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.bean.AffectationParentEleveBean;
import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.service.AffectationParentEleveService;

import java.util.List;

/**
 * 
 *
 */

@Service
public class AffectationParentEleveServiceImpl extends GenericServiceImpl2<AffectationParentEleve,Long,AffectationParentEleveBean> implements AffectationParentEleveService {

    @Autowired
    private AffectationParentEleveRepository affectationParentEleveRepository;

    @Override
    public List<AffectationParentEleve> findByParent(Parent parent) {
        return affectationParentEleveRepository.findByParentId(parent.getId());
    }

//	@Autowired
//	EleveService eleveService;
	
//	@Override
//	public Long createOrUpdate(AffectationParentEleveBean affectationParentBean) {
//		ParentBean parent =  affectationParentBean.getParent();
//		Eleve eleve =  eleveService.getEleveByCodeMassar(affectationParentBean.getEleve().getCodeMassar());
//		return null;
//	}

}
