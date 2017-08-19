package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.EmplissageControlColonneBean;
import com.ayouris.tawassol.common.model.bean.EmplissageControlLigneBean;
import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.entity.EmplissageControlColonne;
import com.ayouris.tawassol.common.model.entity.EmplissageControlLigne;
import com.ayouris.tawassol.common.model.entity.EmplissageResultControl;
import com.ayouris.tawassol.common.model.entity.QEmplissageControlColonne;
import com.ayouris.tawassol.common.model.entity.QEmplissageControlLigne;
import com.ayouris.tawassol.common.model.entity.QEmplissageResultControl;
import com.ayouris.tawassol.repository.EmplissageControlColonneRepository;
import com.ayouris.tawassol.repository.EmplissageControlLigneRepository;
import com.ayouris.tawassol.repository.EmplissageRepository;
import com.ayouris.tawassol.repository.EmplissageResultControlRepository;
import com.ayouris.tawassol.service.EmplissageResultControlService;

@Service
public class EmplissageResultControlServiceImpl extends GenericServiceImpl<EmplissageResultControl, Long>
		implements EmplissageResultControlService {

	@Autowired
	private EmplissageRepository emplissageRepository;

	@Autowired
	private EmplissageControlLigneRepository emplissageControlLigneRepository;

	@Autowired
	private EmplissageResultControlRepository emplissageResultControlRepository;

	@Autowired
	private EmplissageControlColonneRepository emplissageControlColonneRepository;

	@Autowired
	private CustomModelMapper mapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmplissageResultControlServiceImpl.class);

	@Override
	public void create(Long id) {
		Emplissage emplissage = emplissageRepository.findOne(id);
		List<EmplissageControlLigne> controlLignes = getAllControlLigne();
		List<EmplissageControlColonne> controlColonnes = getAllControlColonne();

		for (EmplissageControlLigne ligne : controlLignes) {
			for (EmplissageControlColonne colonne : controlColonnes) {
				EmplissageResultControl emplissageResultControl = new EmplissageResultControl();
				emplissageResultControl.setEmplissage(emplissage);
				emplissageResultControl.setEmplissageControlLigne(ligne);
				emplissageResultControl.setEmplissageControlColonne(colonne);
				save(emplissageResultControl);
			}
		}
	}

	@Override
	public void update(EmplissageBean emplissageBean) {
		EmplissageResultControl emplissageResultControl = null;
		Emplissage emplissage = emplissageRepository.findOne(emplissageBean.getId());
		//emplissageResultControlRepository.deleteByEmplissage(emplissage);

		for (Map.Entry<Long, Map<Long, Long>> ligne : emplissageBean.getMatriceEmplissageResultat().entrySet()) {
			for (Map.Entry<Long, Long> colonne : ligne.getValue().entrySet()) {
				if (colonne.getValue() != 0) {
					emplissageResultControl = new EmplissageResultControl();
					emplissageResultControl.setEmplissage(emplissage);
					emplissageResultControl.setResultat(colonne.getValue());
					emplissageResultControl
							.setEmplissageControlLigne(emplissageControlLigneRepository.findOne(ligne.getKey()));
					emplissageResultControl
							.setEmplissageControlColonne(emplissageControlColonneRepository.findOne(colonne.getKey()));
					save(emplissageResultControl);
				}
			}
		}
	}

	@Override
	@Transactional
	public List<EmplissageResultControl> getAllLineByIdEmplissage(Long id) {
		QEmplissageResultControl resultControl = QEmplissageResultControl.emplissageResultControl;
		Iterable<EmplissageResultControl> emplissageResultControlIterable = emplissageResultControlRepository
				.findAll(resultControl.emplissage.id.eq(id).and(resultControl.emplissageControlLigne.active.isTrue()));
		List<EmplissageResultControl> results = new ArrayList<EmplissageResultControl>();
		emplissageResultControlIterable.forEach(results::add);
		return results;
	}

	@Override
	@Transactional
	public Map<Long, Long> getAllColumnControlByIdLine(Long idEmplissage, Long idLigneControle) {
		QEmplissageResultControl resultControl = QEmplissageResultControl.emplissageResultControl;
		Iterable<EmplissageResultControl> emplissageResultControlIterable = emplissageResultControlRepository
				.findAll(resultControl.emplissage.id.eq(idEmplissage)
						.and(resultControl.emplissageControlLigne.id.eq(idLigneControle))
						.and(resultControl.emplissageControlColonne.active.isTrue()));
		Map<Long, Long> ligneResultat = new LinkedHashMap<Long, Long>();

		for (EmplissageResultControl iter : emplissageResultControlIterable) {
			ligneResultat.put(iter.getEmplissageControlColonne().getId(), iter.getResultat());
		}
		return ligneResultat;
	}

	@Override
	@Transactional
	public List<EmplissageControlLigne> getAllControlLigne() {
		return (List<EmplissageControlLigne>) emplissageControlLigneRepository
				.findAll(QEmplissageControlLigne.emplissageControlLigne.active.isTrue());
	}

	@Override
	@Transactional
	public List<EmplissageControlColonne> getAllControlColonne() {
		return (List<EmplissageControlColonne>) emplissageControlColonneRepository
				.findAll(QEmplissageControlColonne.emplissageControlColonne.active.isTrue());
	}

	@Override
	@Transactional
	public EmplissageResultControl getEmplissageResultControl(Long idEmplissage, Long idLigneControle,
			Long idColonneControle) {
		QEmplissageResultControl resultControl = QEmplissageResultControl.emplissageResultControl;
		return emplissageResultControlRepository
				.findOne(resultControl.emplissage.id.eq(idEmplissage).and(resultControl.emplissageControlLigne.id
						.eq(idLigneControle).and(resultControl.emplissageControlColonne.id.eq(idColonneControle))));
	}

	@Override
	@Transactional
	public List<EmplissageControlLigneBean> getAllControlLigneBean() {
		return mapper.map(getAllControlLigne(), EmplissageControlLigneBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<EmplissageControlColonneBean> getAllControlColonneBean() {
		return mapper.map(getAllControlColonne(), EmplissageControlColonneBean.LIST_BEAN_TYPE);
	}
}
