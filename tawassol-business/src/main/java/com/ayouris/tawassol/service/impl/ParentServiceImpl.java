package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.Parent;
import com.ayouris.tawassol.common.model.entity.QAffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.QParent;
import com.ayouris.tawassol.repository.ParentRepository;
import com.ayouris.tawassol.security.service.PasswordService;
import com.ayouris.tawassol.service.AffectationParentEleveService;
import com.ayouris.tawassol.service.ParentService;
import com.ayouris.tawassol.service.SchoolService;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ParentServiceImpl extends GenericServiceImpl2<Parent, Long, ParentBean> implements ParentService {

	@Autowired
	private CustomModelMapper mapper;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private AffectationParentEleveService affectationParentEleveService;

	@Override
	public List<ParentBean> getAllByEleveId(Long eleveId) {
		
		QParent parent = QParent.parent;
		QAffectationParentEleve affectationParentEleve = QAffectationParentEleve.affectationParentEleve;
		Iterable<Parent> list = parentRepository.findAll(parent.id.in(JPAExpressions.selectFrom(affectationParentEleve)
																					.where(affectationParentEleve.eleve.id.eq(eleveId))
																					.select(affectationParentEleve.parent.id)));
		return mapper.map(list, ParentBean.LIST_BEAN_TYPE);
	}

	@Override
	public Parent createOrUpdate(ParentBean parentBean) {
		//vaidators
		Parent parent = mapper.mapStrict(parentBean,Parent.class);
		parent.setPassword(passwordService.encodePassword(parent.getPassword()));
		parent.setUsername(parent.getPhoneNumber());
		return save(parent);
	}

	@Override
	public boolean isValidated(Long parentId) throws Exception {
		Parent parent = findOne(parentId);
		if(parent == null) {
			//TODO throw error;
			throw new Exception("not found parent with id : " + parentId);
		}
		List<AffectationParentEleve> affectations = affectationParentEleveService.findByParent(parent);
		for(AffectationParentEleve affectation : affectations) {
			if(affectation.getEnabled() != null && affectation.getEnabled()) {
				return true;
			}
		}
		return false;
	}


}
