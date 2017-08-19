package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.entity.Produit;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.ProduitRepositoryCustom;

public interface ProduitRepository extends  CommonRepository<Produit>,ProduitRepositoryCustom, GridRepositoryCustom<ProduitBean> {


}
