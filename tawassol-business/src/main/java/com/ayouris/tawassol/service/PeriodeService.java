package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.PeriodeBean;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.enums.Periodicite;

/**
 * 
 * @author m.wannas
 *
 */

public interface PeriodeService extends GenericService<Periode, Long>, RefService<PeriodeBean> {

	List<PeriodeBean> getAll();

    void validateRequiredValues(PeriodeBean periodeBean,Periodicite periodicite);

	void removePeriodesByConditionComerciale(ConditionCommerciale conditionCommerciale);

	Periode getPeriodeByConcess(Long id, int day);

}
