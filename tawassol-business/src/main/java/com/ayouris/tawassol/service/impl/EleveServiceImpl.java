package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.model.entity.QAffectationEleveClasse;
import com.ayouris.tawassol.common.model.entity.QEleve;
import com.ayouris.tawassol.repository.EleveRepository;
import com.ayouris.tawassol.service.EleveService;
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
	
	@Override
	public List<EleveBean> getAllByClasseId(Long classeId) {
		
		QEleve eleve = QEleve.eleve;
		QAffectationEleveClasse affectationEleveClasse = QAffectationEleveClasse.affectationEleveClasse;
		Iterable<Eleve> list = eleveRepository.findAll(eleve.id.in(JPAExpressions.selectFrom(affectationEleveClasse)
																					.where(affectationEleveClasse.classe.id.eq(classeId))
																					.select(affectationEleveClasse.eleve.id)));
		return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
	}



	
}
