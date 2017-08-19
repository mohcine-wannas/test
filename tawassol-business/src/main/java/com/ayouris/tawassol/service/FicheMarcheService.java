package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.bean.CauseArretEmplissageBean;
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.FicheMarcheBean;
import com.ayouris.tawassol.common.model.bean.PostBean;
import com.ayouris.tawassol.common.model.entity.FicheMarche;

public interface FicheMarcheService extends GenericService<FicheMarche, Long>, RefService<FicheMarcheBean> {

	List<FicheMarcheBean> getAll();

	List<PostBean> getAllPost();

	FicheMarche getFichesNotValideByUsername(EmplissageBean emplissageBean, User user);

	List<CauseArretEmplissageBean> getAllCausesArret();

	void cloturer(Long id);

	Boolean ficheMarcheNonCloturee(FicheMarche entity);

	Boolean isAllFichesNotValide(Long id);

}

