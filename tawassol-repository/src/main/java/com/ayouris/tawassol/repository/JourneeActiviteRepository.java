package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutJourneeActivite;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.JourneeActiviteRepositoryCustom;

public interface JourneeActiviteRepository extends  CommonRepository<JourneeActivite>,JourneeActiviteRepositoryCustom, GridRepositoryCustom<JourneeActiviteBean> {


	JourneeActivite findBySiteAndActiviteAndStatut(Site currentSite, ActiviteJourneeActivite activite,
			StatutJourneeActivite ouverte);
	
}
