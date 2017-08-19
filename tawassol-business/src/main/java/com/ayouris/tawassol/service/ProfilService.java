package com.ayouris.tawassol.service;


import java.util.List;

import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.Profil;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
public interface ProfilService extends GenericService<Profil,Long> {
    Long create(ProfilBean object);
    List<ProfilBean> getAll();
    ProfilBean getDetails(Long id);
	Profil findByCode(Long i);
}
