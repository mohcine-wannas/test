package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.Profil;
import com.ayouris.tawassol.common.model.entity.QProfil;
import com.ayouris.tawassol.repository.ProfilRepository;
import com.ayouris.tawassol.service.ProfilService;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
@Service
public class ProfilServiceImpl extends GenericServiceImpl<Profil,Long> implements ProfilService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotifServiceImpl.class);

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public Long create(ProfilBean profilBean) {

        Profil entity = (Profil) mapper.mapStrict(profilBean, Profil.class);

        try {
            return save(entity).getId();
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<ProfilBean> getAll() {
        List<Profil> motifs = findAll();
        return mapper.map(motifs, ProfilBean.LIST_BEAN_TYPE);
    }

    @Override
    public ProfilBean getDetails(Long id) {
        Profil profil  = findOne(id);
        return mapper.map(profil, ProfilBean.class);
    }

	@Override
	public Profil findByCode(Long code) {
		BooleanExpression predicat = QProfil.profil.code.eq(code);
		return profilRepository.findOne(predicat);
	}
}
