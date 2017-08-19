package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteConcessionnaireBean;
import com.ayouris.tawassol.common.model.entity.PrixVenteConcessionnaire;

public interface PrixVenteConcessionnaireService extends GenericService<PrixVenteConcessionnaire, Long> {

	List<PrixVenteConcessionnaireBean> getAll();

	Long create(PrixVenteConcessionnaireBean object);
	
	PrixVenteConcessionnaireBean getDetails(Long id);

    GridListBean<PrixVenteConcessionnaireBean> list(PageDataBean paginateData);

	Boolean isUnique(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean);

	List<PrixVenteConcessionnaireBean> overlap(PrixVenteConcessionnaireBean prixVenteConcessionnaireBean);

}

