package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.common.model.bean.AffectationNiveauBean;
import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.entity.AffectationNiveau;
import com.ayouris.tawassol.repository.AffectationNiveauRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.AffectationNiveauService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AffectationNiveauServiceImpl extends GenericServiceImpl2<AffectationNiveau,Long,AffectationNiveauBean> implements AffectationNiveauService {

    @Autowired
    private AffectationNiveauRepository AffectationNiveauRepository;

    @Autowired
    private CustomModelMapper mapper;
   @Autowired
   private AffectationCycleService affectationCycleService;

    @Override
    public List<AffectationNiveauBean> getAll() {
    	return affectationCycleService.getCurrentAffectationCycleBean().getAffectationNiveaux();
    }

	
}
