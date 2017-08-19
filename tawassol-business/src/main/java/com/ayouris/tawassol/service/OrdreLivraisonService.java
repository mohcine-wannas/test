package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;

public interface OrdreLivraisonService extends GenericService<OrdreLivraison, Long> {

	List<OrdreLivraisonBean> getAll();

	String create(OrdreLivraisonBean object);
	
	OrdreLivraisonBean getDetails(Long id);

    GridListBean<OrdreLivraisonBean> list(PageDataBean paginateData);

	Boolean existeBonLivraison(OrdreLivraison ordre);

	void updatefromFileAttente(OrdreLivraisonBean ordreLivraisonBean);
	
	void annuler(Long id,String motif);

	Boolean isAnyOrdreNotValide(Long id);
}

