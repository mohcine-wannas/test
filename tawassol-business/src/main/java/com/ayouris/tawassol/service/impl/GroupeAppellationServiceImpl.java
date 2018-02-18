package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GroupeAppellationBean;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.GroupeAppellation;
import com.ayouris.tawassol.common.model.entity.QGroupeAppellation;
import com.ayouris.tawassol.repository.GroupeAppellationRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.GroupeAppellationService;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class GroupeAppellationServiceImpl extends GenericServiceImpl2<GroupeAppellation,Long, GroupeAppellationBean> implements GroupeAppellationService {

    @Autowired
    private GroupeAppellationRepository groupeAppellationRepository;

    @Autowired
    private CustomModelMapper mapper;


	@Override
	public List<GroupeAppellationBean>  getAll() {
		
		Cycle currentCycle = SecurityUtils.getCurrentCycle(); 
        List<GroupeAppellation> groupeAppellations = groupeAppellationRepository.findByCycleIdAndActiveTrueOrderByOrderAsc(currentCycle.getId()); 
        return mapper.map(groupeAppellations, GroupeAppellationBean.LIST_BEAN_TYPE);
	}


	@Override
	public GroupeAppellation getDefaultGroupeAppellation() {
		QGroupeAppellation groupeAppellation = QGroupeAppellation.groupeAppellation;
		
		BooleanExpression expression = groupeAppellation.defaultChoice.isTrue()
													.and(groupeAppellation.active.isTrue());
		
		Iterable<GroupeAppellation> groupeAppellations = groupeAppellationRepository.findAll(expression);
		if(groupeAppellations.iterator().hasNext()) {
			return groupeAppellations.iterator().next();
		}
		
		return null;
	}
	
}
