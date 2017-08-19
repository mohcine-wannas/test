package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.BonLivraisonRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface BonLivraisonRepository extends CommonRepository<BonLivraison>, BonLivraisonRepositoryCustom,
		GridRepositoryCustom<BonLivraisonBean> {

	BonLivraison findByOrdreLivraison(OrdreLivraison ordreLivraison);
}
