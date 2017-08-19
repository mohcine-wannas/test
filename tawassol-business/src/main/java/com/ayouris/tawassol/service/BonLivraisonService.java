package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.bean.ControleCamionConformityBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;

public interface BonLivraisonService extends GenericService<BonLivraison, Long> {

	List<BonLivraisonBean> getAll();

	Long create(BonLivraisonBean object);
	
	BonLivraisonBean getDetails(Long id);

    GridListBean<BonLivraisonBean> list(PageDataBean paginateData);

	BonLivraison createFromOL(OrdreLivraison ordrelivraison);

	//void annuler (Long id);

	void marquerTraite(Long id);

	//BonLivraison annuler(OrdreLivraison ordrelivraison);

	void annulerByOrdreLivraison(OrdreLivraison ordreLivraison, String motif);

	void annuler(BonLivraison bonLivraison, String motif);

	void annuler(Long id, String motif);

	void setControleCamionConformity(BonLivraisonBean BonLivraisonBean);

	List<ControleCamionConformityBean> getControleCamionConformities(Long id);

	List<BonLivraison> getAllBLNonRegleByConcessId(Long id);

	Boolean isAnyBlNotValide(Long id);
	
}

