package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.DestinationBean;
import com.ayouris.tawassol.common.model.entity.DestinationOL;


public interface DestinationService extends GenericService<DestinationOL, Long>, RefService<DestinationBean> {

	List<DestinationBean> getAll();
}
