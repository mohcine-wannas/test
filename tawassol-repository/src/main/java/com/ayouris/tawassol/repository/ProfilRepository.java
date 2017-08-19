package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.Profil;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
public interface ProfilRepository extends CommonRepository<Profil>,
        GridRepositoryCustom<ProfilBean> {
}
