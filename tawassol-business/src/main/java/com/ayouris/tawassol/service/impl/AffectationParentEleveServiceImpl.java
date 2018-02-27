package com.ayouris.tawassol.service.impl;

import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.bean.AffectationParentEleveBean;
import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.service.AffectationParentEleveService;

/**
 * 
 *
 */

@Service
public class AffectationParentEleveServiceImpl extends GenericServiceImpl2<AffectationParentEleve,Long,AffectationParentEleveBean> implements AffectationParentEleveService {

//	@Autowired
//	EleveService eleveService;
	
//	@Override
//	public Long createOrUpdate(AffectationParentEleveBean affectationParentBean) {
//		ParentBean parent =  affectationParentBean.getParent();
//		Eleve eleve =  eleveService.getEleveByCodeMassar(affectationParentBean.getEleve().getCodeMassar());
//		return null;
//	}

}
