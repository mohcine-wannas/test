package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.UniteBean;
import com.ayouris.tawassol.common.model.entity.QUnite;
import com.ayouris.tawassol.common.model.entity.Unite;
import com.ayouris.tawassol.repository.UniteRepository;
import com.ayouris.tawassol.service.UniteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UniteServiceImpl extends GenericServiceImpl2<Unite, Long, UniteBean> implements UniteService {


    @Autowired
    private CustomModelMapper mapper;

    @Autowired
    private UniteRepository uniteRepository;

    @Override
    public List<UniteBean> findByCycleId(Long cycleId) {
        QUnite unite = QUnite.unite;

        List<Unite> unites = (List<Unite>) uniteRepository.findAll(unite.cycle.id.eq(cycleId).and(unite.active.isTrue()));
        return mapper.map(unites, UniteBean.LIST_BEAN_TYPE);
    }


    @Override
    public List<Unite> findUnitesByCycleId(Long cycleId) {
        QUnite unite = QUnite.unite;

        return (List<Unite>) uniteRepository.findAll(unite.cycle.id.eq(cycleId).and(unite.active.isTrue()));
    }
}
