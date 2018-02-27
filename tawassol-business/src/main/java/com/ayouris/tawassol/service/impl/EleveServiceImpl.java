package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.model.entity.Parent;
import com.ayouris.tawassol.common.model.entity.QAffectationEleveClasse;
import com.ayouris.tawassol.common.model.entity.QAffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.QEleve;
import com.ayouris.tawassol.repository.EleveRepository;
import com.ayouris.tawassol.service.AffectationParentEleveService;
import com.ayouris.tawassol.service.EleveService;
import com.ayouris.tawassol.service.ParentService;
import com.querydsl.jpa.JPAExpressions;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class EleveServiceImpl extends GenericServiceImpl2<Eleve, Long, EleveBean> implements EleveService {

	@Autowired
	private CustomModelMapper mapper;
	@Autowired
	private EleveRepository eleveRepository;
	@Autowired
	private ParentService parentService;
	@Autowired
	private AffectationParentEleveService affectationParentEleveService;
	
	@Override
	public List<EleveBean> getAllByClasseId(Long classeId) {
		
		QEleve eleve = QEleve.eleve;
		QAffectationEleveClasse affectationEleveClasse = QAffectationEleveClasse.affectationEleveClasse;
		Iterable<Eleve> list = eleveRepository.findAll(eleve.enabled.isTrue().and(eleve.id.in(JPAExpressions.selectFrom(affectationEleveClasse)
																					.where(affectationEleveClasse.classe.id.eq(classeId)
																							.and(affectationEleveClasse.classe.active.isTrue()))
																					.select(affectationEleveClasse.eleve.id))));
		return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
	}

	@Override
	public List<EleveBean> getAllByParentId(Long parentId) {
		
		QEleve eleve = QEleve.eleve;
		QAffectationParentEleve affectationParentEleve = QAffectationParentEleve.affectationParentEleve;
		Iterable<Eleve> list = eleveRepository.findAll(eleve.enabled.isTrue().and(eleve.id.in(JPAExpressions.selectFrom(affectationParentEleve)
																					.where(affectationParentEleve.parent.id.eq(parentId)
																							.and(affectationParentEleve.parent.enabled.isTrue()))
																					.select(affectationParentEleve.eleve.id))));
		return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
	}

	private Eleve getEleveByCodeMassar(String codeMassar) {
		List<Eleve> list = eleveRepository.findByCodeMassarAndEnabledTrue(codeMassar);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Long setParent(String codeMassar, ParentBean parentBean) {
		//TODO validators
		Eleve eleve = getEleveByCodeMassar(codeMassar);
		Parent parent = parentService.createOrUpdate(parentBean);
		AffectationParentEleve affectation = new AffectationParentEleve(eleve,parent);
		
		return affectationParentEleveService.save(affectation).getId();
	}
	@Override
	public void enableParent(Long id,Boolean enable) {
		//TODO validators 
		Eleve eleve = findOne(id);
		for (AffectationParentEleve affectation : eleve.getAffectationParents()) {
				affectation.setEnabled(enable);
				affectationParentEleveService.save(affectation);
		}
		
	}
}
