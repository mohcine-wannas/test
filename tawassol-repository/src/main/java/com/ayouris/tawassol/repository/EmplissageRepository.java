package com.ayouris.tawassol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.EmplissageRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface EmplissageRepository extends CommonRepository<Emplissage>, EmplissageRepositoryCustom,
		GridRepositoryCustom<EmplissageBean> {

      @Query("Select max(o.numOE) from Emplissage o where o.numOE like :pattern")
      String getLastCode(@Param("pattern") String pattern);

}
