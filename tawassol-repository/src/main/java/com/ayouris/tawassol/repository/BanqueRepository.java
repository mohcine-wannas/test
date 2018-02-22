package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Banque;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.BanqueRepositoryCustom;

/**
 * Created by YounesM on 05/05/2017.
 */
public interface BanqueRepository extends CommonRepository<Banque>, BanqueRepositoryCustom{
}
