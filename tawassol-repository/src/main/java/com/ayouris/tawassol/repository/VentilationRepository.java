package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Ventilation;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.VentilationRepositoryCustom;

public interface VentilationRepository extends CommonRepository<Ventilation>, VentilationRepositoryCustom {
}
