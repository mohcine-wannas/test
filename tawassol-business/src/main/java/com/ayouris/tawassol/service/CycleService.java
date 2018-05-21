package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.Cycle;

import java.util.List;

public interface CycleService extends GenericService<Cycle, Long> {


    List<CycleBean> getAllBySchoolCode(String schoolCode);

}
