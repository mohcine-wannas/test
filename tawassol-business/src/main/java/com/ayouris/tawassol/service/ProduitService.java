package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.entity.Produit;

/**
 * 
 * @author m.wannas
 *
 */

public interface ProduitService extends GenericService<Produit, Long>, RefService<ProduitBean> {

	List<ProduitBean> getAll();

	List<ProduitBean> getAllProduitConditionne();

	List<ProduitBean> getAllProduitActif();
}
