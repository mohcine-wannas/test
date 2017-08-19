package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AffectationProduitListOperateurBean;
import com.ayouris.tawassol.common.model.bean.AffectationProduitOperateurBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationProduitOperateurService extends GenericService<AffectationProduitOperateur, Long>, RefService<AffectationProduitOperateurBean> {

	List<AffectationProduitOperateurBean> getAll();

	Object findBySiteIdAndUserId(Long idSite, Long idUser);

	String createOrUpdate(AffectationProduitListOperateurBean object);

	void removeBySiteIdAndUserId(Long idSite, Long idUser);
}
