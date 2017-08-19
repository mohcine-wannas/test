package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.FileAttenteRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface FileAttenteRepository extends  CommonRepository<FileAttente>,FileAttenteRepositoryCustom, GridRepositoryCustom<FileAttenteBean> {

	
}
