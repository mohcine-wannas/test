package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AffectationProduitOperateurBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.AffectationProduitOperateurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface AffectationProduitOperateurRepository extends  CommonRepository<AffectationProduitOperateur>,AffectationProduitOperateurRepositoryCustom, GridRepositoryCustom<AffectationProduitOperateurBean> {

	List<AffectationProduitOperateur> findBySiteIdAndUserId(Long idSite, Long idUser);

	List<AffectationProduitOperateur> findByUserId(Long idUser);

	List<AffectationProduitOperateur> removeBySiteIdAndUserId(Long idSite, Long idUser);

	
}
