package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;

/**
 * 
 * @author m.wannas
 *
 */

public interface ConditionCommercialeService extends GenericService<ConditionCommerciale, Long>, RefService<ConditionCommercialeBean> {

	List<ConditionCommercialeBean> getAll();

	void validate(ConditionCommercialeBean conditionCommercialeBean);

	void savePeriodes(ConditionCommerciale conditionCommercialeBean);
}
