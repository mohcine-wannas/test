package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.MotifBean;
import com.ayouris.tawassol.common.model.entity.Motif;

import com.ayouris.tawassol.repository.MotifRepository;
import com.ayouris.tawassol.service.MotifService;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */
@Service
public class MotifServiceImpl extends GenericServiceImpl<Motif, Long> implements MotifService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotifServiceImpl.class);

    @Autowired
    private MotifRepository motifRepository;

    @Autowired
    private CustomModelMapper mapper;


    @Override
    public Long create(MotifBean motifBean) {

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Motif entity = (Motif) mapper.map(motifBean, Motif.class);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        try {
            return save(entity).getId();
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }

    }

    @Override
    public List<MotifBean> getAll() {
        List<Motif> motifs = findAll();
        return mapper.map(motifs, MotifBean.LIST_BEAN_TYPE);
    }
}
