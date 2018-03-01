package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.NiveauBean;
import com.ayouris.tawassol.common.model.entity.Niveau;
import com.ayouris.tawassol.service.NiveauService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class NiveauServiceImpl extends GenericServiceImpl2<Niveau,Long,NiveauBean> implements NiveauService {

    

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<NiveauBean> getAll() {
        List<Niveau> niveaux = findAll();
        return mapper.map(niveaux, NiveauBean.LIST_BEAN_TYPE);
    }
	
}
