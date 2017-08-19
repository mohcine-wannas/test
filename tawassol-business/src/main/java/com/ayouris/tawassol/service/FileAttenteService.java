package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ControleCamionConformityBean;
import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;

public interface FileAttenteService extends GenericService<FileAttente, Long> {

	List<FileAttenteBean> getAll();

	String create(FileAttenteBean object);
	
	FileAttenteBean getDetails(Long id);
	
    GridListBean<FileAttenteBean> list(PageDataBean paginateData);
    
	void setControleCamionConformity(FileAttenteBean object);
	
	List<ControleCamionConformityBean> getControleCamionConformities(Long id);
	
	void updatefromOL(FileAttenteBean fileAttenteBean);

	List<FileAttenteBean> getFileAttenteEntree();


	List<FileAttenteBean> getFileAttenteRectifier();

	FileAttenteBean getFileAttenteLinkOL(OrdreLivraisonBean ordre);

	OrdreLivraisonBean getOLRectifier(FileAttente fileAttente);

	void annulerByOrdreLivraison(OrdreLivraison ordreLivraison);

	void setAlaSortieByOrdreLivraison(OrdreLivraison ordreLivraison);

	void setAuCentreOrdreLivraison(OrdreLivraison ordreLivraison);
}

