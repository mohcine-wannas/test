package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.PrestationBean;
import com.ayouris.tawassol.common.model.entity.Prestation;

/**
 * 
 * @author m.wannas
 *
 */

public interface PrestationService extends GenericService<Prestation, Long>, RefService<PrestationBean> {

	List<PrestationBean> getAll();
}
