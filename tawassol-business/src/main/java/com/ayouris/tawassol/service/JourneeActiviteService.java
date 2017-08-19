package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;

public interface JourneeActiviteService extends GenericService<JourneeActivite, Long>, RefService<JourneeActiviteBean> {

	List<JourneeActiviteBean> getAll();


	JourneeActivite getCurrentJourneeActiviteOuvertByActivity(ActiviteJourneeActivite activite);

}