package com.ayouris.tawassol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.OrdreLivraisonRepositoryCustom;

public interface OrdreLivraisonRepository extends CommonRepository<OrdreLivraison>, OrdreLivraisonRepositoryCustom,
		GridRepositoryCustom<OrdreLivraisonBean> {

      @Query("Select max(o.numOL) from OrdreLivraison o where o.numOL like :pattern")
      String getLastCode(@Param("pattern") String pattern);

}
