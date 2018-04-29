package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.UniteBean;
import com.ayouris.tawassol.common.model.entity.Unite;

import java.util.List;

public interface UniteService extends GenericService<Unite, Long> {

    List<UniteBean> findByCycleId(Long cycleId);

    List<Unite> findUnitesByCycleId(Long cycleId);
}
