package com.ayouris.tawassol.service.impl;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.service.AffectationEleveClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ClasseBean;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.entity.Classe;
import com.ayouris.tawassol.common.model.entity.QClasse;
import com.ayouris.tawassol.repository.ClasseRepository;
import com.ayouris.tawassol.service.ClasseService;;


/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ClasseServiceImpl extends GenericServiceImpl2<Classe,Long,ClasseBean> implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private AffectationEleveClasseService affectationEleveClasseService;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<ClasseBean> getAll() {
        List<Classe> classes = findAll();
        return mapper.map(classes, ClasseBean.LIST_BEAN_TYPE);
    }

	@Override
	public List<ClasseBean> getAllByNiveauId(Long id) {
        Iterable<Classe> list = getClassesByNiveauId(id);
		return mapper.map(list, ClasseBean.LIST_BEAN_TYPE);
	}

    @Override
    public List<Classe> getClassesByNiveauId(Long id) {
        QClasse classe = QClasse.classe;
        return (List<Classe>)classeRepository.findAll(classe.affectationNiveau.niveau.id.eq(id));
    }

    @Override
    public Classe getClasseByEleve(Eleve eleve) {
        AffectationEleveClasse affectation = affectationEleveClasseService.getClasseByEleve(eleve);
        if(affectation != null) {
            return affectation.getClasse();
        }
        return null;

    }

}
