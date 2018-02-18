package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.GroupeAppellationBean;
import com.ayouris.tawassol.common.model.entity.GroupeAppellation;

/**
 * 
 * @author m.wannas
 *
 */

public interface GroupeAppellationService extends GenericService<GroupeAppellation,Long> {


	List<GroupeAppellationBean> getAll();

	GroupeAppellation getDefaultGroupeAppellation();

}
