package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AnneeBean;
import com.ayouris.tawassol.common.model.bean.MoisBean;
import com.ayouris.tawassol.common.model.entity.Annee;
import com.ayouris.tawassol.common.model.entity.Mois;
import com.ayouris.tawassol.repository.AnneeRepository;
import com.ayouris.tawassol.repository.MoisRepository;

@Service
public class DateServiceImpl {

	@Autowired
	private AnneeRepository anneeRepository;
	
    @Autowired
    private MoisRepository moisRepository;
    
    @Autowired
	private CustomModelMapper mapper;
    

    public List<AnneeBean> getAllYears() {
        Iterable<Annee> annees = anneeRepository.findAll();
        return mapper.map(annees, AnneeBean.LIST_BEAN_TYPE);
    }
    
    public List<MoisBean> getAllMonths() {
        Iterable<Mois> mois = moisRepository.findAll();
        return mapper.map(mois, MoisBean.LIST_BEAN_TYPE);
    }

}
