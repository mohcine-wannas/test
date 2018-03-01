package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AppellationBean;
import com.ayouris.tawassol.common.model.entity.Appellation;
import com.ayouris.tawassol.service.AppellationService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AppellationServiceImpl extends GenericServiceImpl2<Appellation,Long,AppellationBean> implements AppellationService {

//    @Autowired
//    private AppellationRepository AppellationRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<AppellationBean> getAll() {
        List<Appellation> appellations = findAll();
        return mapper.map(appellations, AppellationBean.LIST_BEAN_TYPE);
    }

	
}
