package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Emplissage;

public interface EmplissageService extends GenericService<Emplissage, Long> {

	List<EmplissageBean> getAll();

	Long create(EmplissageBean object);
	
	EmplissageBean getDetails(Long id);

    GridListBean<EmplissageBean> list(PageDataBean paginateData);

	Boolean getAllEmpNotValide();

	EmplissageBean getControleEmplissage(Long id);

	Long updateEmplissageFromControl(EmplissageBean emplissageBean);

	Boolean isAllEmpNotValideFicheMarche(Long id);

}

