package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AppellationBean;
import com.ayouris.tawassol.common.model.entity.Appellation;

/**
 * 
 * @author m.wannas
 *
 */

public interface AppellationService extends GenericService<Appellation,Long> {


	List<AppellationBean> getAll();

}
