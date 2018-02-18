package com.ayouris.tawassol.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AnneeScolaireBean;
import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.repository.AnneeScolaireRepository;
import com.ayouris.tawassol.security.service.AnneeScolaireSecurityService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AnneeScolaireSecurityServiceImpl extends BaseServiceImpl<AnneeScolaire>
		implements AnneeScolaireSecurityService {

	@Autowired
	protected AnneeScolaireSecurityServiceImpl(AnneeScolaireRepository AnneeScolaireRepository) {
		super(AnneeScolaireRepository);
		this.AnneeScolaireRepository = AnneeScolaireRepository;
	}

	
	private AnneeScolaireRepository AnneeScolaireRepository;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public List<AnneeScolaireBean> getAll() {
		List<AnneeScolaire> anneeScolaires = findAll();
		return mapper.map(anneeScolaires, FabriquantBean.LIST_BEAN_TYPE);
	}

	@Override
	public AnneeScolaire getCurrentAnneeScolaire() {
		List<AnneeScolaire> anneeScolaires = AnneeScolaireRepository.findByCurrentTrue();
		if (anneeScolaires != null && !anneeScolaires.isEmpty()) {
			return anneeScolaires.get(0);
		}
		return null;
	}

}
